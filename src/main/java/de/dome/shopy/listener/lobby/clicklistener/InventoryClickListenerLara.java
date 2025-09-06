package de.dome.shopy.listener.lobby.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.shop.Shop;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InventoryClickListenerLara implements Listener {

    public InventoryClickListenerLara() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (e.getView().getTitle().equals("§bIngenieurin Lara")) {
            /* Check, ob es ein zu verkaufens Item ist*/
            if(e.getClickedInventory() == null) return;
            if(!e.getClickedInventory().equals(e.getView().getTopInventory())) return;

            if(item.getItemMeta().getDisplayName().equals("§7Schlissen")){
                p.closeInventory();
                return;
            }

            /* Check, ob es ein zu verkaufens Item ist*/
            if(e.getCurrentItem() == null) return;
            if(!e.getClickedInventory().equals(e.getView().getTopInventory())) return;


            Shop spielerShop =  Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
            int spielerGeld = spielerShop.getRessourenShopManger().getRessourceValue(Ressource.getRessoureByName("Geld"));

            String regex = "§e(\\d+)";
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
            java.util.regex.Matcher matcher = pattern.matcher(item.getItemMeta().getLore().get(0));

            int kosten = 0;
            if (matcher.find()) {
                kosten = Integer.parseInt(matcher.group(1));
            }
            if(spielerGeld <= kosten){
                p.sendMessage(Shopy.getInstance().getPrefix() + "Leider reicht dein Geld dafür nicht aus! Dir fehlt noch §e" + (kosten - spielerGeld) + " §7€.");
                return;
            }
            Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger().setRessourcenValue(Ressource.getRessoureByName("Geld"), (spielerGeld - kosten));

            ItemStack giveItem = item.clone();
            List<String> lore = giveItem.getLore();
            lore.remove(1);
            lore.remove(0);
            giveItem.setLore(lore);

            p.getInventory().addItem(giveItem);
        }
    }
}
