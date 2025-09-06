package de.dome.shopy.listener.lobby.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItemKategorie;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListenerMona implements Listener {

    public InventoryClickListenerMona() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (e.getView().getTitle().equals("§dEinhorn Prinessin Mona")) {
            if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                /* Check, ob es ein zu verkaufens Item ist*/
                if(e.getClickedInventory() == null) return;
                if(e.getCurrentItem() == null) return;
                if(!e.getClickedInventory().equals(e.getView().getTopInventory())) return;

                Shop spielerShop =  Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
                int spielerEinhornkristall = spielerShop.getRessourenShopManger().getRessourceValue(Ressource.getRessoureByName("Einhornkristall"));


                if(item.getItemMeta().getDisplayName().equals("§7Schlissen")) p.closeInventory();

                if(item.getItemMeta().getDisplayName().equals("§5Shop Erweitern") && kaufMitMoundKristall(p, spielerEinhornkristall)){
                    spielerShop.addShopZone();

                    p.sendMessage(Shopy.getInstance().getPrefix() + "Dein Shop Grundstück wurde erweitert!");
                    p.updateInventory();
                }

                if(item.getItemMeta().getDisplayName().equals("§5Crafting Kategorie Freischalten") && kaufMitMoundKristall(p, spielerEinhornkristall)){
                    ShopItemKategorie shopItemKategorie = spielerShop.schalteZufaelligeItemKategorieFrei();

                    p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast die Kategorie §e" + shopItemKategorie.getItemKategorie().getName() + " §7freigeschaltet!");
                    p.updateInventory();
                }

                /* Kunden Perks */
                if(item.getItemMeta().getDisplayName().equals("§5Spawnzeit Reduzieren") && kaufMitMoundKristall(p, spielerEinhornkristall)){
                    spielerShop.setReduzierteKundenSpawnZeit(spielerShop.getReduzierteKundenSpawnZeit() + 5);

                    p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast die spawnzeit um §e5 Sekunden §7Reduziert!");
                    p.updateInventory();
                }
                if(item.getItemMeta().getDisplayName().equals("§5Maximale Kunden Menge pro Grundstück") && kaufMitMoundKristall(p, spielerEinhornkristall)){
                    spielerShop.setZusaetzlicheKundenProGrunstueck(spielerShop.getZusaetzlicheKundenProGrunstueck() + 1);

                    p.sendMessage(Shopy.getInstance().getPrefix() + "§7Du hast die maximale Anzahl an Kunden pro um Grundstück auf§e "+ spielerShop.getZusaetzlicheKundenProGrunstueck() +" §7erhört!");
                    p.updateInventory();
                }
                if(item.getItemMeta().getDisplayName().equals("§5Wahrscheinlichkeit das Kunden kommen") && kaufMitMoundKristall(p, spielerEinhornkristall)){
                    spielerShop.setZusaetzlicheKundenWahrscheinlichkeit(spielerShop.getZusaetzlicheKundenWahrscheinlichkeit() + 5);

                    p.sendMessage(Shopy.getInstance().getPrefix() + "§7Du hast die Wahrscheinlichkeit das Kunden kommen erhöht!");
                    p.updateInventory();
                }
                if(item.getItemMeta().getDisplayName().equals("§5Wahrscheinlichkeit auf zusätzliches Item") && kaufMitMoundKristall(p, spielerEinhornkristall)){
                    spielerShop.setZusaetzlichesItemWahrscheinlichkeit(spielerShop.getZusaetzlichesItemWahrscheinlichkeit() + 5);

                    p.sendMessage(Shopy.getInstance().getPrefix() + "§7Du hast die Wahrscheinlichkeit auf zusätzliches Item erhöht!");
                    p.updateInventory();
                }
                if(item.getItemMeta().getDisplayName().equals("§5Wahrscheinlichkeit auf zusätzliche Kategorie") && kaufMitMoundKristall(p, spielerEinhornkristall)){
                    spielerShop.setZusaetzlicheKategorieWahrscheinlichkeit(spielerShop.getZusaetzlicheKategorieWahrscheinlichkeit() + 5);

                    p.sendMessage(Shopy.getInstance().getPrefix() + "§7Du hast die Wahrscheinlichkeit auf zusätzliche Kategorie erhöht!");
                    p.updateInventory();
                }
                if(item.getItemMeta().getDisplayName().equals("§5Mehr Verkaufs erlöse") && kaufMitMoundKristall(p, spielerEinhornkristall)){
                    spielerShop.setZusaetzlicherVerkaufserlös(spielerShop.getZusaetzlicherVerkaufserlös() + 5);

                    p.sendMessage(Shopy.getInstance().getPrefix() + "§7Du hast denn Verkaufs erlöse erhöht!");
                    p.updateInventory();
                }
                if(item.getItemMeta().getDisplayName().equals("§5Materialien Kosten reduzieren") && kaufMitMoundKristall(p, spielerEinhornkristall)){
                    spielerShop.setReduzierteMaterialienKosten(spielerShop.getReduzierteMaterialienKosten() + 5);

                    p.sendMessage(Shopy.getInstance().getPrefix() + "§7Du hast die Materialien Kosten reduzieren!");
                    p.updateInventory();
                }
            }
        }
    }

    private boolean kaufMitMoundKristall(Player p, int spielerEinhornkristall){
        if(spielerEinhornkristall <= 0){
            int zuWenig = 1 - spielerEinhornkristall;
            p.sendMessage(Shopy.getInstance().getPrefix() + "Leider reichen deine Einhornkristall dafür nicht aus! Dir fehlt noch §e" + zuWenig + " Einhornkristall.");
            return false;
        }
        Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger().setRessourcenValue(Ressource.getRessoureByName("Einhornkristall"), (spielerEinhornkristall - 1));
        return true;
    }
}
