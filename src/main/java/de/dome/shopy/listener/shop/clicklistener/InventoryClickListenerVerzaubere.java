package de.dome.shopy.listener.shop.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.items.ItemVerzauberung;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class InventoryClickListenerVerzaubere implements Listener {

    public InventoryClickListenerVerzaubere() {
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

        if(e.getView().getTitle().equals("§9Verzaubere")){
            e.setCancelled(true);
            if(e.getSlot() == 12) spielerShop.getShopInventarManger().openVerzaubereItemAuswahl(1);
            else if(e.getSlot() == 27 || e.getSlot() == 18) p.closeInventory();
            else if(e.getSlot() == 22) {
                int itemID = Integer.parseInt(e.getClickedInventory().getItem(12).getItemMeta().getLore().get(0).split(":")[1].substring(1));
                ShopItem shopItem = spielerShop.getShopItemById(itemID);

                Ressource aufwerter = Ressource.getRessoureByName("Geld");
                int benoetigteAufwerterMenge = 1000;
                int aufwerterMenge = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopRessourenManger().getRessourceValue(aufwerter);

                if(aufwerterMenge >= benoetigteAufwerterMenge){
                    Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopRessourenManger().setRessourcenValue(aufwerter, (aufwerterMenge - benoetigteAufwerterMenge));

                    ItemVerzauberung hinzugefuegteVerzauberung;
                    do{
                        Random random = new Random();
                        hinzugefuegteVerzauberung = ItemVerzauberung.getItemItemVerzauberungList().get(random.nextInt(ItemVerzauberung.getItemItemVerzauberungList().size()));

                        if(hinzugefuegteVerzauberung.getItemKategorie() == null) break;
                    } while (hinzugefuegteVerzauberung.getItemKategorie().getId() != shopItem.getItemKategorie().getId());

                    /* Sollte die alte verzauberung noch einen Statuseffekt haben, wird dieser hier enfernt */
                    if(shopItem.getItemVerzauberung() != null){
                        ItemVerzauberung alteItemVerzauberung = shopItem.getItemVerzauberung();

                        if(alteItemVerzauberung.getId() == 3 || alteItemVerzauberung.getId() == 4 || alteItemVerzauberung.getId() == 5){
                            double neuerSchaden = shopItem.getSchaden() / 1.1;
                            shopItem.setSchaden(neuerSchaden);
                        }
                        if(alteItemVerzauberung.getId() == 6 || alteItemVerzauberung.getId() == 7 || alteItemVerzauberung.getId() == 8){
                            double neueAngriffsgeschwindigkeit = shopItem.getAngriffsgeschwindigkeit() / 1.08;
                            shopItem.setAngriffsgeschwindigkeit(neueAngriffsgeschwindigkeit);
                        }
                        if(alteItemVerzauberung.getId() == 15){
                            double neueAngriffsgeschwindigkeit = shopItem.getAngriffsgeschwindigkeit() / 1.11;
                            shopItem.setAngriffsgeschwindigkeit(neueAngriffsgeschwindigkeit);
                        }
                        if(alteItemVerzauberung.getId() == 16){
                            double neuerSchaden = shopItem.getSchaden() / 1.15;
                            shopItem.setSchaden(neuerSchaden);
                        }
                        if(alteItemVerzauberung.getId() == 18 || alteItemVerzauberung.getId() == 19 || alteItemVerzauberung.getId() == 20 || alteItemVerzauberung.getId() == 21){
                            double neueRuestung = shopItem.getRustung() / 1.15;
                            shopItem.setRustung(neueRuestung);
                        }
                        if(alteItemVerzauberung.getId() == 24){
                            double neueRuestung = shopItem.getRustung() / 1.22;
                            shopItem.setRustung(neueRuestung);
                        }
                    }

                    /* Item verzaubern */
                    shopItem.setItemVerzauberung(hinzugefuegteVerzauberung);

                    spielerShop.getShopInventarManger().openVerzaubere(shopItem);
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§aDu hast das Item verzaubert!");

                    /*Verzauberungen die Dirket angewendet werden */
                    if(hinzugefuegteVerzauberung.getId() == 3 || hinzugefuegteVerzauberung.getId() == 4 || hinzugefuegteVerzauberung.getId() == 5){
                        double neuerSchaden = shopItem.getSchaden() + shopItem.getSchaden() * ((double) 10 / 100);
                        shopItem.setSchaden(neuerSchaden);
                    }
                    if(hinzugefuegteVerzauberung.getId() == 6 || hinzugefuegteVerzauberung.getId() ==  7 || hinzugefuegteVerzauberung.getId() == 8){
                        double neueAngriffsgeschwindigkeit = shopItem.getAngriffsgeschwindigkeit() + shopItem.getAngriffsgeschwindigkeit() * ((double) 8 / 100);
                        shopItem.setAngriffsgeschwindigkeit(neueAngriffsgeschwindigkeit);
                    }
                    if(hinzugefuegteVerzauberung.getId() == 15){
                        double neueAngriffsgeschwindigkeit = shopItem.getAngriffsgeschwindigkeit() + shopItem.getAngriffsgeschwindigkeit() * ((double) 11 / 100);
                        shopItem.setAngriffsgeschwindigkeit(neueAngriffsgeschwindigkeit);
                    }
                    if(hinzugefuegteVerzauberung.getId() == 16){
                        double neuerSchaden = shopItem.getSchaden() + shopItem.getSchaden() * ((double) 15 / 100);
                        shopItem.setSchaden(neuerSchaden);
                    }
                    if(hinzugefuegteVerzauberung.getId() == 18 || hinzugefuegteVerzauberung.getId() == 19 || hinzugefuegteVerzauberung.getId() == 20 || hinzugefuegteVerzauberung.getId() == 21){
                        double neueRuestung = shopItem.getRustung() + shopItem.getRustung() * ((double) 15 / 100);
                        shopItem.setRustung(neueRuestung);
                    }
                    if(hinzugefuegteVerzauberung.getId() == 24){
                        double neueRuestung = shopItem.getRustung() + shopItem.getRustung() * ((double) 22 / 100);
                        shopItem.setRustung(neueRuestung);
                    }
                }else {
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDir fehlen noch §e" + (benoetigteAufwerterMenge - aufwerterMenge) + " € §cUm das Item verzaubern zu können!");
                }

            }

        }
        if(e.getView().getTitle().startsWith("§9Verzaubereauswahl Seite")){
            e.setCancelled(true);
            int seite = Integer.parseInt(e.getView().getTitle().split(" ")[2]);

            if(item.getItemMeta().getDisplayName().equals("§7Zurück")) spielerShop.getShopInventarManger().openAufwerter(null);
            else if(item.getItemMeta().getDisplayName().equals("§7Schlissen")) p.closeInventory();
            else if(item.getItemMeta().getDisplayName().equals("§7Letzte Seite")) spielerShop.getShopInventarManger().openAuswerterItemAuswahl(seite - 1);
            else if(item.getItemMeta().getDisplayName().equals("§7Nächste Seite")) spielerShop.getShopInventarManger().openAuswerterItemAuswahl( seite + 1);
            else {
                int itemID = Integer.parseInt(item.getItemMeta().getLore().get(0).split(":")[1].substring(1));
                ShopItem shopItem = spielerShop.getShopItemById(itemID);

                if(shopItem != null){
                    spielerShop.getShopInventarManger().openVerzaubere(shopItem);
                }else {
                    p.sendMessage(Shopy.getInstance().getKonatktSupport());
                }
            }

        }

    }


}
