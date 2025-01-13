package de.dome.shopy.listener.lobby.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Dungeon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListenerDungeonHaendler implements Listener {

    public InventoryClickListenerDungeonHaendler() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (e.getView().getTitle().equals("§5Dungeon Händler")) {
            if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                /* Check, ob es ein zu verkaufens Item ist*/
                if(e.getClickedInventory() == null) return;
                if(!e.getClickedInventory().equals(e.getView().getTopInventory())) return;

                switch (item.getType()){
                    case ZOMBIE_SPAWN_EGG:
                        Shopy.getInstance().getSpielerDungeon().put(p.getUniqueId(), new Dungeon(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()), 1));
                        break;
                    case VINDICATOR_SPAWN_EGG:
                        Shopy.getInstance().getSpielerDungeon().put(p.getUniqueId(), new Dungeon(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()), 2));
                        break;
                    case BLAZE_SPAWN_EGG:
                        Shopy.getInstance().getSpielerDungeon().put(p.getUniqueId(), new Dungeon(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()), 3));
                        break;
                    case EVOKER_SPAWN_EGG:
                        Shopy.getInstance().getSpielerDungeon().put(p.getUniqueId(), new Dungeon(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()), 4));
                        break;
                    case BARRIER:
                        p.closeInventory();
                        break;
                }
            }
        }
    }
}
