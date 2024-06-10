package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDamageListener implements Listener {

    public EntityDamageListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void entityDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if(!Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
                e.setCancelled(true);
            }
        }
    }

}
