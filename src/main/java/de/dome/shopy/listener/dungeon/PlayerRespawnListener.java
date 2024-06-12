package de.dome.shopy.listener.dungeon;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Dungeon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    public PlayerRespawnListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void PlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        if(Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
            Dungeon spielerDungeon = Shopy.getInstance().getSpielerDungeon().get(p.getUniqueId());
            e.setRespawnLocation(spielerDungeon.getSpawn());
        }
    }
}
