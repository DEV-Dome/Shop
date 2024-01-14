package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Cuboid;
import de.dome.shopy.utils.Shop;
import de.dome.shopy.utils.ShopsZonesCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;
import java.util.UUID;

public class BlockPlaceListener implements Listener {


    public BlockPlaceListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        if(!e.getBlock().getWorld().getName().equals("world")){
            if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())){
                if(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getName().equalsIgnoreCase(e.getBlock().getWorld().getName()) || p.hasPermission("shopy.bypass.buildOnOtherWorlds")){
                    Shop ps = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
                    for(Cuboid cb : ps.getZones()){
                        if(cb.contains(e.getBlock())){
                            if(e.getBlock().getType() == Material.BARREL){
                                if(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourcenLager() >= 45){
                                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDu hast die maximale Anzahl, von diesem Itemtypen bereist verbaut.");
                                    e.setCancelled(true);
                                    return;
                                }

                                int newAmount = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourcenLager() + 10;
                                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).changeRessourcenLager(newAmount);

                                p.sendMessage(Shopy.getInstance().getPrefix() + "Durch das Platzieren des Item, wurde dein Ressourcenlager um 10 Plätze erweitert.");
                            }
                            if(e.getBlock().getType() == Material.TRAPPED_CHEST){
                                if(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getItemLager() >= 55){
                                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDu hast die maximale Anzahl, von diesem Itemtypen bereist verbaut.");
                                    e.setCancelled(true);
                                    return;
                                }

                                int newAmount = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getItemLager() + 10;
                                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).changeItemLager(newAmount);

                                p.sendMessage(Shopy.getInstance().getPrefix() + "Durch das Platzieren des Item, wurde dein Itemlager um 10 Plätze erweitert.");
                            }
                            return;
                        }
                    }
                    e.setCancelled(true);
                }
            }
        }
    }

}
