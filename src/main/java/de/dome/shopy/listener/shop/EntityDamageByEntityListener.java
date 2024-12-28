package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.manger.ShopDefaultItemsManger;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EntityDamageByEntityListener implements Listener {

    public EntityDamageByEntityListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onPlayerInteract(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player)) return;
        Player p = (Player) e.getDamager();

        if(!Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) return;
        if (e.getEntity() instanceof ArmorStand) {
            p.getInventory().addItem(ShopDefaultItemsManger.INSTANCE().getRustungStander());
        }

    }

}
