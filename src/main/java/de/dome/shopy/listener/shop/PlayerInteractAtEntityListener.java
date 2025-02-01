package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.manger.ShopDefaultItemsManger;
import de.dome.shopy.utils.shop.Shop;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractAtEntityListener implements Listener {

    public PlayerInteractAtEntityListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof ArmorStand) {
            Player p = e.getPlayer();

            if(p.getGameMode() != GameMode.CREATIVE) e.setCancelled(true);

            if(!Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) return;
            if(!Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getName().equals(e.getPlayer().getWorld().getName())) return;
            e.setCancelled(true);
            Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
            ArmorStand armorStand = (ArmorStand) e.getRightClicked();

            int itemAusstellerID = -1;
            if(spielerShop.getShopItemHalter().containsKey(armorStand.getLocation())) itemAusstellerID = spielerShop.getShopItemHalter().get(armorStand.getLocation()).getId();
            else {
                p.sendMessage(Shopy.getInstance().getKonatktSupport());
                return;
            }

            spielerShop.getShopInventarManger().openRustungsausteller(itemAusstellerID);
        }
    }
}
