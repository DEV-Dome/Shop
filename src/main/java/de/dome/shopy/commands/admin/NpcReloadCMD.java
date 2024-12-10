package de.dome.shopy.commands.admin;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.manger.NpcManger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;


public class NpcReloadCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(args.length == 0){
                if(p.hasPermission("shopy.cmd.npcreload")){
                    NpcManger.INSTANCE().loescheAlleNpc();
                    NpcManger.INSTANCE().spawnNPC();

                    p.sendMessage(Shopy.getInstance().getPrefix() + "§7Es wurden alle NPC's neu geladen.");
                }else {
                    p.sendMessage(Shopy.getInstance().getNoperm());
                }
            }else {
                p.sendMessage(Shopy.getInstance().getPrefix() + "§c/npcreload");
            }
        }else {
            sender.sendMessage(Shopy.getInstance().getPrefix() + "§cDas können nur Spieler ausführen!");
        }
        return true;
    }
}
