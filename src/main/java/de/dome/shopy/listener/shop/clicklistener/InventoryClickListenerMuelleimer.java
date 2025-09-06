package de.dome.shopy.listener.shop.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.shop.Shop;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListenerMuelleimer implements Listener {

    public InventoryClickListenerMuelleimer() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        if(e.getCurrentItem() == null) return;
        if(!Shopy.getInstance().getSpielerShops().containsKey(e.getWhoClicked().getUniqueId())) return;
        if(!Shopy.getInstance().getSpielerShops().get(e.getWhoClicked().getUniqueId()).getWorld().getName().equals(e.getWhoClicked().getWorld().getName())) return;
        if(e.getClickedInventory() == null) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if(e.getView().getTitle().equals("§9Mülleimer")){
            e.setCancelled(true);

            if(e.getClickedInventory().equals(e.getView().getBottomInventory())){
                if(e.isShiftClick()){
                    e.getCurrentItem().setAmount(0);
                }else {
                    int neueAnzahl = e.getCurrentItem().getAmount() - 1;
                    if(neueAnzahl < 0) neueAnzahl = 0;

                    e.getCurrentItem().setAmount(neueAnzahl);
                }
            }else {
                if(item.getItemMeta().getDisplayName().equals("§7Schlissen")) p.closeInventory();
            }
        }
    }


}
