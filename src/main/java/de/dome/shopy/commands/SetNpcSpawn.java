package de.dome.shopy.commands;

import de.dome.shopy.Shopy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class SetNpcSpawn implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command,  String label,  String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(args.length != 0){
                if(p.hasPermission("shopy.cmd.setnpcspawn")){
                    File configFile = new File(Shopy.getInstance().getDataFolder(), "npc.yml");
                    FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

                    if(args[0].equalsIgnoreCase("ersteller")){
                        config.set("npc.ersteller.location", p.getLocation());
                    }else if(args[0].equalsIgnoreCase("dungeonhändler")){
                        config.set("npc.dungeonhändler.location", p.getLocation());
                    } else if(args[0].equalsIgnoreCase("mona")){
                        config.set("npc.mona.location", p.getLocation());
                    }else {
                        p.sendMessage(Shopy.getInstance().getPrefix() + "§c/setnpcspawn <ersteller/dungeonhändler>");
                        return true;
                    }

                    try {
                        config.save(configFile);
                        p.sendMessage(Shopy.getInstance().getPrefix() + "§aDer Spawn wurde gespeichert!");
                    } catch (IOException e) {
                        e.printStackTrace();
                        p.sendMessage(Shopy.getInstance().getPrefix() + "§cBeim Speichern der Datei ist etwas schiefgelaufen. Schau in den Server Log oder in die Konsole für genaue Informationen.");
                    }
                }else {
                    p.sendMessage(Shopy.getInstance().getNoperm());
                }
            }else {
                p.sendMessage(Shopy.getInstance().getPrefix() + "§c/setnpcspawn <ersteller/dungeonhändler/mona>");
            }
        }else {
            sender.sendMessage(Shopy.getInstance().getPrefix() + "§cDas können nur Spieler ausführen!");
        }
        return true;
    }

}
