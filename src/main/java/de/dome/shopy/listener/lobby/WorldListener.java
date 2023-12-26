package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.TimeSkipEvent;

public class WorldListener implements Listener {

    public WorldListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void weatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void timeSkip(TimeSkipEvent e) {
       if(e.getWorld().getName().equalsIgnoreCase("world")){
           e.setCancelled(true);
       }
    }
}
