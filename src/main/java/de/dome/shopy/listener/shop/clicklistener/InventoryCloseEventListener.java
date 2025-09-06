package de.dome.shopy.listener.shop.clicklistener;

import de.dome.shopy.Shopy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseEventListener implements Listener {

    public InventoryCloseEventListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryCloseEvent e) {
        if(e.getPlayer() instanceof Player){
            Player p = (Player) e.getPlayer();

            if(!Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) return;
            if(!Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getName().equals(e.getPlayer().getWorld().getName())) return;

            if(e.getView().getTitle().startsWith("§9")){
                if(p.getOpenInventory() != null) p.closeInventory();
            }
        }

    }
}
