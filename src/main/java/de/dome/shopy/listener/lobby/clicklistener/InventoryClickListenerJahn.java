package de.dome.shopy.listener.lobby.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.manger.ItemShopManger;
import de.dome.shopy.utils.manger.NpcManger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListenerJahn implements Listener {

    public InventoryClickListenerJahn() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        if(e.getCurrentItem() == null) return;
        if(!e.getCurrentItem().hasItemMeta()) return;
        if(e.getClickedInventory() == null) return;
        if(!e.getClickedInventory().equals(e.getView().getTopInventory())) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (e.getView().getTitle().equals(NpcManger.INSTANCE().getJahn().getFullName())) {
            if (item.getItemMeta().getDisplayName().equals("§9Holzblöcke")) {
                ItemShopManger.INSTANCE().openHolzBloeckeAnsicht(p);
            }
            if (item.getItemMeta().getDisplayName().equals("§9Holzitems")) {
                ItemShopManger.INSTANCE().openHolzItemsAnsicht(p);
            }

            if (item.getItemMeta().getDisplayName().equals("§7Schlissen")) {
                p.closeInventory();
            }
        }
    }
}
