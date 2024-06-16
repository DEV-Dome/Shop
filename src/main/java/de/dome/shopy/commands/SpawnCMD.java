package de.dome.shopy.commands;

import de.dome.shopy.Shopy;
import de.dome.shopy.commands.welt.LadeWeltCMD;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpawnCMD  implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(Shopy.getInstance().getServerSpawn() != null){
                if (!Shopy.getInstance().getPlayersNotTeleport().contains(p)) {
                    Shopy.getInstance().getPlayersNotTeleport().add(p);

                    new BukkitRunnable() {
                        int countdownTime = 3;

                        @Override
                        public void run() {
                            if (countdownTime <= 0) {
                                cancel();
                                p.teleport(Shopy.getInstance().getServerSpawn());
                                p.sendMessage(Shopy.getInstance().getPrefix() + "§aDu wurdest zum Spawn Teleporiert.");
                                if(Shopy.getInstance().getPlayersNotTeleport().contains(p)) Shopy.getInstance().getPlayersNotTeleport().remove(p);

                                if(Shopy.getInstance().getGeladeneTempWelten().containsKey(p.getUniqueId())){
                                    for(World world : Shopy.getInstance().getGeladeneTempWelten().get(p.getUniqueId())){
                                        File file = world.getWorldFolder();
                                        Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
                                            Bukkit.unloadWorld(world, true);

                                            Shopy.getInstance().rekursivLoeschen(file);
                                        });
                                    }
                                    Shopy.getInstance().getGeladeneTempWelten().remove(p.getUniqueId());
                                }
                                if(Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
                                    p.getInventory().clear();
                                    for(ItemStack item : Shopy.getInstance().getSpielerDungeon().get(p.getUniqueId()).getSpielerInventrar()){
                                        if(item == null) continue;
                                        p.getInventory().addItem(item);
                                    }

                                    Shopy.getInstance().getSpielerDungeon().remove(p.getUniqueId());
                                }

                                /* Scoreboard Updateten */
                                Shopy.getInstance().getScoreboardManger().setScoreBoard(p);
                            } else {
                                p.sendMessage(Shopy.getInstance().getPrefix() + "Du wirst in " + countdownTime + " Sekunden zum Spawn Teleporiert.");
                                countdownTime--;
                            }
                        }
                    }.runTaskTimer(Shopy.getInstance(), 0L, 20L);
                }else {
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cDu kannst dich zurzeit nicht Teleporieren");
                }
            }else {
                p.sendMessage(Shopy.getInstance().getPrefix() + "§cEs konnte kein Server Spawn geladen werden. Wende dich bitte an den Support.");
            }
        }else {
            sender.sendMessage(Shopy.getInstance().getPrefix() + "§cDas können nur Spieler ausführen!");
        }
        return true;

    }
}
