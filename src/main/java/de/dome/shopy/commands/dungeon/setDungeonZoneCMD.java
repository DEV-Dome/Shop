package de.dome.shopy.commands.dungeon;

import de.dome.shopy.Shopy;
import de.dome.shopy.listener.shop.BlockBreakListener;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class setDungeonZoneCMD implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if(p.hasPermission("shopy.cmd.setdungeonzone")){
                if(args.length == 2){
                    CompletableFuture.runAsync(() -> {
                        try {
                            String dungeonID = args[0];
                            String wert = "";

                            String abfrageDungeonId = "SELECT id FROM dungeon WHERE id = '" + dungeonID + "' LIMIT 1";
                            ResultSet resultDungeonId = Shopy.getInstance().getMySQLConntion().resultSet(abfrageDungeonId);

                            if (!resultDungeonId.next()){
                                p.sendMessage(Shopy.getInstance().getPrefix() + "Dungeon ID nicht gefunden, folgende Dungeon gibt es:");

                                String abfrageDungeons = "SELECT * FROM dungeon";
                                ResultSet resultDungeons = Shopy.getInstance().getMySQLConntion().resultSet(abfrageDungeons);
                                while (resultDungeons.next()){
                                    p.sendMessage(Shopy.getInstance().getPrefix() + "§e[" +resultDungeons.getInt("id")  + "] §7" + resultDungeons.getString("name"));
                                }
                                return;
                            }

                            if(args[1].equalsIgnoreCase("dungeonzone")) wert = "dungeonzone";
                            if(args[1].equalsIgnoreCase("spawnzone")) wert = "spawnzone";
                            if(wert.equalsIgnoreCase("")) {
                                p.sendMessage(Shopy.getInstance().getPrefix() + "§c/setdungeonzone <dungeonid> <dungeonzone/spawnzone>");
                                return;
                            }

                            if(!BlockBreakListener.shopszones.containsKey(p.getUniqueId())){
                                p.sendMessage(Shopy.getInstance().getPrefix() + "§cEs wurde keine Zone ausgewählt.");
                                return;
                            }

                            Location loc1 = BlockBreakListener.shopszones.get(p.getUniqueId()).getZone1();
                            Location loc2 = BlockBreakListener.shopszones.get(p.getUniqueId()).getZone2();

                            String queryPos1 = "INSERT INTO dungeon_positionen (dungeon, wert, value) VALUES (" + dungeonID +", '"+ wert +".pos1', '"+ loc1.toString() +"')";
                            String queryPos2 = "INSERT INTO dungeon_positionen (dungeon, wert, value) VALUES (" + dungeonID +", '"+ wert +".pos2', '"+ loc2.toString() +"')";

                            Shopy.getInstance().getMySQLConntion().query(queryPos1);
                            Shopy.getInstance().getMySQLConntion().query(queryPos2);
                            p.sendMessage(Shopy.getInstance().getPrefix() + "Bereich erstellt!");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }else {
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§c/setdungeonzone <dungeonid> <dungeonzone/spawnzone>");
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
