package de.dome.shopy.commands.admin;

import de.dome.shopy.Shopy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SetSpawnCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(p.hasPermission("shopy.cmd.setspawn")){
                File configFile = new File(Shopy.getInstance().getDataFolder(), "spawn.yml");
                FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

                config.set("server.spawn", p.getLocation());

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
            sender.sendMessage(Shopy.getInstance().getPrefix() + "§cDas können nur Spieler ausführen!");
        }
        return true;
    }


}
