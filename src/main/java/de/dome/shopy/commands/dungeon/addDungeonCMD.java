package de.dome.shopy.commands.dungeon;

import de.dome.shopy.Shopy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class addDungeonCMD implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("shopy.cmd.adddungeon")){
            if(args.length == 2){
                CompletableFuture.runAsync(() -> {
                    try {
                        String dungeonName =  args[0];
                        String dungeonOrdner =  args[1];

                        String abfrageDungeonName = "SELECT id FROM dungeon WHERE name = '" + dungeonName + "' LIMIT 1";
                        ResultSet resultDungeonName = Shopy.getInstance().getMySQLConntion().resultSet(abfrageDungeonName);

                        if (resultDungeonName.next()){
                            sender.sendMessage(Shopy.getInstance().getPrefix() + "Einen Dungeon mit diesen Namen gibt es schon!");
                            return;
                        }

                        String query = "INSERT INTO dungeon (name, dungeon_ordner) VALUES ('" + dungeonName +"', '"+ dungeonOrdner +"')";
                        Shopy.getInstance().getMySQLConntion().query(query);

                        sender.sendMessage(Shopy.getInstance().getPrefix() + "Dungeon §e" + dungeonName + " §7erstellt!");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
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

