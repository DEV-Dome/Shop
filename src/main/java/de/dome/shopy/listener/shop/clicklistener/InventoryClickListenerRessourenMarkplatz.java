package de.dome.shopy.listener.shop.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.shop.Shop;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListenerRessourenMarkplatz  implements Listener {

    public InventoryClickListenerRessourenMarkplatz() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        /* Item Markt*/
        if (e.getView().getTitle().equals("§9Ressouren Markplatz")) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                String name = item.getItemMeta().getDisplayName().substring(2);
                Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

                Ressource ressource = Ressource.getRessoureByName(name);
                int ressoureValue = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger().getRessourceValue(ressource);
                int newAmount = ressoureValue + 1;

                double kosten = Math.round(ressource.getAktuelleKosten());
                int spielerGeld = spielerShop.getRessourenShopManger().getRessourceValue(Ressource.getRessoureByName("Geld"));

                if(spielerGeld < kosten){
                    double zuWenig = kosten - spielerGeld;
                    p.sendMessage(Shopy.getInstance().getPrefix() + "Leider reicht dein geld dafür nicht aus! Dir fehlen §e" + zuWenig + " §7€");
                    return;
                }
                if(newAmount > spielerShop.getRessourcenLager()){
                    p.sendMessage(Shopy.getInstance().getPrefix() + "Leider reicht der Platz in deinem Ressourcenlager nicht. Dieser ist zurzeit auf " + spielerShop.getRessourcenLager() + " begrenzt.");
                    return;
                }
                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger().setRessourcenValue(ressource, newAmount);
                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger().setRessourcenValue(Ressource.getRessoureByName("Geld"), (int) (spielerGeld - kosten));

                p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast dir ein §e" + name + " §7für §e" + kosten + " §7€ gekauft.");

                p.playSound(p, Sound.ENTITY_ITEM_PICKUP,  1,1);
                p.updateInventory();
            }
        }
    }
}
