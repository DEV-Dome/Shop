package de.dome.shopy.listener.shop.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import de.dome.shopy.utils.shop.ShopKunden;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListenerKundenKauf implements Listener {

    public InventoryClickListenerKundenKauf() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (e.getView().getTitle().startsWith("§9Kundenansicht von ")) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
                ShopKunden shopKunde = null;

                String npcNamen = e.getView().getTitle().split(" ")[2];
                for(ShopKunden shopKunden : spielerShop.getShopKunden()){
                    if(shopKunden.getNpc().getFullName().equals(npcNamen)){
                        shopKunde = shopKunden;
                        break;
                    }
                }

                if(item.getItemMeta().getDisplayName().equals("§cKein Passendes Item!")) return;

                if(item.getItemMeta().getDisplayName().equals("§7Menü schlissen")){
                    p.closeInventory();
                    return;
                }
                if(item.getItemMeta().getDisplayName().equals("§9Kunden Weg schicken")){
                    p.closeInventory();
                    if(shopKunde != null) shopKunde.loescheKunden();

                    return;
                }

                int itemID = Integer.parseInt(item.getItemMeta().getLore().get(0).split(":")[1].substring(1));
                ShopItem shopItem = spielerShop.getShopItemById(itemID);

                double itempreis = shopItem.getItemPreis();
                if(spielerShop.getZusaetzlicherVerkaufserlös() > 0) itempreis += itempreis * ((double) spielerShop.getZusaetzlicherVerkaufserlös() / 100);

                /* Check wurde das Item gefunden */
                if(shopItem != null && itemID != -1){
                    spielerShop.delteShopItemById(itemID);

                    /* Geld gutschreiben */
                    int spielerGeld = spielerShop.getRessourenShopManger().getRessourceValue(Ressource.getRessoureByName("Geld"));
                    Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger().setRessourcenValue(Ressource.getRessoureByName("Geld"), (int) (spielerGeld + itempreis));

                    /* NPC Entfernen */
                    if(shopKunde != null) shopKunde.loescheKunden();

                    p.closeInventory();
                    p.playSound(p, Sound.ENTITY_ITEM_PICKUP,  1,1);
                    p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast das Item für §e" + itempreis + "€ §7verkauft!");
                }else {
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cBeim Ausführen dieser Aktion ist leider ein Fehler aufgetreten. Bitte versuche es später erneut oder Kontaktiere den Support.");
                }
            }
        }
    }
}
