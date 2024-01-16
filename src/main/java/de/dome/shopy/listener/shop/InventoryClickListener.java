package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.Item;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.items.Ressoure;
import de.dome.shopy.utils.Shop;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

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

                RyseInventory.builder().title("§9Werkbank " + itemKategorie.getName() + " Übersicht").rows(3).provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {
                        int solt = 11;
                        int zaheler = 0;
                        for(Item item : Item.itemList){
                            if(item.getItemKategorie().getId() != itemKategorie.getId()) continue;

                            ArrayList<String> beschreibung = new ArrayList<>();
                            beschreibung.add(item.getBeschreibung());

                            contents.set(solt, Shopy.getInstance().createItemWithLore(item.getIcon(), "§9" + item.getName(), beschreibung));

                            zaheler++;
                            solt += 1;
                            if (zaheler == 6) {
                                break;
                            }
                        }

                        /*Menü Regeler */
                        contents.set(9, Shopy.getInstance().createItem(Material.ARROW, "§7Zurück"));
                        contents.set(17, Shopy.getInstance().createItem(Material.ARROW, "§7Nach vorne"));

                        contents.set(18, Shopy.getInstance().createItem(Material.CRAFTING_TABLE, "§7Zurück zur Übersicht"));
                        contents.set(26, Shopy.getInstance().createItem(Material.BARRIER, "§7Menü Schlissen"));

                        contents.set(4, Shopy.getInstance().createItem(itemKategorie.getIcon(), "§9" + itemKategorie.getName() + " Statistk"));
                    }
                }).build(Shopy.getInstance()).open(p);
            }
        }
        /* Werkbank einzelne Ansicht */
        if (e.getView().getTitle().startsWith("§9Werkbank ")) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
                /*Statische Items*/
                if(item.getItemMeta().getDisplayName().equals("§7Zurück zur Übersicht")) spielerShop.openWerkbankInventar();
                if(item.getItemMeta().getDisplayName().equals("§7Menü Schlissen")) p.closeInventory();
            }

        }

    }

}
