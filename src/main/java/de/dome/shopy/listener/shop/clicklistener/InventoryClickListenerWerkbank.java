package de.dome.shopy.listener.shop.clicklistener;

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

import java.util.concurrent.CompletableFuture;

public class InventoryClickListenerWerkbank implements Listener {

    public InventoryClickListenerWerkbank() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();


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

                String[] titleWorte = e.getView().getTitle().split(" ");
                int AkkuelleSeite = Integer.parseInt(titleWorte[3]);

                /*Statische Items*/
                if(item.getItemMeta().getDisplayName().equals("§7Zurück zur Übersicht")){
                    spielerShop.openWerkbankInventar();
                    return;
                }
                if(item.getItemMeta().getDisplayName().equals("§7Menü Schlissen")){
                    p.closeInventory();
                    return;
                }

                if(item.getItemMeta().getDisplayName().equals("§7Letzte Seite")) {
                    ItemKategorie itemKategorie = ItemKategorie.getItemKategorieByName(titleWorte[1]);

                    Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openMarkplatzWaffenInventar(AkkuelleSeite - 1, itemKategorie);
                    return;
                }

                if(item.getItemMeta().getDisplayName().equals("§7Nächste Seite")) {
                    ItemKategorie itemKategorie = ItemKategorie.getItemKategorieByName(titleWorte[1]);

                    Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openMarkplatzWaffenInventar(AkkuelleSeite + 1, itemKategorie);
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

                    /* Prüfe ob Platz im Itemlager ist */
                    if(spielerShop.getItemLagerSize() < spielerShop.getShopItems().size() - 1){
                        p.sendMessage(Shopy.getInstance().getPrefix() + "Dein Item lager ist voll. Baue weiter ‚§9Item Lager‘ §7um diesen Platz zu erweitern.");
                        return;
                    }

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

                    /*Item Kosten abziehen*/
                    for(ItemRessourecenKosten itk : realItem.getRessourecsKostenList()){
                        int neueMenge = spielerShop.getRessourenShopManger().getRessourceValue(itk.getRessoure()) - itk.getMenge();
                        spielerShop.getRessourenShopManger().setRessourcenValue(itk.getRessoure(), neueMenge);
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