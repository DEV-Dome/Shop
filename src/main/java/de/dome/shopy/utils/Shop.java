package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class Shop {

    int shopId;
    Player owner;
    int level;
    int ressourcenLager;
    int itemLager;
    World world;
    ArrayList<Cuboid> zones;
    RessourenShopManger ressourenShopManger;
    /* Halte fest, ob überhaupt ein Spielershop gefunden wurde  */
    private boolean loadShop = false;

    public Shop(UUID ownerUUID, Boolean playerTeleport){
        zones = new ArrayList<>();

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
                            this.owner.teleport(this.world.getSpawnLocation());
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

                    Shopy.getInstance().getSpielerShops().put(ownerUUID, this);
                }

            } catch (SQLException e) { }
        });
        basisDaten.thenRun(() -> {
            if(loadShop){
                this.ressourenShopManger = new RessourenShopManger(this);
            }
            CompletableFuture.runAsync(() -> {
                String query = "SELECT * FROM shop_werte WHERE shop = '" + shopId + "'";
                ResultSet result = Shopy.getInstance().getMySQLConntion().resultSet(query);

                /* Shop werte daten aus datenbank laden*/
                try {
                    while (result.next()){
                        if(result.getString("wert").equals("ressourcen_lager")) ressourcenLager = Integer.parseInt(result.getString("value"));
                        if(result.getString("wert").equals("item_lager")) itemLager = Integer.parseInt(result.getString("value"));
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
                    beschreibung.add("§6(Du hast " + ressourenShopManger.getRessourceValue(Ressoure.getRessoureByName("geld")) + " €)");
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
        }).build(Shopy.getInstance()).open(owner);
    }
    public void changeRessourcenLager(int newAmount){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET value = '"+ newAmount +"' WHERE wert = 'ressourcen_lager'");
        });
        ressourcenLager = newAmount;
    }
    public void changeItemLager(int newAmount){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET value = '"+ newAmount +"' WHERE wert = 'item_lager'");
        });
        itemLager = newAmount;
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

    public RessourenShopManger getRessourenShopManger() {
        return ressourenShopManger;
    }

    public int getRessourcenLager() {
        return ressourcenLager;
    }

    public int getItemLager() {
        return itemLager;
    }

    //Location{world=CraftWorld{name=plugins/Shopy/shop_welten/sps_d202f2c8-d4e7-4cc8-94e5-285f3d026a6f_1},x=0.0,y=-60.0,z=-17.0,pitch=0.0,yaw=0.0}
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
