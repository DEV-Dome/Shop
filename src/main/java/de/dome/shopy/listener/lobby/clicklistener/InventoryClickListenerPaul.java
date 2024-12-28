package de.dome.shopy.listener.lobby.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import de.dome.shopy.utils.shop.shophandwerksaufgabe.ShopHandwerksAufgabeItem;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItemData;
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

public class InventoryClickListenerPaul implements Listener {

    public InventoryClickListenerPaul() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        if(e.getCurrentItem() == null) return;
        if(!e.getCurrentItem().hasItemMeta()) return;
        if(!Shopy.getInstance().getSpielerShops().containsKey(e.getWhoClicked().getUniqueId())) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        final Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

        if (e.getView().getTitle().equals("§2Handwerksmeister Paul")) {
            if(item.getItemMeta().getDisplayName().startsWith("§9Aufgabe")){
                String[] itemNameAlsArray = item.getItemMeta().getDisplayName().split(" ");
                if(itemNameAlsArray.length == 3 && itemNameAlsArray[2].equals("[Erledigt]")) return;

                int aufgabenNummer = Integer.parseInt(itemNameAlsArray[1]);

                RyseInventory.builder().title("§2Item Abgabe Aufgabe " + aufgabenNummer).rows(6).provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {
                        int i = 0;
                        int aufgabenIndex = aufgabenNummer - 1;
                        for(ShopItem shopItem : spielerShop.getShopItems()){
                            if(i >= 5 * 9) break;
                            /* prüfe ist Item Relavant für Aufgabe  */
                            boolean gefunden = false;

                            for(ShopHandwerksAufgabeItem handwerksAufgabeItem : spielerShop.getShopHandwerksAufgabe().get(aufgabenIndex).getShopHandwerksAufgabeItems()){
                                if(handwerksAufgabeItem.getItem().getName().equals(shopItem.getName())){
                                    if(handwerksAufgabeItem.getMenge() > handwerksAufgabeItem.getFortschritt()){
                                        gefunden = true;
                                    }
                                }
                            }
                            if(!gefunden) continue;

                            /* Item bauen */
                            ArrayList<String> beschreibung = shopItem.getVolleBeschreibung();
                            beschreibung.add("");

                            beschreibung.add("§a- Linksklick zum Item abgeben");

                            ItemStack item = shopItem.buildBaseItem();
                            item.setLore(beschreibung);

                            contents.updateOrSet(i, item);
                            i++;
                        }

                        contents.updateOrSet(45, Shopy.getInstance().createItem(Material.DIAMOND, "§7Zurück zur Aufgabenübersicht"));
                        contents.updateOrSet(53, Shopy.getInstance().createItem(Material.BARRIER, "§7Menü Schlissen"));
                        contents.updateOrSet(46, Shopy.getInstance().createItemWithLore(Material.FLOWER_BANNER_PATTERN, "§9Aufgabe " + aufgabenNummer, spielerShop.getShopHandwerksAufgabe().get(aufgabenIndex).getBeschreibung()));
                    }
                    @Override
                    public void update(Player player, InventoryContents contents) {
                        for(IntelligentItemData ii : contents.getAllData()) contents.removeFirst();

                        init(player, contents);
                    }
                }).build(Shopy.getInstance()).open(p);
            }
        }

        if (e.getView().getTitle().startsWith("§2Item Abgabe Aufgabe")) {
            if(item.getItemMeta().getDisplayName().equals("§7Zurück zur Aufgabenübersicht")){
                spielerShop.openHandwerksmeisterPaulUbersicht();
                return;
            }
            if(item.getItemMeta().getDisplayName().equals("§7Menü Schlissen")){
                p.closeInventory();
                return;
            }
            if(item.getItemMeta().getDisplayName().startsWith("§9Aufgabe ")){
                return;
            }

            int itemID = Integer.parseInt(item.getItemMeta().getLore().get(0).split(":")[1].substring(1));
            int aufgabenIndex = Integer.parseInt(e.getView().getTitle().split(" ")[3]) - 1;
            ShopItem shopItem = spielerShop.getShopItemById(itemID);


            if(shopItem != null){
                for(ShopHandwerksAufgabeItem handwerksAufgabeItem : spielerShop.getShopHandwerksAufgabe().get(aufgabenIndex).getShopHandwerksAufgabeItems()){
                    if(handwerksAufgabeItem.getItem().getName().equals(shopItem.getName())){
                        if(handwerksAufgabeItem.getMenge() > handwerksAufgabeItem.getFortschritt()){
                            handwerksAufgabeItem.setFortschritt(handwerksAufgabeItem.getFortschritt() + 1);

                            // Überprüfe ob, die Aufgabe abgeschlossen ist.
                            if(spielerShop.getShopHandwerksAufgabe().get(aufgabenIndex).isAufgabeAbgeschlossen()){
                                spielerShop.getShopHandwerksAufgabe().get(aufgabenIndex).setErledigt(true);
                                spielerShop.setErledigteHandwerksAufgaben(spielerShop.getErledigteHandwerksAufgaben() + 1);

                                spielerShop.openHandwerksmeisterPaulUbersicht();
                                spielerShop.delteShopItemById(itemID);

                                String belohnung = spielerShop.getShopHandwerksAufgabe().get(aufgabenIndex).gebeBelohnung();
                                p.sendMessage(Shopy.getInstance().getPrefix() + "§7Du hast die Aufgabe abgeschlossen, zur Belohnung bekommt du folgende Items: §e" + belohnung );
                            }else {
                                p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast das Item: §e" + shopItem.getName() + " §7für die Aufgabe gespendet!");
                                spielerShop.delteShopItemById(itemID);
                                p.updateInventory();
                            }
                            break;
                        }
                    }
                }
            }else {
              p.sendMessage(Shopy.getInstance().getPrefix() + "§cEs ist ein unerwarteter Fehler aufgetreten. Bitte melde dich beim Support, oder versuche es später erneut!");
            }
        }
    }
}
