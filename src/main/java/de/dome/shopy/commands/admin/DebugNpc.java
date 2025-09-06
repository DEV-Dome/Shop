package de.dome.shopy.commands.admin;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.MySQLDefault;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopKunden;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugNpc implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(p.hasPermission("shopy.cmd.debugnpc")){
                if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())){
                    Shop shop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
                    shop.getShopKunden().add(new ShopKunden(shop));

                    Shopy.getInstance().getScoreboardManger().setScoreBoard(shop.getOwner());

                    sender.sendMessage(Shopy.getInstance().getPrefix() + "Es wurde ein neuer Kunde für deinen Shop gespawnt");
                }else {
                    sender.sendMessage(Shopy.getInstance().getPrefix() + "§cDu hast keinen Shop in dem ein NPC Spawn kann");
                }
            }else {
                p.sendMessage(Shopy.getInstance().getNoperm());
            }
        }else {
            sender.sendMessage(Shopy.getInstance().getPrefix() + "§cDas können nur Spieler ausführen!");
        }
        return true;
    }
}
