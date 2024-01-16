package de.dome.shopy.commands;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.Ressoure;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setRessourceCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if(p.hasPermission("shopy.cmd.setressource")){
                if(args.length == 3){
                    String ressourcenName = args[1];

                    Player target = Bukkit.getPlayer(args[0]);
                    if(target == null){
                        p.sendMessage(Shopy.getInstance().getPrefix() + "§cDer angebende Spieler ist nicht online!");
                        return true;
                    }

                    Ressoure ressource = Ressoure.getRessoureByName(ressourcenName);
                    if(ressource == null){
                        p.sendMessage(Shopy.getInstance().getPrefix() + "§cEine Ressource mit diesem Namen gibt es nicht!");
                        return true;
                    }

                    int newValue = Integer.parseInt(args[2]);

                    Shopy.getInstance().getSpielerShops().get(target.getUniqueId()).getRessourenShopManger().setRessourcenValue(ressource, newValue);
                    p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast dem Spieler §e" + target.getName() + " §7Die Ressource §e" + ressource.getName() + " §7auf eine Anzahl von §e" + newValue + " §7gesetzt!");
                }else {
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§c/setressource <spieler> <ressource> <anzahl>");
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
