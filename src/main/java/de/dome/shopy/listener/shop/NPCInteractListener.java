package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import de.dome.shopy.utils.shop.ShopKunden;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Map;

public class NPCInteractListener implements Listener {

    public NPCInteractListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onNPCClick(NPCRightClickEvent e) {
        Player p = e.getClicker();
        NPC npc = e.getNPC();

        Shop kundenShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

        if (!npc.getStoredLocation().getWorld().getName().equals("world")) {
            if (Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) {
                if (Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getName().equalsIgnoreCase(npc.getStoredLocation().getWorld().getName())) {

                    RyseInventory.builder().title("§9Kundenansicht von " + npc.getFullName()).rows(4).provider(new InventoryProvider() {
                        @Override
                        public void init(Player player, InventoryContents contents) {
                            ShopKunden shopKunden = null;

                            for (ShopKunden shopKunde : Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopKunden()){
                                if(shopKunde.getNpc().getUniqueId() == npc.getUniqueId()) shopKunden = shopKunde;
                            }

                            if(shopKunden == null) return;

                            /*Inventroy aufbauen*/
                            contents.set(13, Shopy.getInstance().createItemWithLore(Material.WHITE_CANDLE, "§eInteressante Items", new ArrayList<>(), false, false));

                            int zeahler = 21;
                            if(shopKunden.getGesuchteItems().size() >= 5) zeahler--;

                            for(ShopItem shopItem : shopKunden.getGesuchteItems()){
                                /* Überprüfe ob, dass ausgewählte Item noch im Lager ist */
                                boolean itemNochAufLager = kundenShop.getShopItems().contains(shopItem);

                                if(shopItem != null && itemNochAufLager){
                                    double itempreis = shopItem.getItemPreis();
                                    if(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getZusaetzlicherVerkaufserlös() > 0) itempreis += itempreis * (Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getZusaetzlicherVerkaufserlös() / 100);

                                    ArrayList<String> beschreibung = shopItem.getVolleBeschreibung();
                                    beschreibung.add("");
                                    beschreibung.add("§7Dieser Kunde bietet: §e" + itempreis + "€ §7für das Item!");

                                    ItemStack item = shopItem.buildBaseItem();
                                    item.setLore(beschreibung);

                                    contents.set(zeahler, item);
                                    zeahler++;
                                }else if(!itemNochAufLager) {
                                    ArrayList<String> beschreibung = new ArrayList<>();
                                    beschreibung.add("");
                                    beschreibung.add("§7Leider ist das Item");
                                    beschreibung.add("§7nicht mehr auf Lager!");

                                    ItemStack item = Shopy.getInstance().createItemWithLore(Material.MAP, "§cKein Passendes Item!", beschreibung);

                                    contents.set(zeahler, item);
                                    zeahler++;
                                }else {
                                    ArrayList<String> beschreibung = new ArrayList<>();
                                    beschreibung.add("");
                                    beschreibung.add("§7Leider hat der Kunde");
                                    beschreibung.add("§7nichts passendes gefunden!");

                                    ItemStack item = Shopy.getInstance().createItemWithLore(Material.MAP, "§cKein Passendes Item!", beschreibung);

                                    contents.set(zeahler, item);
                                    zeahler++;
                                }
                            }

                            contents.set(27, Shopy.getInstance().createItemWithLore(Material.OAK_DOOR, "§9Kunden Weg schicken", new ArrayList<>(), false, false));
                            contents.set(35, Shopy.getInstance().createItemWithLore(Material.BARRIER, "§7Menü schlissen", new ArrayList<>(), false, false));
                        }
                    }).build(Shopy.getInstance()).open(p);
                }
            }
        }
    }
}
