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

    public Shop(UUID owner){
        zones = new ArrayList<>();

        try {
            String query = "SELECT * FROM shop WHERE owner = '" + owner + "' LIMIT 1";
            ResultSet result = Shopy.getInstance().getMySQLConntion().resultSet(query);

            if (result.next()){
                this.shopId = result.getInt("id");
                this.owner = Bukkit.getPlayer(result.getString("owner"));
                this.level = result.getInt("shop_level");
                String world = Shopy.getInstance().getDataFolder().getPath() + "/shop_welten/"  + result.getString("shop_ordner");

                Shopy.getInstance().getSpielerShops().put(owner, this);
                loadWorld(world);
                CompletableFuture.runAsync(() -> {
                    /* Welt Zonen Laden */
                    try {
                        String queryZones = "SELECT * FROM shop_template_zonen WHERE template = " + result.getInt("template") +" LIMIT " + result.getInt("shop_zones");

                        Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + queryZones);
                        ResultSet resultZones = Shopy.getInstance().getMySQLConntion().resultSet(queryZones);
                        while(resultZones.next()){
                            Location loc1 = getLocationFromString(resultZones.getString("locationen_1"));
                            loc1.setY(-80);

                            Location loc2 = getLocationFromString(resultZones.getString("locationen_2"));
                            loc2.setY(150);

                            zones.add(new Cuboid(loc1, loc2));
                            Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "Zone geladen");
                        }
                    } catch (SQLException e) {
                        Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + e.getMessage());
                    }
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

    private Location getLocationFromString(String locationString) {
        // Entferne unnötige Zeichen, um nur die Koordinaten zu behalten
        locationString = locationString.replace("Location{", "").replace("}", "");

        String[] parts = locationString.split(",");
        String worldName = parts[0].split("=")[1]; // Extrahiere den Weltname

        double x = Double.parseDouble(parts[1].split("=")[1]);
        double y = Double.parseDouble(parts[2].split("=")[1]);
        double z = Double.parseDouble(parts[3].split("=")[1]);

        // Erstelle die World-Instanz anhand des Welt-Namens
        World world = Bukkit.getWorld(worldName);

        if (world != null) {
            return new Location(world, x, y, z);
        } else {
            // Fehlerbehandlung für nicht gefundene Welt
            return null;
        }
    }
}
