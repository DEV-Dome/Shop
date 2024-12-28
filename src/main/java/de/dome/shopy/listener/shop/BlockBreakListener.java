 package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Cuboid;
import de.dome.shopy.utils.manger.ShopDefaultItemsManger;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopsZonesCreator;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
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

            /* Überprüfe ob der Spieler, auf der Fläche von Shop bauen darf */
            if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())){
                /* Check, ist es der Shop von Spieler oder hat er eine Admin permission */
                if(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getName().equalsIgnoreCase(e.getBlock().getWorld().getName()) || p.hasPermission("shopy.bypass.buildOnOtherWorlds")){
                    Shop ps = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
                    /* Check, ob sich der Spieler in einem Grundstück befindet, welches der Spieler auch schon gekauft hat */
                    for(Cuboid cb : ps.getZones()){
                        if(cb.contains(e.getBlock())){
                           /* Besöndere Blöcke Spawnbar machen*/
                            String buildingHinweis  = "§7Dieser Gegenstand kann nur in der Shop-Welt platziert werden. Und entfaltet da einen besonderen Effet der über das, gewöhnlich Maß in Minecraft hinaus geht.";
                            if(e.getBlock().getType() == Material.LECTERN){
                                e.setDropItems(false);

                                p.getInventory().addItem(ShopDefaultItemsManger.INSTANCE().getRessourcenMark());
                            }
                            if(e.getBlock().getType() == Material.CRAFTING_TABLE){
                                e.setDropItems(false);

                                p.getInventory().addItem(ShopDefaultItemsManger.INSTANCE().getWerkbank());
                            }
                            if(e.getBlock().getType() == Material.TRAPPED_CHEST){
                                e.setDropItems(false);

                                p.getInventory().addItem(ShopDefaultItemsManger.INSTANCE().getItemLager());


                                /* Item Effeckt beim Abbauen (negativ) */
                                int newAmount = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getItemLagerSize() - 10;
                                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).changeItemLager(newAmount);

                                p.sendMessage(Shopy.getInstance().getPrefix() + "Durch das Platzieren des Item, wurde dein Itemlager um 10 Plätze reduziert.");

                            }
                            if(e.getBlock().getType() == Material.BARREL){
                                e.setDropItems(false);

                                p.getInventory().addItem(ShopDefaultItemsManger.INSTANCE().getRessourcenLager());

                                /* Item Effeckt beim Abbauen (negativ) */
                                int newAmount = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourcenLager() - 10;
                                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).changeRessourcenLager(newAmount);

                                p.sendMessage(Shopy.getInstance().getPrefix() + "Durch das Platzieren des Item, wurde dein Ressourcenlager um 10 Plätze reduziert.");
                            }
                            if(e.getBlock().getType() == Material.TARGET){
                                e.setDropItems(false);

                                p.getInventory().addItem(ShopDefaultItemsManger.INSTANCE().getAufgabenTisch());
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
