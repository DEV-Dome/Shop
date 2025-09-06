package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import de.dome.shopy.listener.shop.BlockBreakListener;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class PlayerQuitListener implements Listener {

    public PlayerQuitListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        e.setQuitMessage("");

        if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) {
            Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).unLoadWorld();
            Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopItemHalter().clear();
            Shopy.getInstance().getSpielerShops().remove(p.getUniqueId());
        }
        if(de.dome.shopy.listener.shop.BlockBreakListener.shopszones.containsKey(p.getUniqueId())){
            BlockBreakListener.shopszones.remove(p.getUniqueId());
        }

        if(Shopy.getInstance().getGeladeneTempWelten().containsKey(p.getUniqueId())){
            for(World world : Shopy.getInstance().getGeladeneTempWelten().get(p.getUniqueId())){
                File file = world.getWorldFolder();
                Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
                    Bukkit.unloadWorld(world, true);

                    Shopy.getInstance().rekursivLoeschen(file);
                });
            }
            Shopy.getInstance().getGeladeneTempWelten().remove(p.getUniqueId());

            if(Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
                p.getInventory().clear();
                for(ItemStack item : Shopy.getInstance().getSpielerDungeon().get(p.getUniqueId()).getSpielerInventrar()){
                    if(item == null) continue;
                    p.getInventory().addItem(item);
                }

                Shopy.getInstance().getSpielerDungeon().remove(p.getUniqueId());
            }
        }
    }

}
