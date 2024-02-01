package de.dome.shopy.utils.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Cuboid;
import de.dome.shopy.utils.Ressoure;
import de.dome.shopy.utils.items.Item;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.items.ItemRessourecenKosten;
import de.dome.shopy.utils.items.ItemSeltenheit;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class Shop {

    int shopId;
    Shop instance;
    Player owner;
    int level;
    int ressourcenLagerSize;
    int itemLagerSize;
    World world;
    ArrayList<Cuboid> zones;
    ShopRessourenManger shopRessourenManger;
    /* itemKategorieLevel auf den Shop bezogen */
    Map<String, ShopItemKategorieLevel> itemKategorieLevel;
    ArrayList<ShopItem> shopItems;
    ArrayList<ShopItemVorlage> shopItemVorlagen;
    /* Halte fest, ob überhaupt ein Spielershop gefunden wurde  */
    private boolean loadShop = false;

    public Shop(UUID ownerUUID, Boolean playerTeleport){
        zones = new ArrayList<>();
        shopItems = new ArrayList<>();
        shopItemVorlagen = new ArrayList<>();
        itemKategorieLevel = new LinkedHashMap<>();
        instance = this;

        CompletableFuture<Void> basisDaten = CompletableFuture.runAsync(() -> {
            try {
                String query = "SELECT * FROM shop WHERE owner = '" + ownerUUID + "' LIMIT 1";
                ResultSet result = Shopy.getInstance().getMySQLConntion().resultSet(query);

                /* Shop daten aus datenbank laden*/
                if (result.next()){
                    loadShop = true;

                    this.shopId = result.getInt("id");
                    this.owner = Bukkit.getPlayer(UUID.fromString(result.getString("owner")));
                    this.level = result.getInt("shop_level");
                    String world = Shopy.getInstance().getDataFolder().getPath() + "/shop_welten/"  + result.getString("shop_ordner");

                    /* Frage ab, ob die welt schon geladen ist. Wenn nicht, lade Sie*/
                    Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
                        if(Bukkit.getWorld(world) == null){
                            WorldCreator creator = new WorldCreator(world);

                            creator.environment(World.Environment.NORMAL);
                            creator.type(WorldType.NORMAL);

                            this.world = creator.createWorld();
                            if(playerTeleport) this.owner.teleport(this.world.getSpawnLocation());
                        }else {
                            this.world = Bukkit.getWorld(world);
                        }

                        CompletableFuture<Void> ladeShopGrundStucke = CompletableFuture.runAsync(() -> {
                            try {
                                String queryZones = "SELECT * FROM shop_template_zonen WHERE template = " + result.getInt("template") +" LIMIT " + result.getInt("shop_zones");

                                ResultSet resultZones = Shopy.getInstance().getMySQLConntion().resultSet(queryZones);
                                while(resultZones.next()){
                                    Location loc1 = getLocationFromString(resultZones.getString("locationen_1"));
                                    loc1.setY(-63);
                                    loc1.setWorld(this.world);

                                    Location loc2 = getLocationFromString(resultZones.getString("locationen_2"));
                                    loc2.setY(-50);
                                    loc2.setWorld(this.world);

                                    Cuboid add = new Cuboid(loc1, loc2);

                                    zones.add(add);
                                }
                            } catch (SQLException e) {
                                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
                            }
                        });
                    });
                    //Item Kategorie Level Eintragen
                    for(ItemKategorie  itemKategorie : ItemKategorie.itemKategorieList){
                        int level = -1;
                        int xpZumNachstenLevel = -1;
                        int aktuelleXP = -1;

                        String itemKategorieQuery = "SELECT * FROM shop_werte WHERE wert = 'itemKategorie_" + itemKategorie.getName() + "_level' LIMIT 1";
                        ResultSet resultItemKategorie= Shopy.getInstance().getMySQLConntion().resultSet(itemKategorieQuery);

                        if(resultItemKategorie.next()){
                            level = Integer.parseInt(resultItemKategorie.getString("value"));
                        }else {
                            Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, wert, value) VALUES ('" + shopId + "', 'itemKategorie_" + itemKategorie.getName() + "_level', '1')");
                            level = 1;
                        }

                        itemKategorieQuery = "SELECT * FROM shop_werte WHERE wert = 'itemKategorie_" + itemKategorie.getName() + "_xp' LIMIT 1";
                        resultItemKategorie= Shopy.getInstance().getMySQLConntion().resultSet(itemKategorieQuery);

                        if(resultItemKategorie.next()){
                            aktuelleXP = Integer.parseInt(resultItemKategorie.getString("value"));
                        }else {
                            Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, wert, value) VALUES ('" + shopId + "', 'itemKategorie_" + itemKategorie.getName() + "_xp', '0')");
                            aktuelleXP = 0;
                        }

                        itemKategorieQuery = "SELECT * FROM item_kategorie_level WHERE level = '" + level +"' LIMIT 1";
                        resultItemKategorie= Shopy.getInstance().getMySQLConntion().resultSet(itemKategorieQuery);

                        if(resultItemKategorie.next()){
                            xpZumNachstenLevel = resultItemKategorie.getInt("xp");
                        }

                        itemKategorieLevel.put(itemKategorie.getName(), new ShopItemKategorieLevel(level, xpZumNachstenLevel, aktuelleXP, itemKategorie, this));
                    }

                    Shopy.getInstance().getSpielerShops().put(ownerUUID, this);

                    /* Item vorlagen laden */
                    int lastID = 1;
                    for(Item item : Item.itemList){
                        String itemQuery = "SELECT * FROM shop_item_vorlage WHERE shop = '"+ shopId +"' AND item = '"+ item.getId() +"' LIMIT 1";
                        ResultSet resultItem = Shopy.getInstance().getMySQLConntion().resultSet(itemQuery);
                        ShopItemVorlage shopItemVorlage;

                        if(resultItem.next()){
                            boolean freigeschaltet = false;
                            if(resultItem.getString("freigeschaltet").equalsIgnoreCase("JA")) freigeschaltet = true;

                            shopItemVorlage = new ShopItemVorlage(resultItem.getInt("id"), this, item, resultItem.getInt("hergestellt"), freigeschaltet);
                            lastID = resultItem.getInt("id");
                        }else {
                            Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_item_vorlage (shop, item) VALUES ('" + shopId + "', '"+ item.getId() +"')");
                            shopItemVorlage = new ShopItemVorlage(lastID, this, item, 0,false);

                            lastID++;
                        }

                        shopItemVorlagen.add(shopItemVorlage);
                    }

                    String queryItemLager = "SELECT * From shop_item_lager JOIN item ON item.id = shop_item_lager.item";
                    ResultSet resultItemLager = Shopy.getInstance().getMySQLConntion().resultSet(queryItemLager);
                    while(resultItemLager.next()){
                        ShopItem newItem = new ShopItem(resultItemLager.getInt("shop_item_lager.id"), ItemKategorie.getItemKategorieById(resultItemLager.getInt("item_kategorie")), resultItemLager.getString("name"), resultItemLager.getString("beschreibung"), Material.getMaterial(resultItemLager.getString("icon")), ItemSeltenheit.getItemStufeById(resultItemLager.getInt("item_seltenheit")));
                        shopItems.add(newItem);
                    }
                }

            } catch (SQLException e) { }
        });
        basisDaten.thenRun(() -> {
            if(loadShop){
                this.shopRessourenManger = new ShopRessourenManger(this);
            }
            CompletableFuture.runAsync(() -> {
                String query = "SELECT * FROM shop_werte WHERE shop = '" + shopId + "'";
                ResultSet result = Shopy.getInstance().getMySQLConntion().resultSet(query);

                /* Shop werte daten aus datenbank laden*/
                try {
                    while (result.next()){
                        if(result.getString("wert").equals("ressourcen_lager")) ressourcenLagerSize = Integer.parseInt(result.getString("value"));
                        if(result.getString("wert").equals("item_lager")) itemLagerSize = Integer.parseInt(result.getString("value"));
                    }
                } catch (SQLException e) {
                    Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
                }

            });
        });
    }

    public void unLoadWorld(){
        Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
            Bukkit.unloadWorld(world, true);
        });
    }

    public void openMarkplatzInventar(){
        RyseInventory.builder().title("§9Ressouren Markplatz").rows(4).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                int solt = 10;
                int zaheler = 0;
                for (Map.Entry<Ressoure, Integer> shopRessoure : Shopy.getInstance().getSpielerShops().get(owner.getUniqueId()).getRessourenShopManger().getShopRessoure().entrySet()) {
                    Ressoure ressoure = shopRessoure.getKey();
                    if(!ressoure.getType().equalsIgnoreCase("STANDART")) continue;

                    /*
                      Anzahl farbig markieren, damit man leichter erkennt wie der Stand ist:
                      X < getRessourcenLager = §e (Gelb)
                      X ==getRessourcenLager = §a (Grün)
                      X > getRessourcenLager = §c (Rot)
                    *  */
                    String colorkey = "§e";
                    if(shopRessoure.getValue() <  getRessourcenLager()) colorkey = "§e";
                    if(shopRessoure.getValue() == getRessourcenLager()) colorkey = "§a";
                    if(shopRessoure.getValue() >  getRessourcenLager()) colorkey = "§c";

                    ArrayList<String> beschreibung = new ArrayList<>();
                    beschreibung.add("§7Deine Menge: " + colorkey +  shopRessoure.getValue() + " §7/§e " + getRessourcenLager() + " §7" + ressoure.getName());
                    beschreibung.add("§7Aktuelle Kosten: §e" + Math.round(ressoure.getAktuelleKosten()) + " §7€");
                    beschreibung.add("");
                    beschreibung.add("§6(Du hast " + shopRessourenManger.getRessourceValue(Ressoure.getRessoureByName("geld")) + " €)");
                    beschreibung.add("");
                    beschreibung.add("§5" + ressoure.getBeschreibung());

                    contents.set(solt, Shopy.getInstance().createItemWithLore(ressoure.getIcon(), "§9" + ressoure.getName(), beschreibung));

                    /* Items Anordenen */
                    zaheler++;
                    if (zaheler == 7) {
                        solt += 3;
                        zaheler = 0;
                    } else {
                        solt++;
                    }
                }
            }
            @Override
            public void update(Player player, InventoryContents contents) {
                int solt = 10;
                int zaheler = 0;
                for (Map.Entry<Ressoure, Integer> shopRessoure : Shopy.getInstance().getSpielerShops().get(owner.getUniqueId()).getRessourenShopManger().getShopRessoure().entrySet()) {
                    Ressoure ressoure = shopRessoure.getKey();
                    if(!ressoure.getType().equalsIgnoreCase("STANDART")) continue;

                    /*
                      Anzahl farbig markieren, damit man leichter erkennt wie der Stand ist:
                      X < getRessourcenLager = §e (Gelb)
                      X ==getRessourcenLager = §a (Grün)
                      X > getRessourcenLager = §c (Rot)
                    *  */
                    String colorkey = "§e";
                    if(shopRessoure.getValue() <  getRessourcenLager()) colorkey = "§e";
                    if(shopRessoure.getValue() == getRessourcenLager()) colorkey = "§a";
                    if(shopRessoure.getValue() >  getRessourcenLager()) colorkey = "§c";

                    ArrayList<String> beschreibung = new ArrayList<>();
                    beschreibung.add("§7Deine Menge: " + colorkey +  shopRessoure.getValue() + " §7/§e " + getRessourcenLager() + " §7" + ressoure.getName());
                    beschreibung.add("§7Aktuelle Kosten: §e" + Math.round(ressoure.getAktuelleKosten()) + " §7€");
                    beschreibung.add("");
                    beschreibung.add("§6(Du hast " + shopRessourenManger.getRessourceValue(Ressoure.getRessoureByName("geld")) + " €)");
                    beschreibung.add("");
                    beschreibung.add("§5" + ressoure.getBeschreibung());

                    contents.update(solt, Shopy.getInstance().createItemWithLore(ressoure.getIcon(), "§9" + ressoure.getName(), beschreibung));

                    /* Items Anordenen */
                    zaheler++;
                    if (zaheler == 7) {
                        solt += 3;
                        zaheler = 0;
                    } else {
                        solt++;
                    }
                }
            }
        }).build(Shopy.getInstance()).open(owner);
    }
    public void openMarkplatzWaffenInventar(int seite, ItemKategorie itemKategorie){
        RyseInventory.builder().title("§9Werkbank " + itemKategorie.getName() + " Seite " + seite).rows(3).provider(new InventoryProvider() {
//            @Override
//            public void init(Player player, InventoryContents contents) {
//                int solt = 11;
//                int zaheler = 0;
//                int startBei = seite * 5;
//
//                boolean letztesItemGesetzt = false;
//                boolean erstesItemgesetzt = false;
//
//                for(Item item : Item.itemList){
//                    if(item.getItemKategorie().getId() != itemKategorie.getId()) continue;
//                    if(startBei > zaheler){
//                        zaheler = startBei;
//                        continue;
//                    }else if(startBei != -1){
//                        zaheler = 0;
//                        startBei = -1;
//                    }
//                    if(!erstesItemgesetzt) erstesItemgesetzt = true;
//
//                    ArrayList<String> beschreibung = new ArrayList<>();
//                    beschreibung.add("");
//
//                    beschreibung.add("§7Kosten:");
//                    for(ItemRessourecenKosten itr : item.getRessourecsKostenList()){
//                        beschreibung.add("  §7- §e" + itr.getMenge() + " §7" + itr.getRessoure().getName());
//                    }
//                    beschreibung.add("");
//                    beschreibung.add("§7Erfahrungspunkt:");
//                    beschreibung.add("  §7- §e" + item.getShopXp() + " §7Shop Erfahrungspunkte");
//                    beschreibung.add("  §7- §e" + item.getKategorieXp() + " §7Item-linien Erfahrungspunkte");
//                    beschreibung.add("");
//
//                    String[] beschreibungsArray = item.getBeschreibung().split("\n");
//                    for(String itemBeschreibung : beschreibungsArray){
//                        beschreibung.add(itemBeschreibung.trim());
//                    }
//
//                    String itemName = "§9" + item.getName() +  " " + item.getItemSeltenheit().getFarbe() + " [" + item.getItemSeltenheit().getName() + "]";
//                    contents.set(solt, Shopy.getInstance().createItemWithLore(item.getIcon(), itemName, beschreibung));
//
//                    zaheler++;
//                    solt += 1;
//                    if (zaheler == 5) {
//                        letztesItemGesetzt = true;
//                        break;
//                    }
//                }
//                if(!erstesItemgesetzt){
//                    openMarkplatzWaffenInventar(seite -1, itemKategorie);
//                    return;
//                }
//
//                /*Menü Regeler */
//                if(seite != 0)  contents.set(9, Shopy.getInstance().createItem(Material.ARROW, "§7Letzte Seite"));
//                if(letztesItemGesetzt) contents.set(17, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));
//
//                contents.set(18, Shopy.getInstance().createItem(Material.CRAFTING_TABLE, "§7Zurück zur Übersicht"));
//                contents.set(26, Shopy.getInstance().createItem(Material.BARRIER, "§7Menü Schlissen"));
//
//                contents.set(4, Shopy.getInstance().createItemWithLore(itemKategorie.getIcon(), "§9" + itemKategorie.getName() + " Statistk", itemKategorie.getAnzeigeBeschreibung(instance)));
//            }
            @Override
            public void init(Player player, InventoryContents contents) {
                int solt = 11;
                int zaheler = 0;
                int startBei = seite * 5;

                boolean letztesItemGesetzt = false;
                boolean erstesItemgesetzt = false;

                for(ShopItemVorlage shopItemVorlage : getShopItemVorlage()){
                    Item item = shopItemVorlage.getItem();

                    if(item.getItemKategorie().getId() != itemKategorie.getId()) continue;
                    if(startBei > zaheler){
                        zaheler = startBei;
                        continue;
                    }else if(startBei != -1){
                        zaheler = 0;
                        startBei = -1;
                    }
                    if(!erstesItemgesetzt) erstesItemgesetzt = true;

                    if(shopItemVorlage.isfreigeschaltet()){
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("");

                        beschreibung.add("§7Kosten:");
                        for(ItemRessourecenKosten itr : item.getRessourecsKostenList()){
                            beschreibung.add("  §7- §e" + itr.getMenge() + " §7" + itr.getRessoure().getName());
                        }
                        beschreibung.add("");
                        beschreibung.add("§7Erfahrungspunkt:");
                        beschreibung.add("  §7- §e" + item.getShopXp() + " §7Shop Erfahrungspunkte");
                        beschreibung.add("  §7- §e" + item.getKategorieXp() + " §7Item-linien Erfahrungspunkte");
                        beschreibung.add("");

                        String[] beschreibungsArray = item.getBeschreibung().split("\n");
                        for(String itemBeschreibung : beschreibungsArray){
                            beschreibung.add(itemBeschreibung.trim());
                        }

                        String itemName = "§9" + item.getName() +  " " + item.getItemSeltenheit().getFarbe() + " [" + item.getItemSeltenheit().getName() + "]";
                        contents.set(solt, Shopy.getInstance().createItemWithLore(item.getIcon(), itemName, beschreibung));
                    }else {
                        String FreischlatTyp = "§cNicht bekannt";
                        if(item.getFreischlatTyp().equalsIgnoreCase("STANDART")) FreischlatTyp = "Aufleveln von Items";
                        if(item.getFreischlatTyp().equalsIgnoreCase("ITEM")) FreischlatTyp = "Benutzen eines Bauplans";

                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("");
                        beschreibung.add("§7wird Freigeschaltet durch:");
                        beschreibung.add("§e" + FreischlatTyp);
                        beschreibung.add("");
                        beschreibung.add("§7Dieses Item wurde noch nicht freigeschaltet.");
                        beschreibung.add("§7Schalte das Item frei, in dem du mehr Items");
                        beschreibung.add("§7produzierst oder dir einen Bauplan, kaufst.");


                        String itemName = "§7Item Freischalten.";
                        contents.set(solt, Shopy.getInstance().createItemWithLore(Material.GRAY_DYE, itemName, beschreibung));
                    }

                    zaheler++;
                    solt += 1;
                    if (zaheler == 5) {
                        letztesItemGesetzt = true;
                        break;
                    }
                }
                if(!erstesItemgesetzt){
                    openMarkplatzWaffenInventar(seite -1, itemKategorie);
                    return;
                }

                /*Menü Regeler */
                if(seite != 0)  contents.set(9, Shopy.getInstance().createItem(Material.ARROW, "§7Letzte Seite"));
                if(letztesItemGesetzt) contents.set(17, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));

                contents.set(18, Shopy.getInstance().createItem(Material.CRAFTING_TABLE, "§7Zurück zur Übersicht"));
                contents.set(26, Shopy.getInstance().createItem(Material.BARRIER, "§7Menü Schlissen"));

                contents.set(4, Shopy.getInstance().createItemWithLore(itemKategorie.getIcon(), "§9" + itemKategorie.getName() + " Statistk", itemKategorie.getAnzeigeBeschreibung(instance)));
            }
            @Override
            public void update(Player player, InventoryContents contents) {
                contents.update(4, Shopy.getInstance().createItemWithLore(itemKategorie.getIcon(), "§9" + itemKategorie.getName() + " Statistk", itemKategorie.getAnzeigeBeschreibung(instance)));
            }
        }).build(Shopy.getInstance()).open(this.owner);
    }
    public void openWerkbankInventar(){
        RyseInventory.builder().title("§9Werkbank-Kategorie").rows(3).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                int solt = 10;
                int zaheler = 0;
                for(ItemKategorie itemKategorie : ItemKategorie.itemKategorieList){
                    ArrayList<String> beschreibung = itemKategorie.getAnzeigeBeschreibung(instance);
                    beschreibung.add("");
                    beschreibung.add(itemKategorie.getBeschreibung());

                    contents.set(solt, Shopy.getInstance().createItemWithLore(itemKategorie.getIcon(), "§9" + itemKategorie.getName(), beschreibung));

                    zaheler+=2;
                    if (zaheler <= 7) {
                        solt += 3;
                        zaheler = 0;
                    } else {
                        solt++;
                    }
                }
            }
        }).build(Shopy.getInstance()).open(owner);
    }

    public void openItemLagerInventar(int seite){
        RyseInventory.builder().title("§9Item Lager Seite " + seite)
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {
                        boolean letztesItemGsetzt = false;

                        for(int i = (0 + (seite * 44)); i <= 44 * (seite + 1);i++){
                            /* Item Slot unabhängig der Shop Seite. Darf nicht größer 53 sein */
                            int slot = i - (seite * 44);

                            if(i <= getShopItems().size() - 1){
                                letztesItemGsetzt = true;

                                /* Item laden */
                                ShopItem shopItem = getShopItems().get(i);
                                ArrayList<String> beschreibung = new ArrayList<>();
                                /* ID anzeigen*/
                                beschreibung.add("§7Item-ID: " + shopItem.getId() + "");
                                /* Actionen auflisten*/
                                beschreibung.add("");
                                beschreibung.add("§c- Rechtsklick zum Löschen");
                                beschreibung.add("");

                                String[] beschreibungsArray = shopItem.getBeschreibung().split("\n");

                                for(String itemBeschreibung : beschreibungsArray){
                                    beschreibung.add(itemBeschreibung.trim());
                                }


                                String itemName = "§9" + shopItem.getName() +  " " + shopItem.getItemSeltenheit().getFarbe() + " [" + shopItem.getItemSeltenheit().getName() + "]";

                                contents.set(slot, Shopy.getInstance().createItemWithLore(shopItem.getIcon(), "§9" + itemName, beschreibung));
                            }else {
                                if(i > getItemLagerSize() - 1){
                                    letztesItemGsetzt = false;

                                    ArrayList beschreibung = new ArrayList();
                                    beschreibung.add("");
                                    beschreibung.add("§7Du kannst diesen Solt freischalten, in dem du mehr Item Lager in deinem Shop aufbaust.");

                                    contents.set(slot, Shopy.getInstance().createItemWithLore(Material.GRAY_DYE, "§7Solt noch nicht freigeschaltet", beschreibung));
                                }
                            }
                        }
                        contents.set(45, Shopy.getInstance().createItem(Material.TRAPPED_CHEST, "§7zur Shopübersicht"));
                        contents.set(46, Shopy.getInstance().createItem(Material.BARRIER, "§7Menü Schlissen"));

                        if(seite != 0)          contents.set(52, Shopy.getInstance().createItem(Material.ARROW, "§7Letzte Seite"));
                        if(letztesItemGsetzt)   contents.set(53, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));

                    }
                }).build(Shopy.getInstance()).open(owner);
    }

    public void changeRessourcenLager(int newAmount){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET value = '"+ newAmount +"' WHERE wert = 'ressourcen_lager'");
        });
        ressourcenLagerSize = newAmount;
    }
    public void changeItemLager(int newAmount){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET value = '"+ newAmount +"' WHERE wert = 'item_lager'");
        });
        itemLagerSize = newAmount;
    }

    public Player getOwner() {
        return owner;
    }

    public int getLevel() {
        return level;
    }

    public World getWorld() {
        return world;
    }

    public int getShopId() {
        return shopId;
    }

    public ArrayList<Cuboid> getZones() {
        return zones;
    }

    public ShopRessourenManger getRessourenShopManger() {
        return shopRessourenManger;
    }

    public int getRessourcenLager() {
        return ressourcenLagerSize;
    }

    public int getItemLagerSize() {
        return itemLagerSize;
    }

    public ArrayList<ShopItem> getShopItems() {
        return shopItems;
    }

    public Shop getInstance() {
        return instance;
    }

    public ShopItem getShopItemById(int id){
        ShopItem ret = null;

        for(ShopItem shopItem : shopItems){
            if(shopItem.getId() == id){
                ret = shopItem;
                break;
            }
        }

        return ret;
    }
    public ShopItemVorlage getShopItemVorlageByItem(int id){
        ShopItemVorlage ret = null;

        for(ShopItemVorlage shopItemVorlage : shopItemVorlagen){
            if(shopItemVorlage.getItem().getId() == id){
                ret = shopItemVorlage;
                break;
            }
        }

        return ret;
    }

    public void delteShopItemById(int id){
        for(int i = 0; i <= shopItems.size() - 1; i++){
            ShopItem shopItem = shopItems.get(i);

            if(shopItem.getId() == id){
                CompletableFuture.runAsync(() -> {
                    Shopy.getInstance().getMySQLConntion().query("DELETE FROM shop_item_lager WHERE ID = '" + id +"'");
                });

                shopItems.remove(i);
                break;
            }
        }
    }

    public int getRessourcenLagerSize() {
        return ressourcenLagerSize;
    }

    public ShopRessourenManger getShopRessourenManger() {
        return shopRessourenManger;
    }

    public ArrayList<ShopItemVorlage> getShopItemVorlage() {
        return shopItemVorlagen;
    }

    public Map<String, ShopItemKategorieLevel> getItemKategorieLevel() {
        return itemKategorieLevel;
    }

    private Location getLocationFromString(String locationString) {
        Location ret = null;

        String[] dataArray = locationString.split(",");

        String worldname = dataArray[0].split("name=")[1];
        worldname = worldname.substring(0, worldname.length() - 1);
        World world = Bukkit.getWorld(worldname);

        double x =      Double.parseDouble(dataArray[1].split("=")[1]);
        double y =      Double.parseDouble(dataArray[2].split("=")[1]);
        double z =      Double.parseDouble(dataArray[3].split("=")[1]);
        float pitch =   Float.parseFloat(dataArray[3].split("=")[1]);
        float yaw =     Float.parseFloat(dataArray[3].split("=")[1]);

        ret = new Location(world, x,y,z,yaw,pitch);

        return ret;
    }
}
