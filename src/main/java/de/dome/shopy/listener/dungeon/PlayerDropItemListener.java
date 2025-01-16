package de.dome.shopy.listener.dungeon;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import de.dome.shopy.Shopy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {

    public PlayerDropItemListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }


    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        if (Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
            if(p.getOpenInventory().getTitle().startsWith("§9Item ")){
                e.setCancelled(true);
            }
        }
    }
}
