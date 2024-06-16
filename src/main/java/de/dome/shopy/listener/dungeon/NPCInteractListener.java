package de.dome.shopy.listener.dungeon;

import de.dome.shopy.Shopy;
import dev.sergiferry.playernpc.api.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCInteractListener implements Listener {

    public NPCInteractListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onNPCInteract(NPC.Events.Interact event){
        Player p = event.getPlayer();
        NPC npc = event.getNPC();

        /* Wenn der NPC keinen namen hat, soll er ignoiert werden */
        if(npc.getText().isEmpty()) return;

        if(Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
            if(npc.getText().get(0).equals("§9Waffenlager")){
                Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openItemLagerInventar(0);
            }
        }
    }
}
