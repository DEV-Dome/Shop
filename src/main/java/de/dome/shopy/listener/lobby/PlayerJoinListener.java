package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import de.dome.shopy.commands.welt.LadeWeltCMD;
import de.dome.shopy.listener.shop.BlockBreakListener;
import de.dome.shopy.utils.shop.Shop;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;

public class PlayerJoinListener implements Listener {

    public PlayerJoinListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage("");

        /* Spieler zum Spawn bringen */
        if(Shopy.getInstance().getServerSpawn() != null){
            p.teleport(Shopy.getInstance().getServerSpawn());
        }

        new Shop(p.getUniqueId(), false);
    }
}
