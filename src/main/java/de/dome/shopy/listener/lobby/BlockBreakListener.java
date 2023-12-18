package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import org.bukkit.event.Listener;

public class BlockBreak implements Listener {

    public PlayerJoinListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, GrandShop.getInstance());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        /* Spieler zum Spawn bringen */
        if(GrandShop.getInstance().getServerSpawn() != null){
            p.teleport(GrandShop.getInstance().getServerSpawn());
        }

        /* Spieler eine willkommens nachricht senden */
        p.sendMessage(GrandShop.getInstance().getPrefix() + "Du brauchst Hilfe dabei, diesen Modus zu verstehen? Hier hast du eine Anleitung: XXX.");
        GrandShop.getInstance().getSpielerShops().put(p.getUniqueId(), new Shop(p.getUniqueId()));
    }



}
