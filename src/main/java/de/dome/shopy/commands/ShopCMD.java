package de.dome.shopy.commands;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Shop;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class ShopCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            Shop shop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

            if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())){

                Inventory inv = Bukkit.createInventory(null, 45, "§9Shop");

                ArrayList beschreibungShopInfo = new ArrayList<>();
                beschreibungShopInfo.add("§7Level: §9" + shop.getLevel());


                inv.setItem(13, Shopy.getInstance().createItemWithLore(Material.CHEST, "§eShop von " + p.getName(), beschreibungShopInfo));
                inv.setItem(31, Shopy.getInstance().createItem(Material.NETHER_STAR, "§7zum Shop Teleporiren"));

                p.openInventory(inv);

            }else {
                p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast dir noch keinen Shop erstellt. Dieses kannst du beim §eWelten Ersteller§7 tun.");
            }
        }else {
            sender.sendMessage(Shopy.getInstance().getPrefix() + "§cDas können nur Spieler ausführen!");
        }
        return true;
    }


}
