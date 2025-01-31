package de.dome.shopy.listener.dungeon;

import de.dome.shopy.Shopy;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class PlayerToggleFlightListener implements Listener {

    public PlayerToggleFlightListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void PlayerToggleFlight(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
            e.setCancelled(true); // Standard-Flug verhindern
            p.setAllowFlight(false); // Nach dem Sprung deaktivieren
            p.setFlying(false);

            // Spieler nach oben katapultieren (Anpassen nach Wunsch)
            Vector jump = p.getLocation().getDirection().multiply(0.5).setY(1);
            p.setVelocity(jump);
        }
    }
}
