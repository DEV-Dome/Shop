package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.Ressoure;
import de.dome.shopy.utils.Shop;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    public InventoryClickListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        /* Item Markt*/
        if (e.getView().getTitle().equals("§9Ressouren Markplatz")) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                String name = item.getItemMeta().getDisplayName().substring(2);
                Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

                Ressoure ressoure = Ressoure.getRessoureByName(name);
                int ressoureValue = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger().getRessourceValue(ressoure);
                int newAmount = ressoureValue + 1;

                double kosten = Math.round(ressoure.getAktuelleKosten());
                int spielerGeld = spielerShop.getRessourenShopManger().getRessourceValue(Ressoure.getRessoureByName("Geld"));

                if(spielerGeld < kosten){
                    double zuWenig = kosten - spielerGeld;
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cLeider reicht dein geld dafür nicht aus! Dir fehlen §e" + zuWenig + " §c€");
                    return;
                }
                if(newAmount > spielerShop.getRessourcenLager()){
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cLeider reicht der Platz in deinem Ressourcenlager nicht. Dieser ist zurzeit auf " + spielerShop.getRessourcenLager() + " begrenzt.");
                    return;
                }

                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger().setRessourcenValue(ressoure, newAmount);
                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger().setRessourcenValue(Ressoure.getRessoureByName("Geld"), (int) (spielerGeld - kosten));

                p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast dir ein §e" + name + " §7für §e" + kosten + " §7€ gekauft.");
                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openMarkplatzInventar();
            }
        }
        /* Werkbank Übersicht*/
        if (e.getView().getTitle().equals("§9Werkbank-Kategorie")) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                String kategorieName = item.getItemMeta().getDisplayName().substring(2);
                ItemKategorie itemKategorie = ItemKategorie.getItemKategorieByName(kategorieName);

                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openMarkplatzWaffenInventar(0, itemKategorie);
            }
        }
        /* Werkbank einzelne Ansicht */
        if (e.getView().getTitle().startsWith("§9Werkbank ")) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
                /*Statische Items*/
                if(item.getItemMeta().getDisplayName().equals("§7Zurück zur Übersicht")) spielerShop.openWerkbankInventar();
                if(item.getItemMeta().getDisplayName().equals("§7Menü Schlissen")) p.closeInventory();

                if(item.getItemMeta().getDisplayName().equals("§7Nach vorne")) {
                    String[] titleWorte = e.getView().getTitle().split(" ");
                    int AkkuelleSeite = Integer.parseInt(titleWorte[3]) + 1;
                    ItemKategorie itemKategorie = ItemKategorie.getItemKategorieByName(titleWorte[1]);

                    Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openMarkplatzWaffenInventar(AkkuelleSeite, itemKategorie);
                }
                if(item.getItemMeta().getDisplayName().equals("§7Zurück")) {
                    String[] titleWorte = e.getView().getTitle().split(" ");
                    int AkkuelleSeite = Integer.parseInt(titleWorte[3]) - 1;
                    ItemKategorie itemKategorie = ItemKategorie.getItemKategorieByName(titleWorte[1]);

                    if(AkkuelleSeite < 0) return;

                    Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openMarkplatzWaffenInventar(AkkuelleSeite, itemKategorie);
                }
            }

        }

    }

}
