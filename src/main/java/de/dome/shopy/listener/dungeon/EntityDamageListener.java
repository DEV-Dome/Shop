package de.dome.shopy.listener.dungeon;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Dungeon;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDamageListener implements Listener {

    public EntityDamageListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void entityDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();

            if(Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
                Dungeon spielerDungeon = Shopy.getInstance().getSpielerDungeon().get(p.getUniqueId());
                if(spielerDungeon.isSchutzPhase()){
                    e.setCancelled(true);
                }
            }
        }
    }

}
