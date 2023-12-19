package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Shop {

    int shop_id;
    Player owner;
    int level;
    World world;

    public Shop(UUID owner){

        try {
            String query = "SELECT * FROM shop WHERE owner = '" + owner + "' LIMIT 1";
            ResultSet result = Shopy.getInstance().getMySQLConntion().resultSet(query);

            if (result.next()){
                this.shop_id = result.getInt("id");
                this.owner = Bukkit.getPlayer(result.getString("owner"));
                this.level = result.getInt("shop_level");
                String world = Shopy.getInstance().getDataFolder().getPath() + "/shop_welten/"  + result.getString("shop_ordner");

                Shopy.getInstance().getSpielerShops().put(owner, this);
                Shopy.getInstance().getServer().getScheduler().runTask(Shopy.getInstance(), new Runnable() {
                    @Override
                    public void run() {

                        loadWorld(world);
                    }
                });
            }else {
               // Shopy.getInstance().getSpielerShops().remove(owner);
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

    public int getShop_id() {
        return shop_id;
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

}
