package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.Item;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.Ressoure;
import de.dome.shopy.utils.items.ItemRessourecenKosten;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

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
                    p.sendMessage(Shopy.getInstance().getPrefix() + "Leider reicht dein geld dafür nicht aus! Dir fehlen §e" + zuWenig + " §7€");
                    return;
                }
                if(newAmount > spielerShop.getRessourcenLager()){
                    p.sendMessage(Shopy.getInstance().getPrefix() + "Leider reicht der Platz in deinem Ressourcenlager nicht. Dieser ist zurzeit auf " + spielerShop.getRessourcenLager() + " begrenzt.");
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
                if(item.getItemMeta().getDisplayName().equals("§7Zurück zur Übersicht")){
                    spielerShop.openWerkbankInventar();
                    return;
                }
                if(item.getItemMeta().getDisplayName().equals("§7Menü Schlissen")){
                    p.closeInventory();
                    return;
                }

                if(item.getItemMeta().getDisplayName().equals("§7Nach vorne")) {
                    String[] titleWorte = e.getView().getTitle().split(" ");
                    int AkkuelleSeite = Integer.parseInt(titleWorte[3]) + 1;
                    ItemKategorie itemKategorie = ItemKategorie.getItemKategorieByName(titleWorte[1]);

                    Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openMarkplatzWaffenInventar(AkkuelleSeite, itemKategorie);
                    return;
                }
                if(item.getItemMeta().getDisplayName().equals("§7Zurück")) {
                    String[] titleWorte = e.getView().getTitle().split(" ");
                    int AkkuelleSeite = Integer.parseInt(titleWorte[3]) - 1;
                    ItemKategorie itemKategorie = ItemKategorie.getItemKategorieByName(titleWorte[1]);

                    if(AkkuelleSeite < 0) return;

                    Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openMarkplatzWaffenInventar(AkkuelleSeite, itemKategorie);
                    return;
                }

                /* Item herstellen */
                String[] itemNameWorte = item.getItemMeta().getDisplayName().split(" ");
                String itemName = "";

                /* Nur den Item Namen bauen */
                int zeahler = 0;
                for(String wort : itemNameWorte){
                    if(itemNameWorte.length - 1 == zeahler) break;

                    itemName += " " + wort;
                    zeahler++;
                }

                itemName = itemName.substring(3, itemName.length() - 3);

                Item realItem = Item.getItemByName(itemName);
                if(realItem != null){
                    // Item gefunden, Neun kann es 'gebaut' werden.

                    /* Item Kosten */
                    boolean kostenvorhanden = true;
                    String fehlendeRessourecen = "";
                    for(ItemRessourecenKosten itk : realItem.getRessourecsKostenList()){
                        int ressourenMenge = spielerShop.getRessourenShopManger().getRessourceValue(itk.getRessoure());
                        if(ressourenMenge < itk.getMenge()){
                            if(kostenvorhanden) kostenvorhanden = false;
                            fehlendeRessourecen += "§e" + (itk.getMenge() - ressourenMenge ) + " §7" + itk.getRessoure().getName() + ", ";
                        }
                    }


                    /* Wenn nicht alle Kosten vorhanden sind */
                    if(!kostenvorhanden) {
                        fehlendeRessourecen = fehlendeRessourecen.substring(0, fehlendeRessourecen.length() - 2);
                        fehlendeRessourecen += ".";

                        p.sendMessage(Shopy.getInstance().getPrefix() + "Dir fehlen noch Ressourcen um dieses Item bauen zu können: " + fehlendeRessourecen);
                        return;
                    }

                    spielerShop.getShopItems().add(new ShopItem(-1, realItem.getItemKategorie(), realItem.getName(), realItem.getBeschreibung(), realItem.getIcon(), realItem.getItemSeltenheit()));
                    CompletableFuture.runAsync(() -> {
                        Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_item_lager (shop, item) VALUES ('" + spielerShop.getShopId() + "', '" + realItem.getId() + "')");
                    });

                    p.sendMessage(Shopy.getInstance().getPrefix() + "Item herstellen ...");
                    p.playSound(p, Sound.ENTITY_ITEM_PICKUP,  1,1);
                }else {
                  p.sendMessage(Shopy.getInstance().getPrefix() + "§cBeim Ausführen dieser Aktion ist leider ein Fehler aufgetreten. Bitte versuche es später erneut oder Kontaktiere den Support.");
                }
            }

        }

    }

}
