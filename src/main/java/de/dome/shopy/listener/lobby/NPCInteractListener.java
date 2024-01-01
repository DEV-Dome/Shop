package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import dev.sergiferry.playernpc.api.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class NPCInteractListener implements Listener {

    public NPCInteractListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onNPCInteract(NPC.Events.Interact event){
        Player p = event.getPlayer();
        NPC npc = event.getNPC();

        if(npc.getText().get(0).equals("§aShop Ersteller")){
            if(!Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) {
                Inventory inv = Bukkit.createInventory(null, 27, "§aShop Erstellen");

                ArrayList beschreibungNormalerShop = new ArrayList<>();
                beschreibungNormalerShop.add("§7Hier findest du einen Shop, welcher in einer ganz normalen Umgebung ist.");

                ArrayList beschreibungNochNichtVerfuegbar = new ArrayList<>();
                beschreibungNochNichtVerfuegbar.add("§7Dieses Template, kann nicht genutzt werden. Es wird zu Späteren nachgereicht.");

                inv.setItem(10, Shopy.getInstance().createItemWithLore(Material.BARRIER, "§cNoch nicht verfügbar", beschreibungNochNichtVerfuegbar));
                inv.setItem(13, Shopy.getInstance().createItemWithLore(Material.GRASS_BLOCK, "§aNormaler Shop", beschreibungNormalerShop));
                inv.setItem(16, Shopy.getInstance().createItemWithLore(Material.BARRIER, "§cNoch nicht verfügbar", beschreibungNochNichtVerfuegbar));

                p.openInventory(inv);
            }else {
                Bukkit.dispatchCommand(p, "shop");
            }
        }
    }


}
