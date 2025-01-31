package de.dome.shopy.listener.dungeon;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Dungeon;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class PlayerMoveListener implements Listener {

//    private final Set<Block> lavaBlocks = new HashSet<>();
    private ArrayList<Material> lavaBlocks = new ArrayList<>();
    public PlayerMoveListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void PlayerMove(PlayerMoveEvent e) {
        if(e.getFrom().getBlock() != e.getTo().getBlock()) {
            Player p = e.getPlayer();
            if(Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
                Dungeon spielerDungeon = Shopy.getInstance().getSpielerDungeon().get(p.getUniqueId());
                if(spielerDungeon.getDungeonSetAusgeruestet().containsKey("Enderman") && spielerDungeon.getDungeonSetAusgeruestet().get("Enderman").isBonus1()){
                    if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                        if (p.isOnGround()) {
                            p.setAllowFlight(true);
                        }
                    }
                }
                if(spielerDungeon.getDungeonSetAusgeruestet().containsKey("Kamikaze") && spielerDungeon.getDungeonSetAusgeruestet().get("Kamikaze").isBonus2()){
                    Block block = e.getFrom().subtract(0, 1, 0).getBlock();

                    if (block.getType() != Material.AIR && block.getType() != Material.GRASS && block.getType() != Material.MAGMA_BLOCK && block != null) {
                        lavaBlocks.add(block.getType());
                        block.setType(Material.MAGMA_BLOCK);

                        // Lava nach 5 Sekunden wieder entfernen
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                block.setType(lavaBlocks.get(0));
                                lavaBlocks.remove(0);
                            }
                        }.runTaskLater(Shopy.getInstance(), 60L); // 60 Ticks = 3 Sekunden
                    }
                }
            }
        }
    }
}
