package de.dome.shopy.listener.shop.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListenerReparaturTisch implements Listener {

    public InventoryClickListenerReparaturTisch() {
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

        if(e.getView().getTitle().equals("§9Reparatur Tisch")){
            e.setCancelled(true);

            if(e.getSlot() == 12) spielerShop.getShopInventarManger().openReparaturTischAuswahl(1);
            else if(e.getSlot() == 27 || e.getSlot() == 18) p.closeInventory();
            else if(e.getSlot() == 22) {
                int itemID = Integer.parseInt(e.getClickedInventory().getItem(12).getItemMeta().getLore().get(0).split(":")[1].substring(1));
                ShopItem shopItem = spielerShop.getShopItemById(itemID);

                /* Maximale Haltbarkeit beachten*/
                if(shopItem.getHaltbarkeit() >= shopItem.getMaxHaltbarkeit()){
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDieses Item hat bereits volle Haltbarkeit!");
                    return;
                }

                /* CheckUP ob die Benötigen Materialen vorhanden sind*/
                Ressource geld = Ressource.getRessoureByName("Geld");
                int benoetigteGeldMenge = 50;
                int geldMenge = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopRessourenManger().getRessourceValue(geld);

                if(geldMenge < benoetigteGeldMenge){
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDir fehlen noch §e" + (benoetigteGeldMenge - geldMenge) + " € §cUm das Item reperriern zu können!");
                    return;
                }

                Ressource hauptMatrial = shopItem.getHauptMaterial();
                int benoetigteHauptMatrialMenge = 1;
                int hauptMatrialMenge = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopRessourenManger().getRessourceValue(hauptMatrial);

                if(hauptMatrialMenge < benoetigteHauptMatrialMenge){
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDir fehlen noch §e" + (benoetigteHauptMatrialMenge - hauptMatrialMenge) + " " + hauptMatrial.getName() +"  §cUm das Item reperriern zu können!");
                    return;
                }
                /* Kosten abziehen */
                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopRessourenManger().setRessourcenValue(geld, (geldMenge - benoetigteGeldMenge));
                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopRessourenManger().setRessourcenValue(hauptMatrial, (hauptMatrialMenge - benoetigteHauptMatrialMenge));

                /* Haltbarkeit erhöhen*/
                shopItem.setHaltbarkeit(shopItem.getHaltbarkeit() + 1);

                spielerShop.getShopInventarManger().openReparaturTisch(shopItem);
                p.sendMessage(Shopy.getInstance().getPrefix() + "§aDu hast das Item reperriert!");
            }
        }else if(e.getView().getTitle().startsWith("§9Reparatur Tisch Seite")){
            e.setCancelled(true);
            int seite = Integer.parseInt(e.getView().getTitle().split(" ")[3]);

            if(item.getItemMeta().getDisplayName().equals("§7Zurück")) spielerShop.getShopInventarManger().openReparaturTisch(null);
            else if(item.getItemMeta().getDisplayName().equals("§7Schlissen")) p.closeInventory();
            else if(item.getItemMeta().getDisplayName().equals("§7Letzte Seite")) spielerShop.getShopInventarManger().openReparaturTischAuswahl(seite - 1);
            else if(item.getItemMeta().getDisplayName().equals("§7Nächste Seite")) spielerShop.getShopInventarManger().openReparaturTischAuswahl( seite + 1);
            else {
                int itemID = Integer.parseInt(item.getItemMeta().getLore().get(0).split(":")[1].substring(1));
                ShopItem shopItem = spielerShop.getShopItemById(itemID);

                if(shopItem != null){
                    spielerShop.getShopInventarManger().openReparaturTisch(shopItem);
                }else {
                    p.sendMessage(Shopy.getInstance().getKonatktSupport());
                }
            }

        }
    }
}
