package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class Shop {

    int shopId;
    Player owner;
    int level;
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
        });
    }

    public void unLoadWorld(){
        Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
            Bukkit.unloadWorld(world, true);
        });
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
