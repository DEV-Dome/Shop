package de.dome.shopy.listener.shop.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.items.ItemSeltenheit;
import de.dome.shopy.utils.items.ItemVerzauberungSet;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListenerSetAufwerter implements Listener {

    public InventoryClickListenerSetAufwerter() {
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

        if(e.getView().getTitle().equals("§9Set-Aufwerter")){
            e.setCancelled(true);

            ShopItem shopItem = null;
            if(e.getInventory().getItem(11).hasItemMeta() && !e.getInventory().getItem(11).getItemMeta().getDisplayName().equals("§7Item auswählen")){
                int itemID = Integer.parseInt(e.getClickedInventory().getItem(11).getItemMeta().getLore().get(0).split(":")[1].substring(1));
                shopItem = spielerShop.getShopItemById(itemID);
            }

            ItemVerzauberungSet itemVerzauberungSet = null;
            if(e.getInventory().getItem(14).hasItemMeta() && !e.getInventory().getItem(14).getItemMeta().getDisplayName().equals("§7Set auswählen")){
                String setName = e.getInventory().getItem(14).getItemMeta().getDisplayName().substring(2);
                itemVerzauberungSet = ItemVerzauberungSet.getItemVerzauberungenSetByName(setName);
            }

            if(e.getSlot() == 11) spielerShop.getShopInventarManger().openSetAufwerterItemAuswahl(1, itemVerzauberungSet);
            if(e.getSlot() == 14) spielerShop.getShopInventarManger().openSetAufwerterSetAuswahl(1, shopItem);
            else if(e.getSlot() == 27 || e.getSlot() == 18) p.closeInventory();
            else if(e.getSlot() == 22) {

                /* CheckUP ob Alles nötige ausgewählt wurde */
                if(shopItem == null || itemVerzauberungSet == null){
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDu musst ein Item und ein Set auswählen!");
                    return;
                }

                /* CheckUP ob die Benötigen Materialen vorhanden sind*/

                if(spielerShop.getShopRessourenManger().getRessourceValue(itemVerzauberungSet.getAufwerter()) < 1){
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDir fehlen noch §e" + (1 - spielerShop.getShopRessourenManger().getRessourceValue(itemVerzauberungSet.getAufwerter())) + " " + itemVerzauberungSet.getAufwerter().getName() +" §cUm das Item aufwerten zu können!");
                    return;
                }

                if(spielerShop.getShopRessourenManger().getRessourceValue(Ressource.getRessoureByName("Geld")) < 1000){
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDir fehlen noch §e" + (1000 - spielerShop.getShopRessourenManger().getRessourceValue(Ressource.getRessoureByName("Geld"))) + " € §cUm das Item aufwerten zu können!");
                    return;
                }

                /* Kosten abziehen */
                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopRessourenManger().setRessourcenValue(itemVerzauberungSet.getAufwerter(), (spielerShop.getShopRessourenManger().getRessourceValue(itemVerzauberungSet.getAufwerter())) - 1);
                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopRessourenManger().setRessourcenValue(Ressource.getRessoureByName("Geld"), (spielerShop.getShopRessourenManger().getRessourceValue(Ressource.getRessoureByName("Geld"))) - 1000);

                shopItem.setItemSeltenheit(ItemSeltenheit.getItemStufeById(6));
                shopItem.setItemVerzauberungSet(itemVerzauberungSet);

                spielerShop.getShopInventarManger().openSetAufwerter(null, itemVerzauberungSet);
                p.sendMessage(Shopy.getInstance().getPrefix() + "§aDein Item wurde zum Set aufgewährtet!");
            }
        }else if(e.getView().getTitle().startsWith("§9Set-Aufwerter Itemauswahl Seite")){
            e.setCancelled(true);
            int seite = Integer.parseInt(e.getView().getTitle().split(" ")[3]);

            ItemVerzauberungSet itemVerzauberungSet = null;
            if(e.getInventory().getItem(31) != null && e.getInventory().getItem(31).hasItemMeta()){
                String setName = e.getInventory().getItem(31).getItemMeta().getDisplayName().substring(2);
                itemVerzauberungSet = ItemVerzauberungSet.getItemVerzauberungenSetByName(setName);
            }

            if(item.getItemMeta().getDisplayName().equals("§7Zurück")) spielerShop.getShopInventarManger().openSetAufwerter(null, null);
            else if(item.getItemMeta().getDisplayName().equals("§7Schlissen")) p.closeInventory();
            else if(item.getItemMeta().getDisplayName().equals("§7Letzte Seite")) spielerShop.getShopInventarManger().openSetAufwerterItemAuswahl(seite - 1, itemVerzauberungSet);
            else if(item.getItemMeta().getDisplayName().equals("§7Nächste Seite")) spielerShop.getShopInventarManger().openSetAufwerterItemAuswahl( seite + 1, itemVerzauberungSet);
            else if(e.getSlot() == 31);
            else {
                int itemID = Integer.parseInt(item.getItemMeta().getLore().get(0).split(":")[1].substring(1));
                ShopItem shopItem = spielerShop.getShopItemById(itemID);

                if(shopItem != null){
                    spielerShop.getShopInventarManger().openSetAufwerter(shopItem, itemVerzauberungSet);
                }else {
                    p.sendMessage(Shopy.getInstance().getKonatktSupport());
                }
            }
        }else if(e.getView().getTitle().startsWith("§9Set-Aufwerter Setauswahl Seite")){
            e.setCancelled(true);
            int seite = Integer.parseInt(e.getView().getTitle().split(" ")[3]);

            ShopItem shopItem = null;
            if(e.getInventory().getItem(31) != null && e.getInventory().getItem(31).hasItemMeta()){
                int itemID = Integer.parseInt(e.getClickedInventory().getItem(31).getItemMeta().getLore().get(0).split(":")[1].substring(1));
                shopItem = spielerShop.getShopItemById(itemID);
            }

            if(item.getItemMeta().getDisplayName().equals("§7Zurück")) spielerShop.getShopInventarManger().openSetAufwerter(shopItem, null);
            else if(item.getItemMeta().getDisplayName().equals("§7Schlissen")) p.closeInventory();
            else if(item.getItemMeta().getDisplayName().equals("§7Letzte Seite")) spielerShop.getShopInventarManger().openSetAufwerterSetAuswahl(seite - 1, shopItem);
            else if(item.getItemMeta().getDisplayName().equals("§7Nächste Seite")) spielerShop.getShopInventarManger().openSetAufwerterSetAuswahl( seite + 1, shopItem);
            else if(e.getSlot() == 31);
            else {
                String setName = item.getItemMeta().getDisplayName().substring(2);
                ItemVerzauberungSet itemVerzauberungSet = ItemVerzauberungSet.getItemVerzauberungenSetByName(setName);

                spielerShop.getShopInventarManger().openSetAufwerter(shopItem, itemVerzauberungSet);
            }
        }
    }
}
