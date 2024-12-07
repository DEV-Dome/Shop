package de.dome.shopy.commands.admin;

import de.dome.shopy.Shopy;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public class SetShopPosition implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.hasPermission("shopy.cmd.setshopposition")) {
                if(args.length == 2){
                    CompletableFuture.runAsync(() -> {
                        Location position = p.getLocation();

                        String query = "INSERT INTO shop_template_werte (template, schlussel, inhalt)" + " VALUES (" + args[0] +", 'shop_spawn','"+ position.toString() +"')";

                        Shopy.getInstance().getMySQLConntion().query(query);
                        p.sendMessage(Shopy.getInstance().getPrefix() + "Zone erstellt!");
                    });
                }else {
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cUngültige Parameter: /setshoppositions <template> <spawn>");
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
