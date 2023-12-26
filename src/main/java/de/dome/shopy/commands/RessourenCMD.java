package de.dome.shopy.commands;

import de.dome.shopy.Shopy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RessourenCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())){
                p.sendMessage(Shopy.getInstance().getPrefix() + "Test!");
            }else {
                p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast dir noch keinen Shop erstellt. Dieses kannst du beim §eWelten Ersteller§7 tun.");
            }
        }
        return true;
    }
}
