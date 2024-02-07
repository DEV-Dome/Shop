package de.dome.shopy.commands.welt;

import de.dome.shopy.Shopy;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ZeigeWeltenCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("shopy.cmd.zeigewelten")){
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
