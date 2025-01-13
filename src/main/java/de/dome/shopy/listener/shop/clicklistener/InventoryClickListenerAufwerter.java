package de.dome.shopy.listener.shop.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.items.ItemSeltenheit;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListenerAufwerter implements Listener {

    public InventoryClickListenerAufwerter() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        if(e.getCurrentItem() == null) return;
        if(!e.getCurrentItem().hasItemMeta()) return;
        if(!Shopy.getInstance().getSpielerShops().containsKey(e.getWhoClicked().getUniqueId())) return;
        if(!Shopy.getInstance().getSpielerShops().get(e.getWhoClicked().getUniqueId()).getWorld().getName().equals(e.getWhoClicked().getWorld().getName())) return;
        if(e.getClickedInventory() == null) return;
        if(!e.getClickedInventory().equals(e.getView().getTopInventory())) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        final Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

        if(e.getView().getTitle().equals("§9Aufwerter")){
            e.setCancelled(true);
            if(e.getSlot() == 12) spielerShop.getShopInventarManger().openAuswerterItemAuswahl(1);
            else if(e.getSlot() == 27) p.closeInventory();
            else if(e.getSlot() == 22) {
                int itemID = Integer.parseInt(e.getClickedInventory().getItem(12).getItemMeta().getLore().get(0).split(":")[1].substring(1));
                ShopItem shopItem = spielerShop.getShopItemById(itemID);

                Ressource aufwerter = shopItem.getItemSeltenheit().getAufwerter();
                int benoetigteAufwerterMenge = shopItem.getItemSeltenheit().getAufwerterMenge();
                int aufwerterMenge = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopRessourenManger().getRessourceValue(aufwerter);

                if(aufwerterMenge >= benoetigteAufwerterMenge){
                    Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopRessourenManger().setRessourcenValue(aufwerter, (aufwerterMenge - benoetigteAufwerterMenge));

                    shopItem.aufwerten();

                    if(shopItem.getItemSeltenheit().getId() != 5 && shopItem.getItemSeltenheit().getId() != 6) spielerShop.getShopInventarManger().openAufwerter(shopItem);
                    else spielerShop.getShopInventarManger().openAufwerter(null);
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§aDu hast das Item aufgewertet!");
                }else {
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDir fehlen noch §e" + (benoetigteAufwerterMenge - aufwerterMenge) + " " + aufwerter.getName() + " §cUm das Item aufwerten zu können!");
                }

            }

        }
        if(e.getView().getTitle().startsWith("§9Aufwerterauswahl Seite")){
            e.setCancelled(true);
            int seite = Integer.parseInt(e.getView().getTitle().split(" ")[2]);

            if(item.getItemMeta().getDisplayName().equals("§7Zurück")) spielerShop.getShopInventarManger().openAufwerter(null);
            else if(item.getItemMeta().getDisplayName().equals("§7Schlissen")) p.closeInventory();
            else if(item.getItemMeta().getDisplayName().equals("§7Letzte Seite")) spielerShop.getShopInventarManger().openAuswerterItemAuswahl(seite - 1);
            else if(item.getItemMeta().getDisplayName().equals("§7Nächste Seite")) spielerShop.getShopInventarManger().openAuswerterItemAuswahl( seite + 1);
            else {
                int itemID = Integer.parseInt(item.getItemMeta().getLore().get(0).split(":")[1].substring(1));
                ShopItem shopItem = spielerShop.getShopItemById(itemID);

                if(shopItem != null){
                    spielerShop.getShopInventarManger().openAufwerter(shopItem);
                }else {
                    p.sendMessage(Shopy.getInstance().getKonatktSupport());
                }
            }

        }
    }
}
