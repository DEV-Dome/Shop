package de.dome.shopy.commands;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.MySQLDefault;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LoadDefaultValue implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(p.hasPermission("shopy.cmd.loadDefaultValue")){
                MySQLDefault.getInstance().loadDefault();

                p.sendMessage(Shopy.getInstance().getPrefix() + "§aStandartwerte wurden in die Datenbank geladen.");

            }else {
                p.sendMessage(Shopy.getInstance().getNoperm());
            }
        }else {
            sender.sendMessage(Shopy.getInstance().getPrefix() + "§cDas können nur Spieler ausführen!");
        }
        return true;
    }
}
