package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Dungeon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener {

    public PlayerDeathListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void PlayerDeath(PlayerDeathEvent e) {
        e.setDeathMessage("");
    }

}
