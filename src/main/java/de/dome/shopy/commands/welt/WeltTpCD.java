package de.dome.shopy.commands.welt;

import de.dome.shopy.Shopy;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeltTpCD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(p.hasPermission("shopy.cmd.welttp")){
                if(args.length == 1){
                    String weltName = args[0];

                    World loadWorld = Bukkit.getWorld(Shopy.getInstance().getDataFolder().getPath() + "/temp_welten/" + weltName);

                    if(loadWorld != null){
                        p.sendMessage(Shopy.getInstance().getPrefix() + "Du wurdest zu Welt §e" + weltName + " §7Teleportiert");
                        p.teleport(loadWorld.getSpawnLocation());
                    }else {
                        p.sendMessage(Shopy.getInstance().getPrefix() + "§cDiese Welt wurde nicht geladen!");
                    }
                }else {
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§c/welttp <welt>");
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
