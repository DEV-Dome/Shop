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
//        if (!(e.getPlayer() instanceof Player)) return;
//
//        Player p = (Player) e.getPlayer();
//
//
//        if (e.getView().getTitle().startsWith("§9Item Lager Seite ")) {
//            if (Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
//                Shopy.getInstance().getSpielerDungeon().get(p.getUniqueId()).getWaffenkammerNPC().destroy(p);
//
//                p.sendMessage(Shopy.getInstance().getPrefix() + "Der Waffenkammergeist ist verblasst. Ihr müsst wohl mit den Items auskommen! Viel Glück.");
//                p.playSound(p, Sound.ENTITY_ITEM_BREAK,  1,1);
//            }
//        }
    }

}
