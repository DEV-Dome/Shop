package de.dome.shopy.commands.welt;

import de.dome.shopy.Shopy;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class LadeWeltCMD implements CommandExecutor {



    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(p.hasPermission("shopy.cmd.ladewelt")){
                if(args.length == 1){
                        String weltName = args[0];
                         CompletableFuture.runAsync(() -> {
                            p.sendMessage(Shopy.getInstance().getPrefix() + "Die Welt §e" + weltName + " §7wird geladen, das kann einen Augenblick dauern.");

                            File von = new File(Shopy.getInstance().getDataFolder().getPath() + "/image/" + weltName);
                            File zu = new File(Shopy.getInstance().getDataFolder().getPath() + "/temp_welten/" + weltName);

                            try {
                                Shopy.getInstance().kopiereOrdner(von, zu);
                                Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
                                    WorldCreator creator = new WorldCreator(Shopy.getInstance().getDataFolder().getPath() + "/temp_welten/" + weltName);

                                    creator.environment(World.Environment.NORMAL);
                                    creator.type(WorldType.NORMAL);

                                    World loadWorld = creator.createWorld();
                                    loadWorld.setTime(0);
                                    loadWorld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

                                    p.sendMessage(Shopy.getInstance().getPrefix() + "Die Welt §e" + weltName + " §7wurde geladen. Und du dort hin teleportiert");
                                    p.teleport(loadWorld.getSpawnLocation());

                                    if(Shopy.getInstance().getGeladeneTempWelten().containsKey(p.getUniqueId())){
                                        Shopy.getInstance().getGeladeneTempWelten().get(p.getUniqueId()).add(loadWorld);
                                    }else {
                                        ArrayList<World> worlds = new ArrayList<>();
                                        worlds.add(loadWorld);

                                        Shopy.getInstance().getGeladeneTempWelten().put(p.getUniqueId(), worlds);
                                    }
                                });
                            } catch (IOException e) {
                                Bukkit.getConsoleSender().sendMessage(e.getMessage());
                                throw new RuntimeException(e);
                            }
                        });
                }else {
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§c/ladewelt <welt>");
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