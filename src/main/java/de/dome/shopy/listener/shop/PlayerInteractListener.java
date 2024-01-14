package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressoure;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.Map;

public class PlayerInteractListener implements Listener {

    public PlayerInteractListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getClickedBlock() == null) return;
        if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) return;

        if(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getName().equalsIgnoreCase(e.getClickedBlock().getWorld().getName()) || p.hasPermission("shopy.bypass.interactOnOtherWorlds")) {
            if (e.getClickedBlock().getType() == Material.LECTERN) {
                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openMarkplatzInventar();
            }

            if (e.getClickedBlock().getType() == Material.TRAPPED_CHEST ||
                e.getClickedBlock().getType() == Material.BARREL
            ) {
                e.setCancelled(true);
            }
        }
    }
}
