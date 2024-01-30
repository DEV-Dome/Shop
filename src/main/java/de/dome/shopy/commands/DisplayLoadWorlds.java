package de.dome.shopy.commands;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.shop.Shop;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DisplayLoadWorlds implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("shopy.cmd.displayloadworlds")){
            sender.sendMessage(Shopy.getInstance().getPrefix() + "Folgende Welten sind geladen: ");
            for(World loadWorld : Bukkit.getWorlds()) {
                sender.sendMessage(Shopy.getInstance().getPrefix() + loadWorld.getName() + " ist geladen.");
            }
        }else {
            sender.sendMessage(Shopy.getInstance().getNoperm());
        }
        return true;
    }
}
