package de.dome.shopy.commands.dungeon;

import de.dome.shopy.Shopy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.concurrent.CompletableFuture;

public class addDungeon implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("shopy.cmd.adddungeon")){
            if(args.length == 2){
                CompletableFuture.runAsync(() -> {
                    String dungeonName =  args[0];
                    String dungeonOrdner =  args[1];

                    String query = "INSERT INTO dungeon (name, dungeon_ordner) VALUES ('" + dungeonName +"', '"+ dungeonOrdner +"')";
                    Shopy.getInstance().getMySQLConntion().query(query);

                    sender.sendMessage(Shopy.getInstance().getPrefix() + "Dungeon §e" + dungeonName + " §7erstellt!");
                });
            }else {
                sender.sendMessage(Shopy.getInstance().getPrefix() + "§c/addDungeon <dungeonname> <dungeonordner>");
            }
        }else {
            sender.sendMessage(Shopy.getInstance().getNoperm());
        }
        return true;
    }
}

