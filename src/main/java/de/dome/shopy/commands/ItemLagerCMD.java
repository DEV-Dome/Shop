package de.dome.shopy.commands;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ItemLagerCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            Shop shop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

            if(Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())){
                RyseInventory.builder().title("§9Item Lager")
                        .rows(6)
                        .provider(new InventoryProvider() {
                            @Override
                            public void init(Player player, InventoryContents contents) {
                               for(int i = 0; i <= (5 * 9) - 1 ;i++){
                                    if(i <= shop.getShopItems().size() - 1){
                                        /* Item laden */
                                        ShopItem si = shop.getShopItems().get(i);

                                        ArrayList beschreibung = new ArrayList();
                                        beschreibung.add("");
                                        beschreibung.add(si.getBeschreibung());

                                        contents.set(i, Shopy.getInstance().createItemWithLore(si.getIcon(), "§9" + si.getName(), beschreibung));
                                    }else {
                                        if(i > shop.getItemLager() - 1){
                                            ArrayList beschreibung = new ArrayList();
                                            beschreibung.add("");
                                            beschreibung.add("§7Du kannst diesen Solt freischalten, in dem du mehr Item Lager in deinem Shop aufbaust.");

                                            contents.set(i, Shopy.getInstance().createItemWithLore(Material.GRAY_DYE, "§7Solt noch nicht freigeschaltet", beschreibung));
                                        }
                                    }
                                }
                                contents.set(45, Shopy.getInstance().createItem(Material.TRAPPED_CHEST, "§7zurück zur Übersicht"));
                                contents.set(53, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));
                            }
                        }).build(Shopy.getInstance()).open(p);
            }else {
                p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast dir noch keinen Shop erstellt. Dieses kannst du beim §eWelten Ersteller§7 tun.");
            }
        }else {
            sender.sendMessage(Shopy.getInstance().getPrefix() + "§cDas können nur Spieler ausführen!");
        }
        return true;
    }


}
