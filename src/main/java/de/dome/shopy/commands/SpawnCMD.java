package de.dome.shopy.commands;

import de.dome.shopy.Shopy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnCMD  implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(Shopy.getInstance().getServerSpawn() != null){
                if (!Shopy.getInstance().getPlayersNotTeleport().contains(p)) {
                    /* Countdown, damit Spieler erst nach 5 Sekunden zum Spawn teleportiert wird */
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
