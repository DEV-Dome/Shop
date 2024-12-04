package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
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

        if (!npc.getStoredLocation().getWorld().getName().equals("world")) {
            if (Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) {
                if (Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getName().equalsIgnoreCase(npc.getStoredLocation().getWorld().getName())) {

                    RyseInventory.builder().title("§9Kundenansicht").rows(4).provider(new InventoryProvider() {
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
                            for(ShopItem shopItem : shopKunden.getGesuchteItems()){
                                contents.set(zeahler, Shopy.getInstance().createItemWithLore(shopItem.getIcon(), shopItem.getVollenName(), shopItem.getVolleBeschreibung(), false, false));
                                zeahler++;
                            }

                            contents.set(27, Shopy.getInstance().createItemWithLore(Material.OAK_DOOR, "§7Kunden Weg schicken", new ArrayList<>(), false, false));
                            contents.set(28, Shopy.getInstance().createItemWithLore(Material.BARRIER, "§7Menü schlissen", new ArrayList<>(), false, false));

                            contents.set(35, Shopy.getInstance().createItemWithLore(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE, "§7Kunden Beraten", new ArrayList<>(), false, false));
                        }
                    }).build(Shopy.getInstance()).open(p);
                }
            }
        }
    }
}
