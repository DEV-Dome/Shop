 package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Cuboid;
import de.dome.shopy.utils.Shop;
import de.dome.shopy.utils.ShopsZonesCreator;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;
import java.util.UUID;

public class BlockBreakListener implements Listener {

    public static HashMap<UUID, ShopsZonesCreator> shopszones;

    public BlockBreakListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
        shopszones = new HashMap<>();
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if(!e.getBlock().getWorld().getName().equalsIgnoreCase("world")){
            /* Sonder abfrage, für das erstellen von Zonen*/
            if(p.getItemInHand().getType() == Material.WOODEN_SHOVEL){
                if(p.getGameMode() == GameMode.CREATIVE && p.hasPermission("shopy.cmd.setshopzone")){
                    if(shopszones.containsKey(p.getUniqueId())){
                        ShopsZonesCreator szc = shopszones.get(p.getUniqueId());
                        if(szc.getZone2() == null){
                            szc.setZone2(e.getBlock().getLocation());
                        }else {
                            szc.setZone1(szc.getZone2());
                            szc.setZone2(e.getBlock().getLocation());
                        }
                    }else {
                        shopszones.put(p.getUniqueId(), new ShopsZonesCreator(p.getUniqueId(), e.getBlock().getLocation()));
                    }

                    p.sendMessage(Shopy.getInstance().getPrefix() + " Zonen Markierungen gesetzt.");
                    e.setCancelled(true);
                }
                return;
            }

            if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())){
                p.sendMessage(Shopy.getInstance().getPrefix() + "1");
                if(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getName().equalsIgnoreCase(e.getBlock().getWorld().getName()) || p.hasPermission("shopy.bypass.buildOnOtherWorlds")){
                    p.sendMessage(Shopy.getInstance().getPrefix() + "2");
                    Shop ps = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
                    for(Cuboid cb : ps.getZones()){
                        p.sendMessage(Shopy.getInstance().getPrefix() + cb.getCenter());
                        if(cb.contains(e.getBlock())){

                            return;
                        }
                    }
                    e.setCancelled(true);
                }
            }


        }
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        if(!e.getBlock().getWorld().getName().equals("world")){
            if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())){
                if(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getName().equalsIgnoreCase(e.getBlock().getWorld().getName()) || p.hasPermission("shopy.bypass.buildOnOtherWorlds")){
                    Shop ps = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
                    for(Cuboid cb : ps.getZones()){
                        p.sendMessage(Shopy.getInstance().getPrefix() + cb.getCenter());
                        if(cb.contains(e.getBlock())){

                            return;
                        }
                    }
                    e.setCancelled(true);
                }
            }
        }
    }
}
