package de.dome.shopy.listener.shop.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.Item;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.CompletableFuture;

public class InventoryClickListenerItemLager implements Listener {

    public InventoryClickListenerItemLager() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (e.getView().getTitle().startsWith("§9Item ")) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

                String[] titleWorte = e.getView().getTitle().split(" ");
                int AkkuelleSeite = Integer.parseInt(titleWorte[3]);
//
                if (item.getItemMeta().getDisplayName().equals("§7zur Shopübersicht")) {
                    Bukkit.dispatchCommand(p, "shop");
                    return;
                }
                if (item.getItemMeta().getDisplayName().equals("§7Menü Schlissen")) {
                    p.closeInventory();
                    return;
                }
                if (item.getItemMeta().getDisplayName().equals("§7Letzte Seite")) {
                    spielerShop.openItemLagerInventar(AkkuelleSeite - 1);
                    return;
                }
                if (item.getItemMeta().getDisplayName().equals("§7Nächste Seite")) {
                    spielerShop.openItemLagerInventar(AkkuelleSeite + 1);
                    return;
                }
                //Rechtklick zum Item löschen ausführen
                if(e.isRightClick()){
                    if(item.getType() != Material.GRAY_DYE){


                        int itemID = Integer.parseInt(item.getItemMeta().getLore().get(0).split(":")[1].substring(1));
                        ShopItem shopItem = spielerShop.getShopItemById(itemID);

                        /* Check wurde das Item gefunden */
                        if(shopItem != null){
                            spielerShop.delteShopItemById(itemID);
                            spielerShop.openItemLagerInventar(AkkuelleSeite);
//                            p.updateInventory();
                        }else {
                            p.sendMessage(Shopy.getInstance().getPrefix() + "§cBeim Ausführen dieser Aktion ist leider ein Fehler aufgetreten. Bitte versuche es später erneut oder Kontaktiere den Support.");
                        }
                    }
                }
            }
        }

    }
}
