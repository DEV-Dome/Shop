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

    @EventHandler
    public void playerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        e.setQuitMessage("");

        if(LadeWeltCMD.geladeneTempWelten.containsKey(p.getUniqueId())){
            for(World world : LadeWeltCMD.geladeneTempWelten.get(p.getUniqueId())){
                File file = world.getWorldFolder();
                Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
                    Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + world.getName());
                    Bukkit.unloadWorld(world, true);

                    rekursivLoeschen(file);
                });
            }
            LadeWeltCMD.geladeneTempWelten.remove(p.getUniqueId());
        }

        if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) {
            Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).unLoadWorld();
            Shopy.getInstance().getSpielerShops().remove(p.getUniqueId());
        }
        if(de.dome.shopy.listener.shop.BlockBreakListener.shopszones.containsKey(p.getUniqueId())){
            BlockBreakListener.shopszones.remove(p.getUniqueId());
        }
    }

    public boolean rekursivLoeschen(File file) {
        if (!file.exists()) {
            return true;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    rekursivLoeschen(f);
                }
            }
        }

        return file.delete();
    }

}
