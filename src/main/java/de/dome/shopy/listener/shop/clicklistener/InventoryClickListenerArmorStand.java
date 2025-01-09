package de.dome.shopy.listener.shop.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import de.dome.shopy.utils.shop.ShopItemHalter;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class InventoryClickListenerArmorStand implements Listener {

    public InventoryClickListenerArmorStand() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        if(e.getCurrentItem() == null) return;
        if(!e.getCurrentItem().hasItemMeta()) return;
        if(!Shopy.getInstance().getSpielerShops().containsKey(e.getWhoClicked().getUniqueId())) return;
        if(!Shopy.getInstance().getSpielerShops().get(e.getWhoClicked().getUniqueId()).getWorld().getName().equals(e.getWhoClicked().getWorld().getName())) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        final Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

        if(e.getView().getTitle().startsWith("§9Itemaussteller")){
            int itemAusstellerID = Integer.parseInt(e.getView().getTitle().split(" ")[1]);

            if(e.getSlot() == 27){
                p.closeInventory();
                return;
            }
            final ArrayList<ItemKategorie> itemKategorie = new ArrayList<>();

            if(e.getSlot() == 4) itemKategorie.add(ItemKategorie.getItemKategorieById(4));
            else if(e.getSlot() == 13) itemKategorie.add(ItemKategorie.getItemKategorieById(5));
            else if(e.getSlot() == 22) itemKategorie.add(ItemKategorie.getItemKategorieById(6));
            else if(e.getSlot() == 31) itemKategorie.add(ItemKategorie.getItemKategorieById(7));
            else if(e.getSlot() == 12 || e.getSlot() == 14){
                itemKategorie.add(ItemKategorie.getItemKategorieById(1));
                itemKategorie.add(ItemKategorie.getItemKategorieById(2));
                itemKategorie.add(ItemKategorie.getItemKategorieById(3));
            }

            spielerShop.getShopInventarManger().openRustungsAustellerAuswahl(itemKategorie, itemAusstellerID, 1);

        }
        if(e.getView().getTitle().startsWith("§9Itemausstelluswahl")){
            int itemAusstellerID = Integer.parseInt(e.getView().getTitle().split(" ")[4]);
            ShopItemHalter shopItemHalter = spielerShop.shopItemHalterById(itemAusstellerID);

            int seite = Integer.parseInt(e.getView().getTitle().split(" ")[3]);

            ArrayList<ItemKategorie> itemKategorie = new ArrayList<>();
            if(!e.getView().getTitle().split(" ")[1].equals("Handitem")) itemKategorie.add(ItemKategorie.getItemKategorieByName(e.getView().getTitle().split(" ")[1]));
            else {
                itemKategorie.add(ItemKategorie.getItemKategorieById(1));
                itemKategorie.add(ItemKategorie.getItemKategorieById(2));
                itemKategorie.add(ItemKategorie.getItemKategorieById(3));
            }

            if(item.getItemMeta().getDisplayName().equals("§7Zurück")) spielerShop.getShopInventarManger().openRustungsausteller(itemAusstellerID);
            else if(item.getItemMeta().getDisplayName().equals("§7Schlissen")) p.closeInventory();
            else if(item.getItemMeta().getDisplayName().equals("§7Letzte Seite")) spielerShop.getShopInventarManger().openRustungsAustellerAuswahl(itemKategorie, itemAusstellerID, seite - 1);
            else if(item.getItemMeta().getDisplayName().equals("§7Nächste Seite")) spielerShop.getShopInventarManger().openRustungsAustellerAuswahl(itemKategorie, itemAusstellerID, seite + 1);
            else if(item.getItemMeta().getDisplayName().equals("§7Item löschen")) {
                /* Wenn kein Armor Stand suchen */
                ArmorStand armorStand = null;
                for (Entity entity : shopItemHalter.getLocation().getWorld().getNearbyEntities(shopItemHalter.getLocation(), 0.5, 0.5, 0.5)) {
                    if (entity instanceof ArmorStand) {
                        armorStand = (ArmorStand) entity;
                    }
                }
                /* Wenn kein Armor Stand gefunden wurde */
                if(armorStand == null){
                    p.sendMessage(Shopy.getInstance().getKonatktSupport());
                    return;
                }

                if(itemKategorie.size() == 1){
                    if(itemKategorie.get(0).getId() == 4){
                        shopItemHalter.setItem1(null);
                        armorStand.setItem(EquipmentSlot.HAND, null);

                        spielerShop.getShopInventarManger().openRustungsausteller(itemAusstellerID);
                    }else if(itemKategorie.get(0).getId() == 5){
                        shopItemHalter.setItem2(null);
                        armorStand.setItem(EquipmentSlot.CHEST, null);

                        spielerShop.getShopInventarManger().openRustungsausteller(itemAusstellerID);
                    }else if(itemKategorie.get(0).getId() == 6) {
                        shopItemHalter.setItem3(null);
                        armorStand.setItem(EquipmentSlot.LEGS, null);

                        spielerShop.getShopInventarManger().openRustungsausteller(itemAusstellerID);
                    }else if(itemKategorie.get(0).getId() == 7) {
                        shopItemHalter.setItem4(null);
                        armorStand.setItem(EquipmentSlot.FEET, null);

                        spielerShop.getShopInventarManger().openRustungsausteller(itemAusstellerID);
                    }else p.sendMessage(Shopy.getInstance().getKonatktSupport());
                }else {
                    shopItemHalter.setItem5(null);
                    armorStand.setItem(EquipmentSlot.HAND, null);

                    spielerShop.getShopInventarManger().openRustungsausteller(itemAusstellerID);
                }

            } else {
                int itemID = Integer.parseInt(item.getItemMeta().getLore().get(0).split(":")[1].substring(1));
                ShopItem shopItem = spielerShop.getShopItemById(itemID);

                /* Wenn kein Armor Stand suchen */
                ArmorStand armorStand = null;
                for (Entity entity : shopItemHalter.getLocation().getWorld().getNearbyEntities(shopItemHalter.getLocation(), 0.5, 0.5, 0.5)) {
                    if (entity instanceof ArmorStand) {
                        armorStand = (ArmorStand) entity;
                    }
                }
                /* Wenn kein Armor Stand gefunden wurde */
                if(armorStand == null){
                    p.sendMessage(Shopy.getInstance().getKonatktSupport());
                    return;
                }
                if(itemKategorie.size() == 1){
                    if(itemKategorie.get(0).getId() == 4){
                        shopItemHalter.setItem1(shopItem);

                        armorStand.setItem(EquipmentSlot.HAND, shopItem.buildBaseItem());
                        spielerShop.getShopInventarManger().openRustungsausteller(itemAusstellerID);
                    }else if(itemKategorie.get(0).getId() == 5){
                        shopItemHalter.setItem2(shopItem);

                        armorStand.setItem(EquipmentSlot.CHEST, shopItem.buildBaseItem());

                        spielerShop.getShopInventarManger().openRustungsausteller(itemAusstellerID);
                    }else if(itemKategorie.get(0).getId() == 6) {
                        shopItemHalter.setItem3(shopItem);

                        armorStand.setItem(EquipmentSlot.LEGS, shopItem.buildBaseItem());
                        spielerShop.getShopInventarManger().openRustungsausteller(itemAusstellerID);
                    }else if(itemKategorie.get(0).getId() == 7) {
                        shopItemHalter.setItem4(shopItem);

                        armorStand.setItem(EquipmentSlot.FEET, shopItem.buildBaseItem());
                        spielerShop.getShopInventarManger().openRustungsausteller(itemAusstellerID);
                    }else {
                        p.sendMessage(Shopy.getInstance().getKonatktSupport());
                    }
                }else {
                    shopItemHalter.setItem5(shopItem);

                    armorStand.setItem(EquipmentSlot.HAND, shopItem.buildBaseItem());
                    spielerShop.getShopInventarManger().openRustungsausteller(itemAusstellerID);
                }


            }
        }


    }
}
