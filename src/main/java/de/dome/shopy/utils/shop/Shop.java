package de.dome.shopy.utils.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Cuboid;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.items.Item;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.items.ItemRessourecenKosten;
import de.dome.shopy.utils.items.ItemSeltenheit;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.inventory.ItemStack;

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
    int taskIdSpawnManger = -1;
    int shopTemplate;
    int shopTemplateMaxGroße;

    int reduzierteKundenSpawnZeit;
    int zusaetzlicheKundenProGrunstueck;
    double zusaetzlicheKundenWahrscheinlichkeit;
    double zusaetzlichesItemWahrscheinlichkeit;
    double zusaetzlicheKategorieWahrscheinlichkeit;
    double zusaetzlicherVerkaufserlös;
    double reduzierteMaterialienKosten;

    World world;
    Location shopSpawn;
    ArrayList<Cuboid> zones;
    ShopRessourenManger shopRessourenManger;
    /* itemKategorieLevel auf den Shop bezogen */
    Map<String, ShopItemKategorie> shopItemKategorie;
    ArrayList<ShopItem> shopItems;
    ArrayList<ShopItemVorlage> shopItemVorlagen;
    ArrayList<ShopKunden> shopKunden;



    /* Halte fest, ob überhaupt ein Spielershop gefunden wurde  */
    private boolean loadShop = false;

    public Shop(Player owner, Boolean playerTeleport){
        this.owner = owner;
        zones = new ArrayList<>();
        shopItems = new ArrayList<>();
        shopItemVorlagen = new ArrayList<>();
        shopItemKategorie = new LinkedHashMap<>();
        shopKunden = new ArrayList<>();
        instance = this;
        
        CompletableFuture<Void> basisDaten = CompletableFuture.runAsync(() -> {
            try {
                String query = "SELECT * FROM shop WHERE owner = '" + owner.getUniqueId() + "' LIMIT 1";
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
                            this.world.setTime(0);

                            this.world.setSpawnLimit(SpawnCategory.ANIMAL, 0);
                            this.world.setSpawnLimit(SpawnCategory.AMBIENT, 0);
                            this.world.setSpawnLimit(SpawnCategory.AXOLOTL, 0);
                            this.world.setSpawnLimit(SpawnCategory.MONSTER, 0);

                            this.world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                            if(playerTeleport) this.owner.teleport(this.world.getSpawnLocation());
                        }else {
                            this.world = Bukkit.getWorld(world);
                        }


                         CompletableFuture.runAsync(() -> {
                            try {
                                this.shopTemplate = result.getInt("template");
                                String queryZones = "SELECT * FROM shop_template_zonen WHERE template = " + this.shopTemplate +" LIMIT " + result.getInt("shop_zones");

                                ResultSet resultZones = Shopy.getInstance().getMySQLConntion().resultSet(queryZones);
                                while(resultZones.next()){
                                    Location loc1 = Shopy.getInstance().getLocationFromString(resultZones.getString("locationen_1"));
                                    loc1.setY(-70);
                                    loc1.setWorld(this.world);

                                    Location loc2 = Shopy.getInstance().getLocationFromString(resultZones.getString("locationen_2"));
                                    loc2.setY(-50);
                                    loc2.setWorld(this.world);

                                    Cuboid add = new Cuboid(loc1, loc2);

                                    zones.add(add);
                                }

                                String queryPosition = "SELECT * FROM shop_template_werte WHERE template = " + result.getInt("template") +" LIMIT 1";

                                ResultSet resultPosition = Shopy.getInstance().getMySQLConntion().resultSet(queryPosition);
                                while(resultPosition.next()){
                                    if(resultPosition.getString("schlussel").equals("shop_spawn")){
                                        this.shopSpawn = Shopy.getInstance().getLocationFromString(resultPosition.getString("inhalt"));
                                        this.shopSpawn.setWorld(this.world);
                                    }
                                }

                                String queryMaxGroße = "SELECT COUNT(id) AS 'anzahl' FROM shop_template_zonen WHERE template = " + this.shopTemplate;

                                ResultSet resultMaxGroße = Shopy.getInstance().getMySQLConntion().resultSet(queryMaxGroße);
                                if(resultMaxGroße.next()) this.shopTemplateMaxGroße = resultMaxGroße.getInt("anzahl");
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

                        /* Vorhandes Level */
                        String itemKategorieQuery = "SELECT * FROM shop_werte WHERE schlussel = 'itemKategorie_" + itemKategorie.getName() + "_level' LIMIT 1";
                        ResultSet resultItemKategorie= Shopy.getInstance().getMySQLConntion().resultSet(itemKategorieQuery);

                        if(resultItemKategorie.next()){
                            level = Integer.parseInt(resultItemKategorie.getString("inhalt"));
                        }else {
                            Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shopId + "', 'itemKategorie_" + itemKategorie.getName() + "_level', '1')");
                            level = 1;
                        }

                        /* ist Item Freigeschlatet */
                        boolean freigeschaltet = false;

                        itemKategorieQuery = "SELECT * FROM shop_werte WHERE schlussel = 'itemKategorie_" + itemKategorie.getName() + "_freigeschaltet' LIMIT 1";
                        resultItemKategorie= Shopy.getInstance().getMySQLConntion().resultSet(itemKategorieQuery);

                        if(resultItemKategorie.next()){
                            int tmpFreigeschaltet = Integer.parseInt(resultItemKategorie.getString("inhalt"));
                            if(tmpFreigeschaltet == 1) freigeschaltet = true;
                        }else {
                            if(itemKategorie.getId() == 3 || itemKategorie.getId() == 5){
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shopId + "', 'itemKategorie_" + itemKategorie.getName() + "_freigeschaltet', '1')");
                                freigeschaltet = true;
                            }else {
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shopId + "', 'itemKategorie_" + itemKategorie.getName() + "_freigeschaltet', '0')");
                                freigeschaltet = false;
                            }
                        }

                        /* Vorhande XP */
                        itemKategorieQuery = "SELECT * FROM shop_werte WHERE schlussel = 'itemKategorie_" + itemKategorie.getName() + "_xp' LIMIT 1";
                        resultItemKategorie= Shopy.getInstance().getMySQLConntion().resultSet(itemKategorieQuery);

                        if(resultItemKategorie.next()){
                            aktuelleXP = Integer.parseInt(resultItemKategorie.getString("inhalt"));
                        }else {
                            Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shopId + "', 'itemKategorie_" + itemKategorie.getName() + "_xp', '0')");
                            aktuelleXP = 0;
                        }

                        /* XP zum nächten Level */
                        itemKategorieQuery = "SELECT * FROM item_kategorie_level WHERE level = '" + level +"' LIMIT 1";
                        resultItemKategorie= Shopy.getInstance().getMySQLConntion().resultSet(itemKategorieQuery);

                        if(resultItemKategorie.next()){
                            xpZumNachstenLevel = resultItemKategorie.getInt("xp");
                        }

                        shopItemKategorie.put(itemKategorie.getName(), new ShopItemKategorie(level, xpZumNachstenLevel, aktuelleXP, itemKategorie, this, freigeschaltet));
                    }

                    Shopy.getInstance().getSpielerShops().put(owner.getUniqueId(), this);

                    /* Item vorlagen laden */
                    int lastID = 1;
                    for(Item item : Item.itemList){
                        String itemQuery = "SELECT * FROM shop_item_vorlage WHERE shop = '"+ shopId +"' AND item = '"+ item.getId() +"' LIMIT 1";
                        ResultSet resultItem = Shopy.getInstance().getMySQLConntion().resultSet(itemQuery);
                        ShopItemVorlage shopItemVorlage;

                        if(resultItem.next()){
                            boolean freigeschaltet = false;
                            if(resultItem.getString("freigeschaltet").equalsIgnoreCase("JA")) freigeschaltet = true;

                            shopItemVorlage = new ShopItemVorlage(resultItem.getInt("id"), this, item, resultItem.getInt("hergestellt"), resultItem.getBoolean("meisterung"), freigeschaltet);
                            lastID = resultItem.getInt("id") + 1;
                        }else {
                            if(item.isImmerFreigeschaltet()) {
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_item_vorlage (shop, item, freigeschaltet) VALUES ('" + shopId + "', '"+ item.getId() +"', 'JA')");
                                shopItemVorlage = new ShopItemVorlage(lastID, this, item, 0, false,true);
                            }else {
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_item_vorlage (shop, item) VALUES ('" + shopId + "', '"+ item.getId() +"')");
                                shopItemVorlage = new ShopItemVorlage(lastID, this, item, 0, false,false);
                            }
                            lastID++;
                        }

                        shopItemVorlagen.add(shopItemVorlage);
                    }

                    String queryItemLager = "SELECT *,shop_item.id AS 'sid',item.id AS 'iid' From shop_item JOIN item ON item.id = shop_item.item";
                    ResultSet resultItemLager = Shopy.getInstance().getMySQLConntion().resultSet(queryItemLager);
                    while(resultItemLager.next()){
                        double schaden = 0;
                        double angriffsgeschwindigkeit = 0;
                        double rustung = 0;
                        int haltbarkeit = 0;

                        String queryItemLagerItemWerte = "SELECT * FROM shop_item_werte WHERE item = " + resultItemLager.getInt("sid");
                        ResultSet resultItemLagerItemWerte = Shopy.getInstance().getMySQLConntion().resultSet(queryItemLagerItemWerte);
                        while(resultItemLagerItemWerte.next()){
                            if(resultItemLagerItemWerte.getString("schlussel").equals("schaden")) schaden = Double.parseDouble(resultItemLagerItemWerte.getString("inhalt"));
                            if(resultItemLagerItemWerte.getString("schlussel").equals("angriffsgeschwindigkeit")) angriffsgeschwindigkeit = Double.parseDouble(resultItemLagerItemWerte.getString("inhalt"));
                            if(resultItemLagerItemWerte.getString("schlussel").equals("haltbarkeit")) haltbarkeit = Integer.parseInt(resultItemLagerItemWerte.getString("inhalt"));
                            if(resultItemLagerItemWerte.getString("schlussel").equals("ruestung")) rustung = Double.parseDouble(resultItemLagerItemWerte.getString("inhalt"));
                        }

                        ShopItem newItem = new ShopItem(resultItemLager.getInt("sid"), resultItemLager.getInt("iid"),ItemKategorie.getItemKategorieById(resultItemLager.getInt("item_kategorie")), resultItemLager.getString("name"), resultItemLager.getString("beschreibung"), Material.getMaterial(resultItemLager.getString("icon")), ItemSeltenheit.getItemStufeById(resultItemLager.getInt("item_seltenheit")), schaden, angriffsgeschwindigkeit, rustung, haltbarkeit);
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
                        if(result.getString("schlussel").equals("ressourcen_lager")) ressourcenLagerSize = Integer.parseInt(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("item_lager")) itemLagerSize = Integer.parseInt(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("zusätzliche_kunden_spawn_zeit")) reduzierteKundenSpawnZeit = Integer.parseInt(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("zusätzliche_kunden_pro_grundstück")) zusaetzlicheKundenProGrunstueck = Integer.parseInt(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("zusätzliche_kunden_wahrscheinlichkeit")) zusaetzlicheKundenWahrscheinlichkeit = Integer.parseInt(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("zusätzliches_item_wahrscheinlichkeit")) zusaetzlichesItemWahrscheinlichkeit = Integer.parseInt(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("zusätzliche_kategorie_wahrscheinlichkeit")) zusaetzlicheKategorieWahrscheinlichkeit = Integer.parseInt(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("zusätzlicher_verkaufserlös")) zusaetzlicherVerkaufserlös = Integer.parseInt(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("reduzierte_materialien_kosten")) reduzierteMaterialienKosten = Integer.parseInt(result.getString("inhalt"));
                    }
                } catch (SQLException e) {
                    Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
                }

            });

            Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
                Shopy.getInstance().getScoreboardManger().setScoreBoard(owner);
                kundenManger();
            });

        });
    }

    public void unLoadWorld(){
        Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
            if(taskIdSpawnManger != -1) Bukkit.getScheduler().cancelTask(taskIdSpawnManger);

            for(ShopKunden shopKunden : shopKunden){
                shopKunden.npc.getNavigator().cancelNavigation();
                shopKunden.npc.despawn();
                shopKunden.npc.destroy();
            }

            Bukkit.unloadWorld(world, true);
        });
    }

    public void openMarkplatzInventar(){
        RyseInventory.builder().title("§9Ressouren Markplatz").rows(4).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                int solt = 10;
                int zaheler = 0;
                for (Map.Entry<Ressource, Integer> shopRessoure : Shopy.getInstance().getSpielerShops().get(owner.getUniqueId()).getRessourenShopManger().getShopRessoure().entrySet()) {
                    Ressource ressource = shopRessoure.getKey();
                    if(!ressource.getType().equalsIgnoreCase("STANDART")) continue;

                    String colorkey = "§e";
                    if(shopRessoure.getValue() <  getRessourcenLager()) colorkey = "§e";
                    if(shopRessoure.getValue() == getRessourcenLager()) colorkey = "§a";
                    if(shopRessoure.getValue() >  getRessourcenLager()) colorkey = "§c";

                    double kosten = Math.round(ressource.getAktuelleKosten());
                    if(reduzierteMaterialienKosten > 0) kosten -= kosten * (reduzierteMaterialienKosten / 100);

                    ArrayList<String> beschreibung = new ArrayList<>();
                    beschreibung.add("§7Deine Menge: " + colorkey +  shopRessoure.getValue() + " §7/§e " + getRessourcenLager() + " §7" + ressource.getName());
                    beschreibung.add("§7Aktuelle Kosten: §e" + kosten + " §7€");
                    beschreibung.add("");
                    beschreibung.add("§6(Du hast " + shopRessourenManger.getRessourceValue(Ressource.getRessoureByName("geld")) + " €)");
                    beschreibung.add("");
                    beschreibung.add("§5" + ressource.getBeschreibung());

                    contents.updateOrSet(solt, Shopy.getInstance().createItemWithLore(ressource.getIcon(), "§9" + ressource.getName(), beschreibung));

                    /* Items Anordenen */
                    zaheler++;
                    if (zaheler == 7) {
                        solt += 3;
                        zaheler = 0;
                    }else if(solt == 14 ){
                        solt += 5;
                        zaheler = 0;
                    }else if(solt == 20){
                        solt += 8;
                        zaheler = 0;
                    } else {
                        solt++;
                    }
                }
            }
            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(owner);
    }
    public void openWaffenCraftInventar(int seite, ItemKategorie itemKategorie){
        RyseInventory.builder().title("§9Werkbank " + itemKategorie.getName() + " Seite " + seite).rows(3).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                int solt = 11;
                int zaheler = 0;
                int startBei = seite * 5;

                boolean letztesItemGesetzt = false;
                boolean erstesItemgesetzt = false;

                for (ShopItemVorlage shopItemVorlage : getShopItemVorlage()) {
                    Item item = shopItemVorlage.getItem();

                    if (item.getItemKategorie().getId() != itemKategorie.getId()) continue;
                    if (startBei > zaheler) {
                        zaheler++;
                        continue;
                    } else if (startBei != -1) {
                        zaheler = 0;
                        startBei = -1;
                    }
                    if (!erstesItemgesetzt) erstesItemgesetzt = true;

                    if (shopItemVorlage.isfreigeschaltet()) {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("");

                        beschreibung.add("§7Kosten:");
                        for (ItemRessourecenKosten itr : item.getRessourecsKostenList()) {
                            if(itr == null) continue;
                            beschreibung.add("  §7- §e" + itr.getMenge() + " §7" + itr.getRessoure().getName());
                        }

                        beschreibung.add("");
                        beschreibung.add("§7Herstellungen: §e" + shopItemVorlage.getHergestellt());
                        if(shopItemVorlage.isMeisterung()) beschreibung.add("§7Diese gegenstand wurde §egemeistert!");
                        else beschreibung.add("§7Meisterung: §e" + item.getMeisterMenge());
                        beschreibung.add("");

                        beschreibung.add("§7Erfahrungspunkt:");
                        beschreibung.add("  §7- §e" + item.getShopXp() + " §7Shop Erfahrungspunkte");
                        beschreibung.add("  §7- §e" + item.getKategorieXp() + " §7Item-linien Erfahrungspunkte");
                        beschreibung.add("");

                        String[] beschreibungsArray = item.getBeschreibung().split("\n");
                        for (String itemBeschreibung : beschreibungsArray) {
                            beschreibung.add(itemBeschreibung.trim());
                        }

                        String itemName = "§9" + item.getName() + " " + item.getItemSeltenheit().getFarbe() + " [" + item.getItemSeltenheit().getName() + "]";


                        contents.updateOrSet(solt, Shopy.getInstance().createItemWithLore(item.getIcon(), itemName, beschreibung, false, true));
                    } else {
                        String FreischlatTyp = "§cNicht bekannt";
                        Item freischaltItem = Item.getItemByFreischaltItem(item);

                        if (item.getFreischlatTyp().equalsIgnoreCase("STANDART")) FreischlatTyp = "Aufleveln von Items";
                        if (item.getFreischlatTyp().equalsIgnoreCase("ITEM")) FreischlatTyp = "Benutzen eines Bauplans";

                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("");
                        if (freischaltItem != null) {
                            int hergestellt = getShopItemVorlageByItem(freischaltItem.getId()).getHergestellt();
                            int freischaltMenege = freischaltItem.getFreischaltMenge();
                            String freischaltItemName = freischaltItem.getName();

                            beschreibung.add("§9" + freischaltItemName + ":");
                            beschreibung.add("§e" + hergestellt + " §7/ §e" + freischaltMenege);
                        } else {
                            beschreibung.add("§7wird Freigeschaltet durch:");
                            beschreibung.add("§e" + FreischlatTyp);
                        }
                        beschreibung.add("");
                        beschreibung.add("§7Dieses Item wurde noch nicht freigeschaltet.");
                        beschreibung.add("§7Schalte das Item frei, in dem du mehr Items");
                        beschreibung.add("§7produzierst oder dir einen Bauplan, kaufst.");


                        String itemName = "§eItem Freischalten.";
                        contents.updateOrSet(solt, Shopy.getInstance().createItemWithLore(Material.GRAY_DYE, itemName, beschreibung));
                    }

                    zaheler++;
                    solt += 1;

                    if (zaheler >= 5) {
                        letztesItemGesetzt = true;
                        break;
                    }
                }
                if (!erstesItemgesetzt) {
                    if(seite > 0){
                        openWaffenCraftInventar(seite - 1, itemKategorie);
                        return;
                    }
                }

                /*Menü Regeler */
                if (seite != 0) contents.updateOrSet(9, Shopy.getInstance().createItem(Material.ARROW, "§7Letzte Seite"));
                if (letztesItemGesetzt) contents.updateOrSet(17, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));

                contents.updateOrSet(18, Shopy.getInstance().createItem(Material.CRAFTING_TABLE, "§7Zurück zur Übersicht"));
                contents.updateOrSet(26, Shopy.getInstance().createItem(Material.BARRIER, "§7Menü Schlissen"));

                contents.updateOrSet(4, Shopy.getInstance().createItemWithLore(itemKategorie.getIcon(), "§9" + itemKategorie.getName() + " Statistk", itemKategorie.getAnzeigeBeschreibung(instance)));
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(this.owner);
    }
    public void openWerkbankInventar(){
        RyseInventory.builder().title("§9Werkbank-Kategorie").rows(5).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                int solt = 10;
                int zaheler = 0;
                for(ShopItemKategorie shopItemKategorie : shopItemKategorie.values()){

                    if(shopItemKategorie.isFreigeschaltet()){
                        ArrayList<String> beschreibung = shopItemKategorie.getItemKategorie().getAnzeigeBeschreibung(instance);

                        beschreibung.add("");
                        String[] beschreibungsArray = shopItemKategorie.getItemKategorie().getBeschreibung().split("\n");
                        for (String itemBeschreibung : beschreibungsArray) {
                            beschreibung.add(itemBeschreibung.trim());
                        }

                        contents.updateOrSet(solt, Shopy.getInstance().createItemWithLore(shopItemKategorie.getItemKategorie().getIcon(), "§9" + shopItemKategorie.getItemKategorie().getName(), beschreibung));
                    }else {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("");
                        beschreibung.add("§7Diese Kategorie wurde noch nicht freigeschaltet.");
                        beschreibung.add("§7Die Kategorie kann freigeschaltet werden, in dem");
                        beschreibung.add("§7sie bei Mona gekauft wird!");

                        String itemName = "§eKategorie noch nicht Freischalten.";
                        contents.updateOrSet(solt, Shopy.getInstance().createItemWithLore(Material.GRAY_DYE, itemName, beschreibung));
                    }

                    zaheler+=2;
                    if (zaheler <= 7) {
                        solt += 3;
                        zaheler = 0;
                    } else {
                        solt++;
                    }
                }
            }
            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
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

                            if(i <= getItemLagerSize() - 1){
                                if(i <= getShopItems().size() - 1) {
                                    letztesItemGsetzt = true;

                                    /* Item laden */
                                    ShopItem shopItem = getShopItems().get(i);
                                    if(shopItem.getHaltbarkeit() <= 0) continue;

                                    ArrayList<String> beschreibung = shopItem.getVolleBeschreibung();
                                    beschreibung.add("");

                                    /* Beschreibung angepassten, je nachdem ob man in einem Dungeon ist */
                                    if(Shopy.getInstance().getSpielerDungeon().containsKey(owner.getUniqueId())) {
                                        beschreibung.add("§a- Linksklick zum Item entnehmen");
                                    }else {
                                        beschreibung.add("§c- Rechtsklick zum Löschen");
                                    }

                                    ItemStack item = shopItem.buildBaseItem();
                                    item.setLore(beschreibung);

                                    contents.updateOrSet(slot, item);
                                }
                            }else {
                                if(i > getItemLagerSize() - 1){
                                    letztesItemGsetzt = false;

                                    ArrayList beschreibung = new ArrayList();
                                    beschreibung.add("");
                                    beschreibung.add("§7Du kannst diesen Solt freischalten, in dem du mehr Item Lager in deinem Shop aufbaust.");

                                    contents.updateOrSet(slot, Shopy.getInstance().createItemWithLore(Material.GRAY_DYE, "§7Solt noch nicht freigeschaltet", beschreibung));
                                }
                            }
                        }
                        // Option nicht in einem Dungeon anbieten
                        if(!Shopy.getInstance().getSpielerDungeon().containsKey(owner.getUniqueId())) {
                            contents.updateOrSet(45, Shopy.getInstance().createItem(Material.TRAPPED_CHEST, "§7zur Shopübersicht"));
                            contents.updateOrSet(46, Shopy.getInstance().createItem(Material.BARRIER, "§7Menü Schlissen"));
                        }else contents.set(45, Shopy.getInstance().createItem(Material.BARRIER, "§7Menü Schlissen"));


                        if(seite != 0)          contents.updateOrSet(52, Shopy.getInstance().createItem(Material.ARROW, "§7Letzte Seite"));
                        if(letztesItemGsetzt)   contents.updateOrSet(53, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));

                    }
                    @Override
                    public void update(Player player, InventoryContents contents) {
                        init(player, contents);
                    }
                }).build(Shopy.getInstance()).open(owner);
    }
    public void openRessourenUbersicht(String type){
        RyseInventory.builder().title("§9Shop - Ressourcen Übersicht")
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {
                        int solt = 10;
                        int zaheler = 0;
                        for (Map.Entry<Ressource, Integer> shopRessoure : getRessourenShopManger().getShopRessoure().entrySet()) {
                            Ressource ressource = shopRessoure.getKey();
                            if(!ressource.getType().equalsIgnoreCase(type)) continue;

                            ArrayList<String> beschreibung = new ArrayList<>();
                            beschreibung.add("§7Menge: §e" + shopRessoure.getValue());
                            beschreibung.add("");

                            String[] beschreibungsArray = ressource.getBeschreibung().split("\n");
                            for(String itemBeschreibung : beschreibungsArray){
                                beschreibung.add(itemBeschreibung.trim());
                            }

                            contents.set(solt, Shopy.getInstance().createItemWithLore(ressource.getIcon(), "§9" + ressource.getName(), beschreibung));

                            /* Items Anordenen */
                            zaheler++;
                            if(zaheler == 7){
                                solt += 3;
                                zaheler = 0;
                            }else if(solt == 14 && type.equals("STANDART")){
                                solt += 5;
                                zaheler = 0;
                            }else if(solt == 14 && type.equals("DUNGEON-LOOT")){
                                solt += 5;
                                zaheler = 0;
                            }else if(solt == 20 && type.equals("STANDART")){
                                solt += 8;
                                zaheler = 0;
                            }else {
                                solt++;
                            }
                        }

                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Zum Öffnen der Kategorie hier klicken");

                        /* Menü Items bauen. Sodass diese via Verzauberung anzeigen, welches Menü aktiv ist. */
                        boolean aktivItem = false;
                        if(type.equals("STANDART")) aktivItem = true;
                        contents.set(47, Shopy.getInstance().createItemWithLore(Material.OAK_WOOD, "§e" + "Herstellungsmaterialien", beschreibung, aktivItem, false));

                        aktivItem = false;
                        if(type.equals("DUNGEON-LOOT")) aktivItem = true;
                        contents.set(48, Shopy.getInstance().createItemWithLore(Material.AMETHYST_SHARD, "§e" + "Dungoenmaterialien", beschreibung, aktivItem, false));

                        aktivItem = false;
                        if(type.equals("SPECIAL")) aktivItem = true;
                        contents.set(49, Shopy.getInstance().createItemWithLore(Material.ECHO_SHARD, "§e" + "Specialmatrialen", beschreibung, aktivItem, false));

                        aktivItem = false;
                        if(type.equals("AUFWERTER")) aktivItem = true;
                        contents.set(50, Shopy.getInstance().createItemWithLore(Material.BLUE_DYE, "§e" + "Aufwerter", beschreibung, aktivItem, false));
                    }
                    @Override
                    public void update(Player player, InventoryContents contents) {
                        init(player, contents);
                    }
                }).build(Shopy.getInstance()).open(owner);
    }
    public void kundenManger(){
        Shop shop = this;

        taskIdSpawnManger = Bukkit.getScheduler().runTaskTimer(Shopy.getInstance(), new Runnable() {
            @Override
            public void run() {
                int maxKunden = zones.size() * (2 + zusaetzlicheKundenProGrunstueck);

                double wahrscheinlichkeit = 0.55;
                if(zusaetzlicheKategorieWahrscheinlichkeit > 0) wahrscheinlichkeit += (zusaetzlicheKategorieWahrscheinlichkeit / 100);

                if(shopKunden.size() < maxKunden) {
                    if(Shopy.getInstance().isWahrscheinlichkeit(wahrscheinlichkeit)){
                        shopKunden.add(new ShopKunden(shop));

                        Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
                            Shopy.getInstance().getScoreboardManger().setScoreBoard(shop.getOwner());
                        });
                    }
                }

            }
        }, 1200L, (1200 - (20 * reduzierteKundenSpawnZeit))).getTaskId();

    }

    public void addShopZone(){
        CompletableFuture.runAsync(() -> {
            try {
                String queryUpateZones = "UPDATE shop SET shop_zones = '"+ (zones.size() + 1) +"' WHERE id = " + shopId;
                Shopy.getInstance().getMySQLConntion().query(queryUpateZones);


                String queryZones = "SELECT * FROM shop_template_zonen WHERE template = " + this.shopTemplate +" LIMIT " + (zones.size() + 1);
                ResultSet resultZones = Shopy.getInstance().getMySQLConntion().resultSet(queryZones);

                zones = new ArrayList<>();
                while(resultZones.next()){
                    Location loc1 = Shopy.getInstance().getLocationFromString(resultZones.getString("locationen_1"));
                    loc1.setY(-70);
                    loc1.setWorld(this.world);

                    Location loc2 = Shopy.getInstance().getLocationFromString(resultZones.getString("locationen_2"));
                    loc2.setY(-50);
                    loc2.setWorld(this.world);

                    Cuboid add = new Cuboid(loc1, loc2);

                    zones.add(add);
                }
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }
        });
    }
    public ShopItemKategorie schalteZufaelligeItemKategorieFrei(){
        ShopItemKategorie freischlaten = null;
        ArrayList<ShopItemKategorie> nichtFreigeschlatet = new ArrayList<>();

        for(ShopItemKategorie shopItemKategorie : getShopItemKategorie().values()){
            if(!shopItemKategorie.isFreigeschaltet()){
                nichtFreigeschlatet.add(shopItemKategorie);
            }
        }

        Random random = new Random();
        freischlaten = nichtFreigeschlatet.get(random.nextInt(nichtFreigeschlatet.size()));
        freischlaten.setFreigeschaltet(true);

        ShopItemKategorie finalFreischlaten = freischlaten;
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '1' WHERE schlussel = 'itemKategorie_"+ finalFreischlaten.itemKategorie.getName() +"_freigeschaltet'");
        });

        return freischlaten;
    }

    public void changeRessourcenLager(int newAmount){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '"+ newAmount +"' WHERE schlussel = 'ressourcen_lager'");
        });
        ressourcenLagerSize = newAmount;
    }
    public void changeItemLager(int newAmount){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '"+ newAmount +"' WHERE schlussel = 'item_lager'");
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
                    Shopy.getInstance().getMySQLConntion().query("DELETE FROM shop_item_werte WHERE item  = '" + id +"'");
                    Shopy.getInstance().getMySQLConntion().query("DELETE FROM shop_item WHERE id = '" + id +"'");
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

    public Map<String, ShopItemKategorie> getShopItemKategorie() {
        return shopItemKategorie;
    }

    public ArrayList<ShopKunden> getShopKunden() {
        return shopKunden;
    }

    public Location getShopSpawn() {
        return shopSpawn;
    }

    public int getShopTemplate() {
        return shopTemplate;
    }

    public int getShopTemplateMaxGroße() {
        return shopTemplateMaxGroße;
    }

    public int getReduzierteKundenSpawnZeit() {
        return reduzierteKundenSpawnZeit;
    }

    public int getZusaetzlicheKundenProGrunstueck() {
        return zusaetzlicheKundenProGrunstueck;
    }

    public double getZusaetzlicheKundenWahrscheinlichkeit() {
        return zusaetzlicheKundenWahrscheinlichkeit;
    }

    public double getZusaetzlichesItemWahrscheinlichkeit() {
        return zusaetzlichesItemWahrscheinlichkeit;
    }

    public double getZusaetzlicheKategorieWahrscheinlichkeit() {
        return zusaetzlicheKategorieWahrscheinlichkeit;
    }

    public double getZusaetzlicherVerkaufserlös() {
        return zusaetzlicherVerkaufserlös;
    }

    public double getReduzierteMaterialienKosten() {
        return reduzierteMaterialienKosten;
    }

    public void setReduzierteKundenSpawnZeit(int reduzierteKundenSpawnZeit) {
        this.reduzierteKundenSpawnZeit = reduzierteKundenSpawnZeit;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '"+ reduzierteKundenSpawnZeit +"' WHERE shop  = '" + shopId +"' AND schlussel = 'zusätzliche_kunden_spawn_zeit'");
        });
    }

    public void setZusaetzlicheKundenProGrunstueck(int zusaetzlicheKundenProGrunstueck) {
        this.zusaetzlicheKundenProGrunstueck = zusaetzlicheKundenProGrunstueck;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '"+ zusaetzlicheKundenProGrunstueck +"' WHERE shop  = '" + shopId +"' AND schlussel = 'zusätzliche_kunden_pro_grundstück'");
        });
    }

    public void setZusaetzlicheKundenWahrscheinlichkeit(double zusaetzlicheKundenWahrscheinlichkeit) {
        this.zusaetzlicheKundenWahrscheinlichkeit = zusaetzlicheKundenWahrscheinlichkeit;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '"+ zusaetzlicheKundenWahrscheinlichkeit +"' WHERE shop  = '" + shopId +"' AND schlussel = 'zusätzliche_kunden_wahrscheinlichkeit'");
        });
    }

    public void setZusaetzlichesItemWahrscheinlichkeit(double zusaetzlichesItemWahrscheinlichkeit) {
        this.zusaetzlichesItemWahrscheinlichkeit = zusaetzlichesItemWahrscheinlichkeit;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '"+ zusaetzlichesItemWahrscheinlichkeit +"' WHERE shop  = '" + shopId +"' AND schlussel = 'zusätzliches_item_wahrscheinlichkeit'");
        });
    }

    public void setZusaetzlicheKategorieWahrscheinlichkeit(double zusaetzlicheKategorieWahrscheinlichkeit) {
        this.zusaetzlicheKategorieWahrscheinlichkeit = zusaetzlicheKategorieWahrscheinlichkeit;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '"+ zusaetzlicheKategorieWahrscheinlichkeit +"' WHERE shop  = '" + shopId +"' AND schlussel = 'zusätzliche_kategorie_wahrscheinlichkeit'");
        });
    }

    public void setZusaetzlicherVerkaufserlös(double zusaetzlicherVerkaufserlös) {
        this.zusaetzlicherVerkaufserlös = zusaetzlicherVerkaufserlös;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '"+ zusaetzlicherVerkaufserlös +"' WHERE shop  = '" + shopId +"' AND schlussel = 'zusätzlicher_verkaufserlös'");
        });
    }

    public void setReduzierteMaterialienKosten(double reduzierteMaterialienKosten) {
        this.reduzierteMaterialienKosten = reduzierteMaterialienKosten;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '"+ reduzierteMaterialienKosten +"' WHERE shop  = '" + shopId +"' AND schlussel = 'reduzierte_materialien_kosten'");
        });
    }

}
