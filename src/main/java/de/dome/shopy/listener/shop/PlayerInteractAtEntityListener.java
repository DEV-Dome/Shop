package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractAtEntityListener implements Listener {

    public PlayerInteractAtEntityListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }
    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof ArmorStand) {
            Player p = e.getPlayer();
            if(!Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) return;

            e.setCancelled(true);
            p.sendMessage(Shopy.getInstance().getPrefix() + "§7Funktion kommt später ....");
        }
    }
}
