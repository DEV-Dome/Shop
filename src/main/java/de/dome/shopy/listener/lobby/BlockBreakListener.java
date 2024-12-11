package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockBreakListener implements Listener {

    public BlockBreakListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

//        if(e.getBlock().getWorld().getName().equalsIgnoreCase("world")){
            if(p.getGameMode() != GameMode.CREATIVE){
                e.setCancelled(true);
                p.sendMessage(Shopy.getInstance().getNoperm());
            }
//        }
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        if(e.getBlock().getWorld().getName().equals("world")){
            if(p.getGameMode() != GameMode.CREATIVE){
                e.setCancelled(true);
                p.sendMessage(Shopy.getInstance().getNoperm());
            }
        }
    }
}
