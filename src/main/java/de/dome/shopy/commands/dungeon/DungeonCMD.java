package de.dome.shopy.commands.dungeon;

import de.dome.shopy.Shopy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class DungeonCMD implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("shopy.cmd.dungeon")){
            CompletableFuture.runAsync(() -> {
                try {
                    String abfrageDungeons = "SELECT * FROM dungeon";
                    ResultSet resultDungeons = Shopy.getInstance().getMySQLConntion().resultSet(abfrageDungeons);

                    while (resultDungeons.next()){
                        sender.sendMessage(Shopy.getInstance().getPrefix() + "§e[" +resultDungeons.getInt("id")  + "] §7" + resultDungeons.getString("name") + " - §6("+ resultDungeons.getString("dungeon_ordner") + ")");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        }else {
            sender.sendMessage(Shopy.getInstance().getNoperm());
        }
        return true;
    }

}
