package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Cuboid;
import de.dome.shopy.utils.manger.ShopDefaultItemsManger;
import de.dome.shopy.utils.shop.Shop;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

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
                    Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
                    for(Cuboid cb : spielerShop.getZones()){
                        if(cb.contains(e.getBlock())){
                            if(e.getBlock().getType() == ShopDefaultItemsManger.INSTANCE().getRessourcenLager().getType()){
                                if(spielerShop.getRessourcenLager() >= 45){
                                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDu hast die maximale Anzahl, von diesem Itemtypen bereist verbaut.");
                                    e.setCancelled(true);
                                    return;
                                }

                                int newAmount = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourcenLager() + 10;
                                spielerShop.andereRessourcenLager(newAmount);

                                p.sendMessage(Shopy.getInstance().getPrefix() + "Durch das Platzieren des Item, wurde dein Ressourcenlager um 10 Plätze erweitert.");
                            }
                            if(e.getBlock().getType() == ShopDefaultItemsManger.INSTANCE().getItemLager().getType()){
                                if(spielerShop.getItemLagerSize() >= 55){
                                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDu hast die maximale Anzahl, von diesem Itemtypen bereist verbaut.");
                                    e.setCancelled(true);
                                    return;
                                }

                                int newAmount = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getItemLagerSize() + 10;
                                spielerShop.andereItemLager(newAmount);

                                p.sendMessage(Shopy.getInstance().getPrefix() + "Durch das Platzieren des Item, wurde dein Itemlager um 10 Plätze erweitert.");
                            }
                            if(e.getBlock().getType() == ShopDefaultItemsManger.INSTANCE().getTresen().getType()){
                                if(spielerShop.getTresenPostion() == null){
                                    spielerShop.platziereTresen(e.getBlock().getLocation());
                                    p.sendMessage(Shopy.getInstance().getPrefix() + "§7Dein neuer Tresen ist aufgebaut! Von neun an stellen sich die Kunden hier an.");
                                }else {
                                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDu hast die maximale Anzahl, von diesem Itemtypen bereist verbaut.");
                                    e.setCancelled(true);
                                }
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
