package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Cuboid;
import de.dome.shopy.utils.manger.ShopDefaultItemsManger;
import de.dome.shopy.utils.manger.ShopInventarManger;
import de.dome.shopy.utils.shop.Shop;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class PlayerInteractListener implements Listener {

    private final HashMap<UUID, Long> cooldown;


    public PlayerInteractListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
        cooldown = new HashMap<>();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getClickedBlock() == null) return;
        if (!Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) return;

        ShopInventarManger shopInventarManger = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopInventarManger();

        if(e.getAction() == Action.LEFT_CLICK_BLOCK){
            if (Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getName().equalsIgnoreCase(e.getClickedBlock().getWorld().getName()) || p.hasPermission("shopy.bypass.buildOnOtherWorlds")) {
                /* Überprüfe ob der Spieler, auf der Fläche von Shop bauen darf */
                if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())){
                    /* Check, ist es der Shop von Spieler oder hat er eine Admin permission */
                    if(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getName().equalsIgnoreCase(e.getClickedBlock().getWorld().getName()) || p.hasPermission("shopy.bypass.buildOnOtherWorlds")){
                        Shop ps = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
                        /* Check, ob sich der Spieler in einem Grundstück befindet, welches der Spieler auch schon gekauft hat */
                        for(Cuboid cb : ps.getZones()){
                            if(cb.contains(e.getClickedBlock())){
                                p.playSound(p, Sound.BLOCK_ROOTED_DIRT_BREAK,  1,1);
                                p.getInventory().addItem(new ItemStack(e.getClickedBlock().getType()));
                                e.getClickedBlock().setType(Material.AIR);
                                return;
                            }
                        }
                        e.setCancelled(true);
                    }
                }
            }
        }else if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getName().equalsIgnoreCase(e.getClickedBlock().getWorld().getName()) || p.hasPermission("shopy.bypass.interactOnOtherWorlds")) {
                // Doppelte Events innerhalb von 100ms verhindern
                long now = System.currentTimeMillis();
                if (cooldown.containsKey(p.getUniqueId()) && (now - cooldown.get(p.getUniqueId())) < 100) return;
                cooldown.put(p.getUniqueId(), now);

                if (e.getClickedBlock().getType() == Material.LECTERN) e.setCancelled(true);

                else if (e.getClickedBlock().getType() == Material.CARTOGRAPHY_TABLE) {
                    e.setCancelled(true);
                    shopInventarManger.openMarkplatzInventar();
                }else if (e.getClickedBlock().getType() == Material.CRAFTING_TABLE) {
                    e.setCancelled(true);
                    shopInventarManger.openWerkbankInventar();
                }else if (e.getClickedBlock().getType() == Material.TRAPPED_CHEST) {
                    e.setCancelled(true);
                    shopInventarManger.openItemLagerInventar(0);
                }else if (e.getClickedBlock().getType() == Material.TARGET) {
                    shopInventarManger.openHandwerksmeisterPaulUbersicht();
                }else if (e.getClickedBlock().getType() == Material.BARREL) {
                    e.setCancelled(true);
                }else if (e.getClickedBlock().getType() == Material.SMITHING_TABLE) {
                    e.setCancelled(true);
                    shopInventarManger.openAufwerter(null);
                }else if (e.getClickedBlock().getType() == Material.AMETHYST_BLOCK) {
                    e.setCancelled(true);
                    shopInventarManger.openVerzaubere(null);
                }else if (e.getClickedBlock().getType() == Material.ANVIL) {
                    e.setCancelled(true);
                    shopInventarManger.openReparaturTisch(null);
                }else if (e.getClickedBlock().getType() == Material.CALIBRATED_SCULK_SENSOR) {
                    e.setCancelled(true);
                    shopInventarManger.openSetAufwerter(null, null);
                }else if (e.getClickedBlock().getType() == Material.HONEY_BLOCK) {
                    e.setCancelled(true);
                    shopInventarManger.openMuelleimer();
                }
            }
        }
    }
}
