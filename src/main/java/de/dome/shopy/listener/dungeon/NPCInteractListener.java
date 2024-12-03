package de.dome.shopy.listener.dungeon;

import de.dome.shopy.Shopy;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCInteractListener implements Listener {

    public NPCInteractListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onNPCInteract(NPCRightClickEvent event){
        Player p = event.getClicker();
        NPC npc = event.getNPC();

        /* Wenn der NPC keinen namen hat, soll er ignoiert werden */
        if(npc.getFullName().isEmpty()) return;

        if(Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
            if(npc.getFullName().equals("§9Waffenlager")){
                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openItemLagerInventar(0);
            }
        }
    }
}
