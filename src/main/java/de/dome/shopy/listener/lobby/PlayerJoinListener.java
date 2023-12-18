package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
    }

    public void playerQuit(PlayerQuitEvent e) {
        e.setQuitMessage("");
    }

}
