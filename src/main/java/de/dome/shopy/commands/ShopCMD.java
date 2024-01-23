package de.dome.shopy.commands;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.shop.Shop;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ShopCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            Shop shop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

            if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())){
                RyseInventory.builder().title("§9Shop")
                        .rows(6)
                        .provider(new InventoryProvider() {
                            @Override
                            public void init(Player player, InventoryContents contents) {
                                ArrayList beschreibungShopInfo = new ArrayList<>();
                                beschreibungShopInfo.add("§7Level: §e" + shop.getLevel());
                                contents.set(13, Shopy.getInstance().createItemWithLore(Material.CHEST, "§eShop von " + p.getName(), beschreibungShopInfo));

                                contents.set(29, Shopy.getInstance().createItem(Material.HEART_OF_THE_SEA, "§9Shop Ressouren"));
                                contents.set(31, Shopy.getInstance().createItem(Material.NETHER_STAR, "§9zum Shop Teleporiren"));

                                beschreibungShopInfo = new ArrayList<>();
                                beschreibungShopInfo.add("§7Ressourcen Lager: §e" + shop.getRessourcenLager());
                                beschreibungShopInfo.add("§7Item Lager: §e" + shop.getItemLager());

                                contents.set(33, Shopy.getInstance().createItemWithLore(Material.FEATHER, "§9Statistik", beschreibungShopInfo));

                                contents.set(39, Shopy.getInstance().createItem(Material.TRAPPED_CHEST, "§9Item Lager"));
                            }
                        }).build(Shopy.getInstance()).open(p);
            }else {
                p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast dir noch keinen Shop erstellt. Dieses kannst du beim §eWelten Ersteller§7 tun.");
            }
        }else {
            sender.sendMessage(Shopy.getInstance().getPrefix() + "§cDas können nur Spieler ausführen!");
        }
        return true;
    }


}
