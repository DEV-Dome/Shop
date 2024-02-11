package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import de.dome.shopy.commands.welt.LadeWeltCMD;
import de.dome.shopy.utils.shop.Shop;
import org.bukkit.*;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Dungeon {

    Shop shop;
    int dungeonId = -1;

    World world;

    Location spawn;
    Cuboid dungeonZone;
    Cuboid spawnZone;

    /* private functionen, damit der conctur klappt*/
    private Location dungeonZonePos1 = null;
    private Location dungeonZonePos2 = null;

    private Location spawnZonePos1 = null;
    private Location spawnZonePos2 = null;

    public Dungeon(Shop shop) {
        this.shop = shop;

         CompletableFuture.runAsync(() -> {

            try {
                String queryDungeon = "SELECT * FROM dungeon ORDER BY RAND() LIMIT 1";
                ResultSet resultDungeon = Shopy.getInstance().getMySQLConntion().resultSet(queryDungeon);

                if (resultDungeon.next()){
                    dungeonId = resultDungeon.getInt("id");

                    String weltName = resultDungeon.getString("dungeon_ordner");

                    File von = new File(Shopy.getInstance().getDataFolder().getPath() + "/image/" + weltName);
                    File zu = new File(Shopy.getInstance().getDataFolder().getPath() + "/temp_welten/_" + shop.getShopId() +"_"  + weltName);
                    Shopy.getInstance().kopiereOrdner(von, zu);

                    String queryDungeonWerte = "SELECT * FROM dungeon_positionen WHERE dungeon = '" + dungeonId + "'";
                    ResultSet resultDungeonWerte = Shopy.getInstance().getMySQLConntion().resultSet(queryDungeonWerte);

                    while(resultDungeonWerte.next()){
                        if(resultDungeonWerte.getString("wert").equalsIgnoreCase("spawn")) spawn = Shopy.getInstance().getLocationFromString(resultDungeonWerte.getString("value"));

                        if(resultDungeonWerte.getString("wert").equalsIgnoreCase("dungeonzone.pos1")) dungeonZonePos1 = Shopy.getInstance().getLocationFromString(resultDungeonWerte.getString("value"));
                        if(resultDungeonWerte.getString("wert").equalsIgnoreCase("dungeonzone.pos2")) dungeonZonePos2 = Shopy.getInstance().getLocationFromString(resultDungeonWerte.getString("value"));

                        if(resultDungeonWerte.getString("wert").equalsIgnoreCase("spawnzone.pos1")) spawnZonePos1 = Shopy.getInstance().getLocationFromString(resultDungeonWerte.getString("value"));
                        if(resultDungeonWerte.getString("wert").equalsIgnoreCase("spawnzone.pos2")) spawnZonePos2 = Shopy.getInstance().getLocationFromString(resultDungeonWerte.getString("value"));
                    }


                    Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
                            /* Welt erstellen */
                            WorldCreator creator = new WorldCreator(Shopy.getInstance().getDataFolder().getPath() + "/temp_welten/_" + shop.getShopId() + "_"  + weltName);

                            creator.environment(World.Environment.NORMAL);
                            creator.type(WorldType.NORMAL);

                            world = creator.createWorld();
                            world.setTime(0);
                            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

                            /* Richtige welte setzten*/
                            dungeonZonePos1.setWorld(world);
                            dungeonZonePos2.setWorld(world);

                            spawnZonePos1.setWorld(world);
                            spawnZonePos2.setWorld(world);

                            spawn.setWorld(world);

                            /* Zonen erstellen */
                            dungeonZone = new Cuboid(dungeonZonePos1, dungeonZonePos2);
                            spawnZone = new Cuboid(spawnZonePos1, spawnZonePos2);

                            /* Nachricht an DSpieler senden */
                            shop.getOwner().teleport(spawn);
                            shop.getOwner().sendMessage(Shopy.getInstance().getPrefix() + "Das Ziel ist alles hier gespawnten Monster zu töten!");

                            /* Welt als Tempör makieren */
                            if(Shopy.getInstance().getGeladeneTempWelten().containsKey(shop.getOwner().getUniqueId())){
                                Shopy.getInstance().getGeladeneTempWelten().get(shop.getOwner().getUniqueId()).add(world);
                            }else {
                                ArrayList<World> worlds = new ArrayList<>();
                                worlds.add(world);

                                Shopy.getInstance().getGeladeneTempWelten().put(shop.getOwner().getUniqueId(), worlds);
                            }
                    });
                }
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
