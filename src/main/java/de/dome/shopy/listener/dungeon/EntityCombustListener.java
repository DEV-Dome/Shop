package de.dome.shopy.listener.dungeon;

import de.dome.shopy.Shopy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;

public class EntityCombustListener implements Listener {

    public EntityCombustListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void EntityCombust(EntityCombustEvent e){
        e.setCancelled(true);
    }

}
