package de.dome.shopy.commands;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.Ressoure;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class moneyCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast §e" + Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger().getRessourceValue(Ressoure.getRessoureByName("Geld")) + " §7€");
        }else {
            sender.sendMessage(Shopy.getInstance().getPrefix() + "§cDas können nur Spieler ausführen!");
        }
        return true;
    }
}
