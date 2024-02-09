package de.dome.shopy.commands.admin;

import de.dome.shopy.Shopy;
import de.dome.shopy.listener.shop.BlockBreakListener;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public class SetShopZoneCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.hasPermission("shopy.cmd.setshopzone")) {
                if(args.length == 1){
                    if(BlockBreakListener.shopszones.containsKey(p.getUniqueId()) && BlockBreakListener.shopszones.get(p.getUniqueId()).getZone2() != null){
                        CompletableFuture.runAsync(() -> {
                                Location loc1 = BlockBreakListener.shopszones.get(p.getUniqueId()).getZone1();
                                Location loc2 = BlockBreakListener.shopszones.get(p.getUniqueId()).getZone2();

                                String query = "INSERT INTO shop_template_zonen (template, locationen_1, locationen_2, anordnung)" +
                                        " VALUES (" + args[0] +", '"+ loc1.toString() +"', '"+ loc2.toString() +"', 0)";

                                Shopy.getInstance().getMySQLConntion().query(query);
                                p.sendMessage(Shopy.getInstance().getPrefix() + "Zone erstellt!");
                        });
                    }else {
                        p.sendMessage(Shopy.getInstance().getPrefix() + "§cBitte zuerst eine Zone mit einer Holzschaufel markieren, damit eine Shopzone erstellt werden kann.");
                    }
                }else {
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cUngültige Parameter: /setshopzone <template>");

                }
            }else {
                p.sendMessage(Shopy.getInstance().getNoperm());
            }
        }
        return true;
    }
}
