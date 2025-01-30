package de.dome.shopy.listener.dungeon;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Cuboid;
import de.dome.shopy.utils.Dungeon;
import de.dome.shopy.utils.Ressource;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import org.bukkit.scheduler.BukkitRunnable;


import java.util.ArrayList;


public class EntityDeathListener implements Listener {

    public EntityDeathListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void entityDeath(EntityDeathEvent e) {
        if(e.getEntity().getKiller() instanceof Player && Shopy.getInstance().getSpielerDungeon().containsKey(e.getEntity().getKiller().getUniqueId())){
            Player p = e.getEntity().getKiller();
            Dungeon spielerDungeon = Shopy.getInstance().getSpielerDungeon().get(p.getUniqueId());

            /* Set bonus */
            if(spielerDungeon.getDungeonSetAusgeruestet().containsKey("Low Live") && spielerDungeon.getDungeonSetAusgeruestet().get("Low Live").isBonus2()){
                p.setHealth(20);
            }

            /* Spieler Loot ausgeben */
            if(spielerDungeon.getDungeonEntitys().contains(e.getEntity())){
                ArrayList<Ressource> gedroppterLoot = null;
                if(e.getEntity().getType() == EntityType.ZOMBIE){
                    String[] mobLoot = {"Zombiesleim", "Zombiedreck"};
                    gedroppterLoot = Shopy.getInstance().getDropManger().dropMobLoot(spielerDungeon, mobLoot);
                }

                if(e.getEntity().getType() == EntityType.SKELETON){
                    String[] mobLoot = {"Skelett Arm", "Pfeilreste"};
                    gedroppterLoot = Shopy.getInstance().getDropManger().dropMobLoot(spielerDungeon, mobLoot);
                }

                if(e.getEntity().getType() == EntityType.SPIDER){
                    String[] mobLoot = {"Spinnenauge", "Hasenfell"};
                    gedroppterLoot = Shopy.getInstance().getDropManger().dropMobLoot(spielerDungeon, mobLoot);
                }
                if(e.getEntity().getType() == EntityType.DROWNED){
                    String[] mobLoot = {"Stab eines Dreistacks", "Sauerstoffkristall"};
                    gedroppterLoot = Shopy.getInstance().getDropManger().dropMobLoot(spielerDungeon, mobLoot);
                }
                if(e.getEntity().getType() == EntityType.VINDICATOR){
                    String[] mobLoot = {"Ei", "Tonklumpen"};
                    gedroppterLoot = Shopy.getInstance().getDropManger().dropMobLoot(spielerDungeon, mobLoot);
                }
                if(e.getEntity().getType() == EntityType.BLAZE){
                    String[] mobLoot = {"Feuerstab", "Verbranntest Eisen"};
                    gedroppterLoot = Shopy.getInstance().getDropManger().dropMobLoot(spielerDungeon, mobLoot);
                }
                if(e.getEntity().getType() == EntityType.EVOKER){
                    String[] mobLoot = {"Zauberstaub", "Drachenatem"};
                    gedroppterLoot = Shopy.getInstance().getDropManger().dropMobLoot(spielerDungeon, mobLoot);
                }

                /* Urspünglichen Monster Loot unterbinden*/
                e.setDroppedExp(0);
                e.getDrops().clear();

                /* Scoreboard Aktualisieren */
                Shopy.getInstance().getScoreboardManger().setScoreBoard(p);

                /* Hologram erzeugen, was anzeigent welcher Loot gefallen ist, */
//                Hologram hologram = Shopy.getInstance().getHolographicDisplaysAPI().createHologram(e.getEntity().getLocation().add(0,2,0));
//                hologram.getLines().appendText("§7Fallen gelassen:");
//                if(!gedroppterLoot.isEmpty()){
//                    hologram.getLines().appendText("");
//                    for (Ressource item : gedroppterLoot) {
//                        hologram.getLines().appendText("§e1x " + item.getName());
//                    }
//                }else hologram.delete();


                /* Sicherstellen das alle Toten Monster auch tot sind */
                /* Wenn es nur noch 5 Gegener gibt, aller Monster zum Spieler Teleportiren */

                if(spielerDungeon.getLebendeGegner() <= 5){
                    if(!spielerDungeon.isEndZoneSpawing()){
                        p.sendMessage(Shopy.getInstance().getPrefix() + "Es leben nur noch " +  spielerDungeon.getLebendeGegner() + " Monster. Dieser haben sich zu dir Teleportiert um sich zu rächen.");

                        /*Zone um den Spieler rum bauen (3 x 3)*/
                        Cuboid mobSpawnEndZone = new Cuboid(p.getLocation().add(3,0,2), p.getLocation().add(-3,0,-2));

                        for(Entity dungeonEntity : spielerDungeon.getDungeonEntitys()) {
                            Location teleportTo = mobSpawnEndZone.getRandomLocation();
                            teleportTo.setY(p.getY());

                            dungeonEntity.teleport(teleportTo);
                        }

                        spielerDungeon.setEndZoneSpawing(true);
                    }
                }

                /* Prüfe on der Dungeon abgeschlossen ist */
                if(spielerDungeon.getLebendeGegner() == 0){
                    spielerDungeon.DungeonAbschlissen();
                }

                /* Hologram nach 3 Sekunden entfernen */
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        cancel();
//                        hologram.delete();
                    }
                }.runTaskTimer(Shopy.getInstance(), 60L, 60L);
            }
        }else {
            for (Dungeon dungeon : Shopy.getInstance().getSpielerDungeon().values()) {
                if(dungeon.getDungeonEntitys().contains(e.getEntity())){
                    Shopy.getInstance().getScoreboardManger().setScoreBoard(dungeon.getShop().getOwner());
                    e.setDroppedExp(0);
                    e.getDrops().clear();

                    if(dungeon.getLebendeGegner() == 0){
                        dungeon.DungeonAbschlissen();
                    }
                    break;
                }
            }
        }
    }

}
