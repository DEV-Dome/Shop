package de.dome.shopy.listener.dungeon;

import de.dome.shopy.Shopy;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class InventoryCloseListener implements Listener {

    public InventoryCloseListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {

    }

}
