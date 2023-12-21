package de.dome.shopy.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

    public Shop(UUID ownerUUID){
        zones = new ArrayList<>();

        try {
            String query = "SELECT * FROM shop WHERE owner = '" + ownerUUID + "' LIMIT 1";
            ResultSet result = Shopy.getInstance().getMySQLConntion().resultSet(query);

            if (result.next()){
                this.shopId = result.getInt("id");
                this.owner = Bukkit.getPlayer(UUID.fromString(result.getString("owner")));
                this.level = result.getInt("shop_level");
                String world = Shopy.getInstance().getDataFolder().getPath() + "/shop_welten/"  + result.getString("shop_ordner");

                this.owner.sendMessage(Shopy.getInstance().getPrefix() + "TEST!");
                Shopy.getInstance().getSpielerShops().put(ownerUUID, this);
                loadWorld(world);
                CompletableFuture.runAsync(() -> {
                    Location loc1 = null;
                    Location loc2 = null;
                    /* Welt Zonen Laden */
                    try {
                        this.owner.sendMessage(Shopy.getInstance().getPrefix() + "TEST! 2");
                        String queryZones = "SELECT * FROM shop_template_zonen WHERE template = " + result.getInt("template") +" LIMIT " + result.getInt("shop_zones");

                        ResultSet resultZones = Shopy.getInstance().getMySQLConntion().resultSet(queryZones);
                        this.owner.sendMessage(Shopy.getInstance().getPrefix() + "TEST! 3");
                        while(resultZones.next()){
                            this.owner.sendMessage(Shopy.getInstance().getPrefix() + "TEST! 4");
                             loc1 = getLocationFromString(resultZones.getString("locationen_1"));
                            loc1.setY(-80);

                             loc2 = getLocationFromString(resultZones.getString("locationen_2"));
                            loc2.setY(150);
                        }
                        this.owner.sendMessage(Shopy.getInstance().getPrefix() + "TEST! 5");
                    } catch (SQLException e) {
                        Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + e.getMessage());
                    }
                    this.owner.sendMessage(Shopy.getInstance().getPrefix() + "TEST! 6");
                    Cuboid add = new Cuboid(loc1, loc2);
                    zones.add(add);

                    this.owner.sendMessage(Shopy.getInstance().getPrefix() + "Arraylist §e" + zones.size() + "");
                    this.owner.sendMessage(Shopy.getInstance().getPrefix() + "Zone mit der Mitte §e" + add.getCenter() + " §7Hinzugefügt");
                });

            }
        } catch (SQLException e) { }

    }

    public void loadWorld(String world){
        WorldCreator creator = new WorldCreator(world);

        creator.environment(World.Environment.NORMAL);
        creator.type(WorldType.NORMAL);

        this.world = creator.createWorld();
    }

    public void unLoadWorld(){
        Bukkit.unloadWorld(world, true);
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

    //Location{world=CraftWorld{name=plugins/Shopy/shop_welten/sps_d202f2c8-d4e7-4cc8-94e5-285f3d026a6f_1},x=0.0,y=-60.0,z=-17.0,pitch=0.0,yaw=0.0}
    private Location getLocationFromString(String locationString) {
        Location ret = null;

        String[] dataArray = locationString.split(",");

        String worldname =      dataArray[0].split("name=")[1];
        worldname.substring(0, worldname.length() - 1);
        World world =           Bukkit.getWorld(worldname);

        double x =      Double.parseDouble(dataArray[1].split("=")[1]);
        double y =      Double.parseDouble(dataArray[2].split("=")[1]);
        double z =      Double.parseDouble(dataArray[3].split("=")[1]);
        float pitch =   Float.parseFloat(dataArray[3].split("=")[1]);
        float yaw =     Float.parseFloat(dataArray[3].split("=")[1]);

        ret = new Location(world, x,y,z,yaw,pitch);

        return ret;
    }
}
