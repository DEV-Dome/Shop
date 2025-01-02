package de.dome.shopy.utils.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Cuboid;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.items.Item;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.items.ItemRessourecenKosten;
import de.dome.shopy.utils.items.ItemSeltenheit;
import de.dome.shopy.utils.manger.ShopInventarManger;
import de.dome.shopy.utils.shop.shophandwerksaufgabe.ShopHandwerksAufgabe;
import de.dome.shopy.utils.shop.shophandwerksaufgabe.ShopHandwerksAufgabeItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
    int taskIdHandwerksManger = -1;
    int shopTemplate;
    int shopTemplateMaxGroße;

    int reduzierteKundenSpawnZeit;
    int zusaetzlicheKundenProGrunstueck;
    double zusaetzlicheKundenWahrscheinlichkeit;
    double zusaetzlichesItemWahrscheinlichkeit;
    double zusaetzlicheKategorieWahrscheinlichkeit;
    double zusaetzlicherVerkaufserlös;
    double reduzierteMaterialienKosten;

    int erledigteHandwerksAufgaben;

    World world;
    Location shopSpawn;
    Location tresenPostion;
    ArrayList<Cuboid> zones;
    ShopRessourenManger shopRessourenManger;
    /* itemKategorieLevel auf den Shop bezogen */
    Map<String, ShopItemKategorie> shopItemKategorie;
    ArrayList<ShopItem> shopItems;
    ArrayList<ShopItemVorlage> shopItemVorlagen;
    ArrayList<ShopKunden> shopKunden;
    ArrayList<ShopHandwerksAufgabe> shopHandwerksAufgabe;
    HashMap<Location, ShopItemHalter> shopItemHalter;

    /* Manger */
    ShopInventarManger shopInventarManger;

    /* Halte fest, ob überhaupt ein Spielershop gefunden wurde  */
    private boolean loadShop = false;

    public Shop(Player owner, Boolean playerTeleport){
        this.owner = owner;
        zones = new ArrayList<>();
        shopItems = new ArrayList<>();
        shopItemVorlagen = new ArrayList<>();
        shopItemKategorie = new LinkedHashMap<>();
        shopKunden = new ArrayList<>();
        shopHandwerksAufgabe = new ArrayList<>();
        shopItemHalter = new HashMap<>();
        instance = this;
        tresenPostion = null;
        shopInventarManger = new ShopInventarManger(this);
        
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
                        String itemKategorieQuery = "SELECT * FROM shop_werte WHERE schlussel = 'itemKategorie_" + itemKategorie.getName() + "_level' AND shop = " + shopId + " LIMIT 1";
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
                        itemKategorieQuery = "SELECT * FROM shop_werte WHERE schlussel = 'itemKategorie_" + itemKategorie.getName() + "_xp'  AND shop = " + shopId + " LIMIT 1";
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
                        boolean ausgestellt = false;
                        if(shopItemHalterById(resultItemLager.getInt("ausgestellt")) != null) ausgestellt = true;

                        String queryItemLagerItemWerte = "SELECT * FROM shop_item_werte WHERE item = " + resultItemLager.getInt("sid");
                        ResultSet resultItemLagerItemWerte = Shopy.getInstance().getMySQLConntion().resultSet(queryItemLagerItemWerte);
                        while(resultItemLagerItemWerte.next()){
                            if(resultItemLagerItemWerte.getString("schlussel").equals("schaden")) schaden = Double.parseDouble(resultItemLagerItemWerte.getString("inhalt"));
                            if(resultItemLagerItemWerte.getString("schlussel").equals("angriffsgeschwindigkeit")) angriffsgeschwindigkeit = Double.parseDouble(resultItemLagerItemWerte.getString("inhalt"));
                            if(resultItemLagerItemWerte.getString("schlussel").equals("haltbarkeit")) haltbarkeit = Integer.parseInt(resultItemLagerItemWerte.getString("inhalt"));
                            if(resultItemLagerItemWerte.getString("schlussel").equals("ruestung")) rustung = Double.parseDouble(resultItemLagerItemWerte.getString("inhalt"));
                        }

                        ShopItem newItem = new ShopItem(resultItemLager.getInt("sid"), resultItemLager.getInt("iid"),ItemKategorie.getItemKategorieById(resultItemLager.getInt("item_kategorie")), resultItemLager.getString("name"), resultItemLager.getString("beschreibung"), Material.getMaterial(resultItemLager.getString("icon")), ItemSeltenheit.getItemStufeById(resultItemLager.getInt("item_seltenheit")), schaden, angriffsgeschwindigkeit, rustung, haltbarkeit, ausgestellt);
                        shopItems.add(newItem);
                    }
                }

                String queryItemHalter = "SELECT * From shop_item_halter WHERE shop = " + shopId ;
                ResultSet resultItemHalter = Shopy.getInstance().getMySQLConntion().resultSet(queryItemHalter);
                while(resultItemHalter.next()){

                    Location itemHalterLoaction = Shopy.getInstance().getLocationFromString(resultItemHalter.getString("location"));
                    ShopItem shopItem1 = null;
                    ShopItem shopItem2 = null;
                    ShopItem shopItem3 = null;
                    ShopItem shopItem4 = null;

                    for(ShopItem shopItem : shopItems){
                        if(shopItem.getId() == resultItemHalter.getInt("item_1")) shopItem1 = shopItem;
                        if(shopItem.getId() == resultItemHalter.getInt("item_2")) shopItem2 = shopItem;
                        if(shopItem.getId() == resultItemHalter.getInt("item_3")) shopItem3 = shopItem;
                        if(shopItem.getId() == resultItemHalter.getInt("item_4")) shopItem4 = shopItem;
                    }

                    ShopItemHalter newShopItemHalter = new ShopItemHalter(resultItemHalter.getInt("id"), this, resultItemHalter.getString("typ"), itemHalterLoaction, shopItem1, shopItem2, shopItem3, shopItem4);
                    shopItemHalter.put(itemHalterLoaction, newShopItemHalter);
                }


            } catch (SQLException e) { }
        });
        basisDaten.thenRun(() -> {
            if(playerTeleport) this.owner.teleport(this.shopSpawn);
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
                        if(result.getString("schlussel").equals("zusätzliche_kunden_wahrscheinlichkeit")) zusaetzlicheKundenWahrscheinlichkeit = Double.parseDouble(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("zusätzliches_item_wahrscheinlichkeit")) zusaetzlichesItemWahrscheinlichkeit = Double.parseDouble(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("zusätzliche_kategorie_wahrscheinlichkeit")) zusaetzlicheKategorieWahrscheinlichkeit = Double.parseDouble(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("zusätzlicher_verkaufserlös")) zusaetzlicherVerkaufserlös = Double.parseDouble(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("reduzierte_materialien_kosten")) reduzierteMaterialienKosten = Double.parseDouble(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("erledigte_handwerks_aufgaben")) erledigteHandwerksAufgaben = Integer.parseInt(result.getString("inhalt"));
                        if(result.getString("schlussel").equals("tresen_postion")) tresenPostion = Shopy.getInstance().getLocationFromString(result.getString("inhalt"));
                    }
                } catch (SQLException e) {
                    Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
                }

            }).thenRun(() -> {
                Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
                    Shopy.getInstance().getScoreboardManger().setScoreBoard(owner);
                    kundenManger();
                    HandwerksAufgabenManger();
                });
            });
        });
    }

    public void unLoadWorld(){
        Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
            if(taskIdSpawnManger != -1) Bukkit.getScheduler().cancelTask(taskIdSpawnManger);
            if(taskIdHandwerksManger != -1) Bukkit.getScheduler().cancelTask(taskIdHandwerksManger);

            for(ShopKunden shopKunden : shopKunden){
                shopKunden.npc.getNavigator().cancelNavigation();
                shopKunden.npc.despawn();
                shopKunden.npc.destroy();
            }

            Bukkit.unloadWorld(world, true);
        });
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
        }, (1000 - (20 * reduzierteKundenSpawnZeit)), (1200 - (20 * reduzierteKundenSpawnZeit))).getTaskId();

    }
    public void HandwerksAufgabenManger(){
        Shop shop = this;
            taskIdHandwerksManger = Bukkit.getScheduler().runTaskTimer(Shopy.getInstance(), new Runnable() {
                @Override
                public void run() {
                    ArrayList<ShopHandwerksAufgabe> newShopHandwerksAufgabe = new ArrayList<>();

                    CompletableFuture.runAsync(() -> {
                        try {
                            String queryAufgaben = "SELECT * FROM shop_handwerks_aufgaben WHERE gueltig_bis >= NOW() AND shop = " + shop.getShopId();
                            ResultSet resultAufgaben = Shopy.getInstance().getMySQLConntion().resultSet(queryAufgaben);

                            while(resultAufgaben.next()){
                                ShopHandwerksAufgabe aufgabe = new ShopHandwerksAufgabe(shop, resultAufgaben.getInt("id"), resultAufgaben.getBoolean("erledigt"));
                                aufgabe.setGueltigBis(LocalDateTime.of(resultAufgaben.getDate("gueltig_bis").toLocalDate(), resultAufgaben.getTime("gueltig_bis").toLocalTime()));

                                String queryAufgabenItems = "SELECT * FROM shop_handwerks_aufgaben_zuordnung " +
                                        "LEFT JOIN shop_handwerks_aufgaben_item ON shop_handwerks_aufgaben_zuordnung.aufgaben_item = shop_handwerks_aufgaben_item.id  " +
                                        "WHERE aufgabe = " + resultAufgaben.getInt("id");

                                ResultSet resultAufgabenItems = Shopy.getInstance().getMySQLConntion().resultSet(queryAufgabenItems);
                                while(resultAufgabenItems.next()){
                                    ShopHandwerksAufgabeItem aufgabenItem = new ShopHandwerksAufgabeItem(Item.getItemById(resultAufgabenItems.getInt("item")), resultAufgabenItems.getInt("id"), resultAufgabenItems.getInt("menge"), resultAufgabenItems.getString("belohnung"), resultAufgabenItems.getInt("belohnung_menge"), resultAufgabenItems.getInt("fortschritt"));
                                    aufgabe.getShopHandwerksAufgabeItems().add(aufgabenItem);
                                }
                                newShopHandwerksAufgabe.add(aufgabe);
                            }
                        } catch (SQLException e) {
                            Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
                        }
                    }).thenRun(() ->{
                        while(newShopHandwerksAufgabe.size() <= 2){
                            newShopHandwerksAufgabe.add(ShopHandwerksAufgabe.erstelleAufgabe(shop));
                        }

                        shopHandwerksAufgabe = newShopHandwerksAufgabe;
                    });
                }
            }, 20, 1200L).getTaskId();
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
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '1' WHERE schlussel = 'itemKategorie_"+ finalFreischlaten.itemKategorie.getName() +"_freigeschaltet' AND shop = " + shopId );
        });

        return freischlaten;
    }

    public ArrayList<ShopItemVorlage> getNichtFreigeschaltetItems(){
        ArrayList<ShopItemVorlage> nichtFreigeschaltetItems = new ArrayList<>();

        for(ShopItemVorlage shopItemVorlage : getShopItemVorlage()){
            if(!shopItemVorlage.isfreigeschaltet()) {
                if(getShopItemKategorie().get(shopItemVorlage.getItem().getItemKategorie().getName()).isFreigeschaltet()){
                    nichtFreigeschaltetItems.add(shopItemVorlage);
                }
            }
        }

        return nichtFreigeschaltetItems;
    }
    public ShopItemVorlage schalteNeuesSchriftrollenItemFrei(){
        ShopItemVorlage nichtFreigeschaltetItems = null;

        for(ShopItemVorlage shopItemVorlage : getShopItemVorlage()){
            if(!shopItemVorlage.isfreigeschaltet()) {
                if(getShopItemKategorie().get(shopItemVorlage.getItem().getItemKategorie().getName()).isFreigeschaltet()){
                    shopItemVorlage.setFreigeschaltet(true);

                    nichtFreigeschaltetItems = shopItemVorlage;
                    break;
                }
            }
        }

        return nichtFreigeschaltetItems;
    }


    public void andereRessourcenLager(int newAmount){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '"+ newAmount +"' WHERE schlussel = 'ressourcen_lager' AND shop = " + shopId);
        });
        ressourcenLagerSize = newAmount;
    }
    public void andereItemLager(int newAmount){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '"+ newAmount +"' WHERE schlussel = 'item_lager' AND shop = " + shopId);
        });
        itemLagerSize = newAmount;
    }
    public void platziereTresen(Location postion) {
        tresenPostion = postion;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('"+ shopId +"', 'tresen_postion', '"+ postion +"')");
        });
    }
    public void loscheTresen(){
        tresenPostion = null;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("DELETE FROM shop_werte WHERE schlussel = 'tresen_postion' AND shop = " + shopId);
        });
    }
    public void platziereItemHalter(Location postion, String typ){
        CompletableFuture.runAsync(() -> {
            int insertId = Shopy.getInstance().getMySQLConntion().queryReturnKey("INSERT INTO shop_item_halter (shop, typ, location) VALUES ('"+ shopId +"', '"+ typ +"','"+ postion +"' )");

            ShopItemHalter newShopItemHalter = new ShopItemHalter(insertId, this, typ, postion, null, null, null, null);
            shopItemHalter.put(postion, newShopItemHalter);
        });
    }
    public void loscheItemHalter(Location postion){
        if(shopItemHalter.containsKey(postion)){
            /* Alle Item unseten*/
            shopItemHalter.get(postion).item1.setAusgestellt(false, 0);
            shopItemHalter.get(postion).item2.setAusgestellt(false, 0);
            shopItemHalter.get(postion).item3.setAusgestellt(false, 0);
            shopItemHalter.get(postion).item4.setAusgestellt(false, 0);

            /* Löschen aus Liste*/
            shopItemHalter.remove(postion);
        }


        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("DELETE FROM shop_item_halter WHERE location = '"+ postion +"' AND shop = " + shopId);
        });
    }

    public Location getTresenPostion() {
        return tresenPostion;
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
    public ShopItemHalter shopItemHalterById(int sucheID){
        ShopItemHalter shopItemHalterGefunden = null;

        for (ShopItemHalter shopItemHalter : getShopItemHalter().values()){
            if(shopItemHalter.getId() == sucheID) shopItemHalterGefunden = shopItemHalter;
        }

        return shopItemHalterGefunden;
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

    public ArrayList<ShopHandwerksAufgabe> getShopHandwerksAufgabe() {
        return shopHandwerksAufgabe;
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

    public int getErledigteHandwerksAufgaben() {
        return erledigteHandwerksAufgaben;
    }

    public ShopInventarManger getShopInventarManger() {
        return shopInventarManger;
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

    public void setErledigteHandwerksAufgaben(int erledigteHandwerksAufgaben) {
        this.erledigteHandwerksAufgaben = erledigteHandwerksAufgaben;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET inhalt = '"+ erledigteHandwerksAufgaben +"' WHERE shop  = '" + shopId +"' AND schlussel = 'erledigte_handwerks_aufgaben'");
        });
    }

    public HashMap<Location, ShopItemHalter> getShopItemHalter() {
        return shopItemHalter;
    }
}
