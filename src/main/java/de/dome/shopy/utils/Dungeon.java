package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.shop.Shop;
import dev.sergiferry.playernpc.api.NPC;
import dev.sergiferry.playernpc.api.NPCLib;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class Dungeon {

    private Shop shop;
    private int dungeonId = -1;
    private int dungeonLevel = -1;

    private World world;

    private Location spawn;
    private Cuboid dungeonZone;
    private Cuboid spawnZone;

    /* private functionen, damit der conctur klappt*/
    private Location dungeonZonePos1 = null;
    private Location dungeonZonePos2 = null;

    private Location spawnZonePos1 = null;
    private Location spawnZonePos2 = null;

    private boolean endZoneSpawing = false;
    private Location waffenlagerPosition = null;

    private ArrayList<Entity> dungeonEntitys;
    private HashMap<Ressource, Integer> dungeonLoot;

    private boolean schutzPhase = true;
    private NPC.Global waffenkammerNPC = null;
    private ItemStack[] spielerInventrar;

    public Dungeon(Shop shop, int level) {
        this.shop = shop;
        this.dungeonLevel = level;
        this.dungeonEntitys = new ArrayList<>();
        this.dungeonLoot = new HashMap<>();

         CompletableFuture.runAsync(() -> {

            try {
                /* Zufälligen Dungeon in der DB auswählen*/
                String queryDungeon = "SELECT * FROM dungeon ORDER BY RAND() LIMIT 1";
                ResultSet resultDungeon = Shopy.getInstance().getMySQLConntion().resultSet(queryDungeon);

                if (resultDungeon.next()){
                    /* Dungeon Laden */
                    dungeonId = resultDungeon.getInt("id");

                    String weltName = resultDungeon.getString("dungeon_ordner");

                    File von = new File(Shopy.getInstance().getDataFolder().getPath() + "/image/" + weltName);
                    File zu = new File(Shopy.getInstance().getDataFolder().getPath() + "/temp_welten/_" + shop.getShopId() +"_"  + weltName);
                    Shopy.getInstance().kopiereOrdner(von, zu);

                    /* Dungeon werte aus datenbank laden */
                    String queryDungeonWerte = "SELECT * FROM dungeon_positionen WHERE dungeon = '" + dungeonId + "'";
                    ResultSet resultDungeonWerte = Shopy.getInstance().getMySQLConntion().resultSet(queryDungeonWerte);

                    while(resultDungeonWerte.next()){
                        if(resultDungeonWerte.getString("wert").equalsIgnoreCase("spawn")) spawn = Shopy.getInstance().getLocationFromString(resultDungeonWerte.getString("value"));
                        if(resultDungeonWerte.getString("wert").equalsIgnoreCase("waffenlager")) waffenlagerPosition = Shopy.getInstance().getLocationFromString(resultDungeonWerte.getString("value"));

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
                            spawnZonePos1.setY(0);

                            spawnZonePos2.setWorld(world);
                            spawnZonePos2.add(0, 100, 0);

                            spawn.setWorld(world);
                            waffenlagerPosition.setWorld(world);

                            /* Zonen erstellen */
                            dungeonZone = new Cuboid(dungeonZonePos1, dungeonZonePos2);
                            spawnZone = new Cuboid(spawnZonePos1, spawnZonePos2);

                            /* Nachricht an den Spieler senden & zum Spawn Teleportiren */
                            shop.getOwner().teleport(spawn);
                            shop.getOwner().sendMessage(Shopy.getInstance().getPrefix() + "Das Ziel ist es, alle hier gespawnten Monster zu töten!");

                            spielerInventrar = shop.getOwner().getInventory().getContents();
                            shop.getOwner().getInventory().clear();

                            /* NPC Spawn (Waffenlager) */
                            ArrayList<String> npcText = new ArrayList<>();
                            npcText.add("§9Waffenlager");
                            npcText.add("§7Rüste eine deiner hergestellten Waffen aus");
                            String npcId = "npc-waffenlager-d-" + shop.getOwner().getUniqueId() + "-" + System.currentTimeMillis();

                            waffenkammerNPC = NPCLib.getInstance().generateGlobalNPC(Shopy.getInstance(), npcId, waffenlagerPosition);
                            waffenkammerNPC.setText(npcText);
                            waffenkammerNPC.setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY3ODM1NjAzMjY1MiwKICAicHJvZmlsZUlkIiA6ICI3ZDJhY2YzOGQ3YTQ0YjU0YTliMGNkYTZhNzk1YmNmYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb3VuY2luZXNzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E4MDI4YTVhNDI3MzdhOGVmNTNkMWZjMzQxZDU0YTVjNWJjN2EzNWZiNTk1Yjg0N2M1MDk3YzQ0M2FlNWI3OGMiCiAgICB9CiAgfQp9", "cK3OamDNeexBjQmBMKGy88j50zgeE82Y0+J4GYJgf7J2BYJ2TM2CaAgft08GWKW9YemEfqlznRhqk/eKtwPfpLffD0iNj2q9ZjdlEDfqDz1458eydT2dPIXh7jtU2xc0MCR+U5iySd7j/BNpGlg0nNC1wEWwcetyx8kx4R9sS8jJugvdMyHqHllDPkggiDIt81m0Uh8lDLjHYL9OdzWOHR7uykB/LcnStEnUh0kR7rqyKNKtgs6aGRQudbL2FJm9mUTga+mCkOnaXU/+BE57K8jxP/x50SzO43QiNrTuSCIJeBEW6EtZ5NlRbFY8rhsRcCzDDanTtkMvTH+Ss2fm2ftVfA9/2xf46/yFyWjp1cmwNqayeMFkEOBhZ5LuVEVmW9M427+ajogIEsyPXqfI9w2ePWp7IZ6LSfmWvArYfbw19ZDAfQSdz8u2G6hICCOcliwq7bNsFTCkzdCQxQIXgB7FNrwJevtU134zSFBbMCMzaMmpqeu6sSMAza+zIMcYDgZ1UY00LUsdqMsDHgGdr6CHnC1eZtBvNmloow4s/q9+7mBBBeg4X+9aRwW4UzdfJXdQoyARz1epJ04OvNJbfV8D2u1ME3EBfz4yl6MbAh+eJBtG/XZf2IeVk/41U1CN7fdILJ6KjmtQodf8OzUwzZUVz/CS1axqOE7r/1fAyEs=");
                            waffenkammerNPC.lookAt(spawn);
                            waffenkammerNPC.createAllPlayers();
                            waffenkammerNPC.update();
                            waffenkammerNPC.show();

                            /* Schutzphase bennden */
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    cancel();
                                    schutzPhase = false;
                                }
                            }.runTaskTimer(Shopy.getInstance(), 200L, 200L);

                            /* Monster Spawn */
                            spawnManger();

                            /* Scoreboard Updateten */
                             Shopy.getInstance().getScoreboardManger().setScoreBoard(shop.getOwner());

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
    public void DungeonAbschlissen(){
        Player p = shop.getOwner();
        ArrayList<Ressource> abschlussLoot = Shopy.getInstance().getDropManger().dropAbschlussLoot(this);

        p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast es geschafft den Dungeon zu säubern. Dabei hast du folgenden Loot Erhalten: ");
        for (Map.Entry<Ressource, Integer> entry : dungeonLoot.entrySet()) {
            p.sendMessage(Shopy.getInstance().getPrefix() + "§e" + entry.getValue() + "x " + entry.getKey().getName());
        }

        String abschlussLootString = "";
        for (Ressource res : abschlussLoot) {
            if(abschlussLootString.equals("")){
                abschlussLootString += res.getName();
            }else {
                abschlussLootString += ", " + res.getName();
            }
        }

        p.sendMessage("");
        p.sendMessage(Shopy.getInstance().getPrefix() + "Zusätzlich bekommst du, zur Belohnung folge des geschenkt: §a" + abschlussLootString);

        p.sendMessage("");
        p.sendMessage("");

        if(Shopy.getInstance().getServerSpawn() != null){
            if (!Shopy.getInstance().getPlayersNotTeleport().contains(p)) {
                new BukkitRunnable() {
                    int countdownTime = 3;
                    @Override
                    public void run() {
                        if (countdownTime <= 0) {
                            cancel();
                            p.teleport(Shopy.getInstance().getServerSpawn());
                            p.sendMessage(Shopy.getInstance().getPrefix() + "Du wurdest zum Spawn Teleporiert. ");
                            if(Shopy.getInstance().getPlayersNotTeleport().contains(p)) Shopy.getInstance().getPlayersNotTeleport().remove(p);

                            if(Shopy.getInstance().getGeladeneTempWelten().containsKey(p.getUniqueId())){
                                for(World world : Shopy.getInstance().getGeladeneTempWelten().get(p.getUniqueId())){
                                    File file = world.getWorldFolder();
                                    Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
                                        Bukkit.unloadWorld(world, true);

                                        Shopy.getInstance().rekursivLoeschen(file);
                                    });
                                }
                                Shopy.getInstance().getGeladeneTempWelten().remove(p.getUniqueId());
                            }

                            if(Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
                                Shopy.getInstance().getSpielerDungeon().remove(p.getUniqueId());

                                p.getInventory().clear();
                                for(ItemStack item : spielerInventrar){
                                    p.getInventory().addItem(item);
                                }

                                Shopy.getInstance().getScoreboardManger().setScoreBoard(shop.getOwner());
                            }
                        } else {
                            p.sendMessage(Shopy.getInstance().getPrefix() + "Du wirst in " + countdownTime + " Sekunden zum Spawn Teleporiert.");
                            countdownTime--;
                        }
                    }
                }.runTaskTimer(Shopy.getInstance(), 0L, 20L);
            }
        }
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
                minLeben = 15;
                maxLeben = 35;
                break;
            case 2:
                spawnMenge = 50;
                minLeben = 40;
                maxLeben = 50;

                /* zusätzliche Mob Typen Anpassen */
                mobTypes.add(Vindicator.class);
                mobTypes.remove(Zombie.class);
                break;
            case 3:
                spawnMenge = 100;
                minLeben = 70;
                maxLeben = 100;

                /* zusätzliche Mob Typen Anpassen */
                mobTypes.add(Vindicator.class);
                mobTypes.add(Evoker.class);

                mobTypes.remove(Zombie.class);
                mobTypes.remove(Spider.class);
                break;
            case 4:
                spawnMenge = 200;
                minLeben = 100;
                maxLeben = 125;

                /*  Mob Typen Anpassen */
                mobTypes.add(Vindicator.class);
                mobTypes.add(Blaze.class);
                mobTypes.add(Evoker.class);

                mobTypes.remove(Zombie.class);
                mobTypes.remove(Skeleton.class);
                mobTypes.remove(Spider.class);
                break;
        }

        /* Mob Spawnen */
        for (int i = 1; i <= spawnMenge; i++){
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

            dungeonEntitys.add(spawnMonster);

            int monsterleben = minLeben + random.nextInt(maxLeben - minLeben + 1);

            /* Monster Spezifische sachen*/
            if(spawnMonster instanceof Zombie){
                Zombie spawnMonsterDetailliert = (Zombie) spawnMonster;
                spawnMonsterDetailliert.setCanPickupItems(false);
                spawnMonsterDetailliert.setMaxHealth(monsterleben);
                spawnMonsterDetailliert.setHealth(monsterleben);
                spawnMonsterDetailliert.setAdult();
                spawnMonsterDetailliert.setJumping(false);
            }
            if(spawnMonster instanceof Skeleton){
                Skeleton spawnMonsterDetailliert = (Skeleton) spawnMonster;
                spawnMonsterDetailliert.setCanPickupItems(false);
                spawnMonsterDetailliert.setMaxHealth(monsterleben);
                spawnMonsterDetailliert.setHealth(monsterleben);
                spawnMonsterDetailliert.setJumping(false);
            }
            if(spawnMonster instanceof Spider){
                Spider spawnMonsterDetailliert = (Spider) spawnMonster;
                spawnMonsterDetailliert.setCanPickupItems(false);
                spawnMonsterDetailliert.setMaxHealth(monsterleben);
                spawnMonsterDetailliert.setHealth(monsterleben);
                spawnMonsterDetailliert.setJumping(false);
            }
            if(spawnMonster instanceof Drowned){
                Drowned spawnMonsterDetailliert = (Drowned) spawnMonster;
                spawnMonsterDetailliert.setCanPickupItems(false);
                spawnMonsterDetailliert.setMaxHealth(monsterleben);
                spawnMonsterDetailliert.setHealth(monsterleben);
                spawnMonsterDetailliert.setAdult();
                spawnMonsterDetailliert.setJumping(false);
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

    public Shop getShop() {
        return shop;
    }

    public int getDungeonId() {
        return dungeonId;
    }

    public int getDungeonLevel() {
        return dungeonLevel;
    }

    public World getWorld() {
        return world;
    }

    public Location getSpawn() {
        return spawn;
    }

    public Cuboid getDungeonZone() {
        return dungeonZone;
    }

    public Cuboid getSpawnZone() {
        return spawnZone;
    }

    public Location getDungeonZonePos1() {
        return dungeonZonePos1;
    }

    public Location getDungeonZonePos2() {
        return dungeonZonePos2;
    }

    public Location getSpawnZonePos1() {
        return spawnZonePos1;
    }

    public Location getSpawnZonePos2() {
        return spawnZonePos2;
    }

    public ArrayList<Entity> getDungeonEntitys() {
        return dungeonEntitys;
    }

    public boolean isEndZoneSpawing() {
        return endZoneSpawing;
    }

    public void setEndZoneSpawing(boolean endZoneSpawing) {
        this.endZoneSpawing = endZoneSpawing;
    }

    public HashMap<Ressource, Integer> getDungeonLoot() {
        return dungeonLoot;
    }

    public Location getWaffenlagerPosition() {
        return waffenlagerPosition;
    }

    public NPC.Global getWaffenkammerNPC() {
        return waffenkammerNPC;
    }

    public ItemStack[] getSpielerInventrar() {
        return spielerInventrar;
    }

    public int getLebendeGegner(){
        int dungeonEntityLebendig = 0;

        for(Entity dungeonEntity : dungeonEntitys) {
            if (!dungeonEntity.isDead()) dungeonEntityLebendig++;
        }

        return dungeonEntityLebendig;
    }

    public boolean isSchutzPhase() {
        return schutzPhase;
    }

    public int getErhaltendenLoot(){
        int loot = 0;

        for (int lootAnzahl : dungeonLoot.values()){
            loot += lootAnzahl;
        }

        return loot;
    }

}
