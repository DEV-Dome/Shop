package de.dome.shopy.commands;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressoure;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RessourenCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())){
                RyseInventory.builder().title("§9Shop")
                        .rows(4)
                        .provider(new InventoryProvider() {
                            @Override
                            public void init(Player player, InventoryContents contents) {
                                int solt = 10;
                                int zaheler = 0;
                                for (Map.Entry<Ressoure, Integer> shopRessoure  : Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger().getShopRessoure().entrySet()) {
                                    Ressoure ressoure = shopRessoure.getKey();

                                    ArrayList<String> beschreibung = new ArrayList<>();
                                    beschreibung.add("§7Menge: §e" + shopRessoure.getValue());
                                    beschreibung.add("");
                                    beschreibung.add("§5" + ressoure.getBeschreibung());

                                    contents.set(solt, Shopy.getInstance().createItemWithLore(ressoure.getIcon(), "§9" + ressoure.getName(), beschreibung));

                                    /* Items Anordenen */
                                    zaheler++;
                                    if(zaheler == 7){
                                        solt += 3;
                                        zaheler = 0;
                                    }else {
                                        solt++;
                                    }
                                }

                            }
                        }).build(Shopy.getInstance()).open(p);
            }else {
                p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast dir noch keinen Shop erstellt. Dieses kannst du beim §eWelten Ersteller§7 tun.");
            }
        }
        return true;
    }
}
