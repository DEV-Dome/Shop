package de.dome.shopy.listener.dungeon;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Dungeon;
import de.dome.shopy.utils.Ressoure;
import de.dome.shopy.utils.shop.ShopRessourenManger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class EntityDeathListener implements Listener {

    public EntityDeathListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void entityDeath(EntityDeathEvent e) {
        if(e.getEntity().getKiller() instanceof Player && Shopy.getInstance().getSpielerDungeon().containsKey(e.getEntity().getKiller().getUniqueId())){
            Player p = e.getEntity().getKiller();
            Dungeon spielerDungeon = Shopy.getInstance().getSpielerDungeon().get(p.getUniqueId());

            /* Spieler Loot ausgeben */
            if(spielerDungeon.getDungeonEntitys().contains(e.getEntity())){
                if(e.getEntity().getType() == EntityType.ZOMBIE){
                    Ressoure ressource = Ressoure.getRessoureByName("Monsterhaut");
                    if(ressource != null){
                        ShopRessourenManger shopRessourenManger = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger();
                        shopRessourenManger.setRessourcenValue(ressource, shopRessourenManger.getRessourceValue(ressource) + 1);
                    }
                }

                if(e.getEntity().getType() == EntityType.SKELETON){
                    Ressoure ressource = Ressoure.getRessoureByName("Skelett Arm");
                    if(ressource != null){
                        ShopRessourenManger shopRessourenManger = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger();
                        shopRessourenManger.setRessourcenValue(ressource, shopRessourenManger.getRessourceValue(ressource) + 1);
                    }
                }

                if(e.getEntity().getType() == EntityType.SPIDER){
                    Ressoure ressource = Ressoure.getRessoureByName("Spinnenbein");
                    if(ressource != null){
                        ShopRessourenManger shopRessourenManger = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger();
                        shopRessourenManger.setRessourcenValue(ressource, shopRessourenManger.getRessourceValue(ressource) + 1);
                    }
                }
                if(e.getEntity().getType() == EntityType.DROWNED){
                    Ressoure ressource = Ressoure.getRessoureByName("Seegras");
                    if(ressource != null){
                        ShopRessourenManger shopRessourenManger = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger();
                        shopRessourenManger.setRessourcenValue(ressource, shopRessourenManger.getRessourceValue(ressource) + 1);
                    }
                }

                e.setDroppedExp(0);
                e.getDrops().clear();
                spielerDungeon.getDungeonEntitys().remove(e.getEntity());
                p.sendMessage(Shopy.getInstance().getPrefix() + "§5DEBUG: " + spielerDungeon.getDungeonEntitys().size());

                /* Prüfe on der Dungeon abgeschlossen ist */
                if(spielerDungeon.getDungeonEntitys().size() == 0){
                    spielerDungeon.DungeonAbschlissen();
                }
            }
        }

        for (Dungeon dungeon : Shopy.getInstance().getSpielerDungeon().values()) {
            if(dungeon.getDungeonEntitys().contains(e.getEntity())){
                dungeon.getDungeonEntitys().remove(e.getEntity());
                e.setDroppedExp(0);
                e.getDrops().clear();

                if(dungeon.getDungeonEntitys().size() == 0){
                    dungeon.DungeonAbschlissen();
                }
            }
        }
    }
}
