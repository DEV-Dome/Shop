package de.dome.shopy.listener.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressoure;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.Map;

public class PlayerInteractListener implements Listener {

    public PlayerInteractListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getName().equalsIgnoreCase(e.getClickedBlock().getWorld().getName()) || p.hasPermission("shopy.bypass.interactOnOtherWorlds")) {
            if (e.getClickedBlock().getType() == Material.LECTERN) {
                RyseInventory.builder().title("§9Ressouren Markplatz").rows(4).provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {
                        int solt = 10;
                        int zaheler = 0;
                        for (Map.Entry<Ressoure, Integer> shopRessoure : Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger().getShopRessoure().entrySet()) {
                            Ressoure ressoure = shopRessoure.getKey();
                            if(!ressoure.getType().equalsIgnoreCase("STANDART")) continue;

                            ArrayList<String> beschreibung = new ArrayList<>();
                            beschreibung.add("§7Menge: §e" + shopRessoure.getValue());
                            beschreibung.add("");
                            beschreibung.add("§7Aktuelle Kosten §e:" + Math.round(ressoure.getAktuelleKosten()));
                            beschreibung.add("");
                            beschreibung.add("§5" + ressoure.getBeschreibung());

                            contents.set(solt, Shopy.getInstance().createItemWithLore(ressoure.getIcon(), "§9" + ressoure.getName(), beschreibung));

                            /* Items Anordenen */
                            zaheler++;
                            if (zaheler == 7) {
                                solt += 3;
                                zaheler = 0;
                            } else {
                                solt++;
                            }
                        }
                    }
                }).build(Shopy.getInstance()).open(p);
            }
        }
    }
}
