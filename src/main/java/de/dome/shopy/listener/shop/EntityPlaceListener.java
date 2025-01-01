package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPlaceEvent;

public class EntityPlaceListener implements Listener {

    public EntityPlaceListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onEntityPlace(EntityPlaceEvent e) {
        Player p = e.getPlayer();

        if (e.getEntity() instanceof ArmorStand) {
            Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).platziereItemHalter(e.getEntity().getLocation(), "ARMOR_STAND");
        }
    }

}
