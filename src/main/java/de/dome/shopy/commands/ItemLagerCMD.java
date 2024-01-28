package de.dome.shopy.commands;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ItemLagerCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            Shop shop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

            if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())){
                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openItemLagerInventar(0);
            }else {
                p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast dir noch keinen Shop erstellt. Dieses kannst du beim §eWelten Ersteller§7 tun.");
            }
        }else {
            sender.sendMessage(Shopy.getInstance().getPrefix() + "§cDas können nur Spieler ausführen!");
        }
        return true;
    }


}
