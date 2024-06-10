package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import de.dome.shopy.commands.welt.LadeWeltCMD;
import de.dome.shopy.utils.shop.Shop;
import org.bukkit.*;
import org.bukkit.entity.*;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Dungeon {

    Shop shop;
    int dungeonId = -1;
    int dungeonLevel = -1;

    World world;

    Location spawn;
    Cuboid dungeonZone;
    Cuboid spawnZone;

    /* private functionen, damit der conctur klappt*/
    private Location dungeonZonePos1 = null;
    private Location dungeonZonePos2 = null;

    private Location spawnZonePos1 = null;
    private Location spawnZonePos2 = null;

    public Dungeon(Shop shop, int level) {
        this.shop = shop;
        dungeonLevel = level;

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
                            world.setDifficulty(Difficulty.NORMAL);
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

                            /* Nachricht an den Spieler senden */
                            shop.getOwner().teleport(spawn);
                            shop.getOwner().sendMessage(Shopy.getInstance().getPrefix() + "Das Ziel ist alles hier gespawnten Monster zu töten!");

                            /* Monster Spawn */
                            spawnManger();

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

    private void spawnManger(){
        ArrayList<Class> mobTypes = new ArrayList<>();
        mobTypes.add(Zombie.class);
        mobTypes.add(Skeleton.class);
        mobTypes.add(Spider.class);
        mobTypes.add(Drowned.class);

        Random random = new Random();

        /* Menge Einstellung nach level zu ordenen */
        int spawnMenge = 0;
        int minLeben = 1;
        int maxLeben = 20;

        switch (dungeonLevel){
            case 1:
                spawnMenge = 25;
                minLeben = 10;
                maxLeben = 35;
                break;
            case 2:
                spawnMenge = 50;
                minLeben = 25;
                maxLeben = 75;
                break;
            case 3:
                spawnMenge = 100;
                minLeben = 45;
                maxLeben = 150;
                break;
            case 4:
                spawnMenge = 250;
                minLeben = 75;
                maxLeben = 200;
                break;
        }

        /* Mob Spawnen */
        for (int i = 0; i <= spawnMenge; i++){
            /*Type auswählen*/
            Class vorlageMonster = mobTypes.get(random.nextInt(mobTypes.size()));

            Location spawnLocation = null;
            while (spawnLocation == null){
                spawnLocation = dungeonZone.getRandomLocation();
                if(spawnZone.contains(spawnLocation)) spawnLocation = null;
            }

            Entity spawnMonster = spawn.getWorld().spawn(spawnLocation, vorlageMonster);
            spawnMonster.setCustomNameVisible(true);
            spawnMonster.setCustomName("§9" + generiereZufälligenGengerNamen());

            /* Monster Spezifische sachen*/
            if(spawnMonster instanceof Zombie){
                Zombie spawnMonsterDetailliert = (Zombie) spawnMonster;
                spawnMonsterDetailliert.setCanPickupItems(false);
                spawnMonsterDetailliert.setMaxHealth(random.nextInt(minLeben, maxLeben));
                spawnMonsterDetailliert.setAdult();
            }
            if(spawnMonster instanceof Skeleton){
                Skeleton spawnMonsterDetailliert = (Skeleton) spawnMonster;
                spawnMonsterDetailliert.setCanPickupItems(false);
                spawnMonsterDetailliert.setMaxHealth(random.nextInt(minLeben, maxLeben));
            }
            if(spawnMonster instanceof Spider){
                Spider spawnMonsterDetailliert = (Spider) spawnMonster;
                spawnMonsterDetailliert.setCanPickupItems(false);
                spawnMonsterDetailliert.setMaxHealth(random.nextInt(minLeben, maxLeben));
            }
            if(spawnMonster instanceof Drowned){
                Drowned spawnMonsterDetailliert = (Drowned) spawnMonster;
                spawnMonsterDetailliert.setCanPickupItems(false);
                spawnMonsterDetailliert.setMaxHealth(random.nextInt(minLeben, maxLeben));
                spawnMonsterDetailliert.setAdult();
            }
        }
    }

    private String generiereZufälligenGengerNamen() {
        Random random = new Random();

        // Vornamen-Liste mit 100 Einträgen
        String[] vornamen = {
                "Ältester", "Aurin", "Banshee", "Dämon", "Echo", "Eis", "Erzengel", "Geist", "Gespenst",
                "Gischt", "Glühwürmchen", "Golem", "Gott", "Grauen", "Irrlicht", "Kobold", "Kreatur", "M Nebel",
                "Nacht", "Nekromant", "Nymphe", "Ork", "Phantom", "Plage", "Qual", "Rauch", "Schatten", "Schimäre",
                "Seelen Qual", "Seelenfänger", "Seelenfresser", "Seelenräuber", "Seelensammler", "Seelentrinker", "Seelenverkäufer",
                "Seelenzerstörer", "Skelett", "Sphäre", "Spuk", "Sumpf", "Teufel", "Todesbringer", "Todesschatten", "Trauma",
                "Unheil", "Unhold", "Vampir", "Wahn", "Wesen", "Windgeplagter", "Wölfin", "Wurm", "Zorn", "Zombie", "Steven"
        };

        // Adjektiv-Liste mit 20 Einträgen
        String[] adjektive = {
                "Alter", "Böses", "Dunkeler", "Eisiger", "Einsamer", "Erbarmungsloser", "Furchtbarer", "Geisterhafter", "Grausiger", "Heimtückischer",
                "Leerer", "Mystischer", "Nebliger", "Schattenhafter", "Schrecklicher", "Schwacher", "Stummer", "Unheimlicher", "Verfluchter", "Zerbrechlicher"
        };

        // Zufällige Auswahl von Vor- und Nachname
        String vorname = vornamen[random.nextInt(vornamen.length)];
        String adjektiv = adjektive[random.nextInt(adjektive.length)];

        // Kombinieren von Vor- und Nachname zu einem vollständigen Namen
        return adjektiv + " " + vorname;
    }
}
