package de.dome.shopy.listener.shop.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.concurrent.CompletableFuture;

public class InventoryClickListenerItemLager implements Listener {

    public InventoryClickListenerItemLager() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }


    public void onInventoryClose(InventoryCloseEvent e) {

    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();


        if (e.getView().getTitle().startsWith("§9Item ")) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                /* Check, ob es ein zu verkaufens Item ist*/
                if(e.getClickedInventory() == null) return;
                if(!e.getClickedInventory().equals(e.getView().getTopInventory())) return;

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
                    spielerShop.getShopInventarManger().openItemLagerInventar(AkkuelleSeite - 1);
                    return;
                }
                if (item.getItemMeta().getDisplayName().equals("§7Nächste Seite")) {
                    spielerShop.getShopInventarManger().openItemLagerInventar(AkkuelleSeite + 1);
                    return;
                }
                //Rechtklick zum Item löschen ausführen
                if(e.isRightClick()){
                    if(item.getType() != Material.GRAY_DYE) {
                        if (!Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
                            int itemID = Integer.parseInt(item.getItemMeta().getLore().get(0).split(":")[1].substring(1));
                            ShopItem shopItem = spielerShop.getShopItemById(itemID);


                            /* Check wurde das Item gefunden */
                            if(shopItem != null && itemID != -1){
                                spielerShop.delteShopItemById(itemID);

                                p.updateInventory();
                                p.playSound(p, Sound.ENTITY_ITEM_BREAK,  1,1);
                            }else {
                                p.sendMessage(Shopy.getInstance().getPrefix() + "§cBeim Ausführen dieser Aktion ist leider ein Fehler aufgetreten. Bitte versuche es später erneut oder Kontaktiere den Support.");
                            }
                        }
                    }
                }else {
                    if (Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
                        /* Wenn Itemlager im Dungeon geöffnet wurde */

                        /* Check ob, dass Item schon genommen wurde */
                        if(!p.getInventory().contains(item)){
                            ItemMeta im = item.getItemMeta();
                            im.setUnbreakable(true);
                            item.setItemMeta(im);

                            p.getInventory().addItem(item);

                            /* ShopItem holen und haltbarkeit um 1 runtersetzten */
                            int itemID = Integer.parseInt(item.getItemMeta().getLore().get(0).split(":")[1].substring(1));
                            ShopItem shopItem = spielerShop.getShopItemById(itemID);

                            if(shopItem.HaltbarkeitVerringern()){
                                /* Item wurde zerstört */
                                p.sendMessage(Shopy.getInstance().getPrefix() + "Die Haltbarkeit dieses Items ist auf 0 gekommen. Deswegen wurde es zerstört!");
                                p.playSound(p, Sound.ENTITY_ITEM_BREAK,  1,1);
                            }else {
                                p.playSound(p, Sound.ENTITY_ITEM_PICKUP,  1,1);
                            }
                        }else {
                            p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast dir dieses Item bereits genommen!");
                        }
                    }
                }
            }
        }

    }
}
