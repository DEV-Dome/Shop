package de.dome.shopy.listener.dungeon;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import de.dome.shopy.Shopy;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerArmorChangeListener implements Listener {

    public PlayerArmorChangeListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onPlayer(PlayerArmorChangeEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getNewItem();
        ItemStack itemOld = e.getOldItem();
        if (!Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) return;
        if (!Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())) return;

        Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

        /* Wird ein neues Verzaubers Rüstungsteil angelegt */
        if(item.hasItemMeta() && item.getItemMeta().hasLore()){
            String[] lorearray = item.getItemMeta().getLore().get(0).split(":");

            if(lorearray[0].equals("§7Item-ID")){
                int itemID = Integer.parseInt(lorearray[1].substring(1));
                ShopItem shopItem = spielerShop.getShopItemById(itemID);

                if(shopItem.getItemVerzauberung() != null){
                    if(shopItem.getItemVerzauberung().getName().equals("Teufel")) p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, true, false));
                    if(shopItem.getItemVerzauberung().getName().equals("Geist")) p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, true, false));

                    if(shopItem.getItemVerzauberung().getName().equals("Waschbrettbauch")) p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0, true, false));
                    if(shopItem.getItemVerzauberung().getName().equals("Barbar")) p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, true, false));

                    if(shopItem.getItemVerzauberung().getName().equals("Panter")) p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, true, false));
                    if(shopItem.getItemVerzauberung().getName().equals("Hase")) p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 0, true, false));
                    if(shopItem.getItemVerzauberung().getName().equals("Fee")) p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, Integer.MAX_VALUE, 0, true, false));
                }
            }
        }

        /* Wird ein  Verzaubers Rüstungsteil abgelegt wird */
        if(itemOld.hasItemMeta() && itemOld.getItemMeta().hasLore()){
            String[] lorearray = itemOld.getItemMeta().getLore().get(0).split(":");

            if(lorearray[0].equals("§7Item-ID")){
                int itemID = Integer.parseInt(lorearray[1].substring(1));
                ShopItem shopItem = spielerShop.getShopItemById(itemID);

                if(shopItem.getItemVerzauberung() != null){
                    if(shopItem.getItemVerzauberung().getName().equals("Teufel")) p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                    if(shopItem.getItemVerzauberung().getName().equals("Geist")) p.removePotionEffect(PotionEffectType.INVISIBILITY);

                    if(shopItem.getItemVerzauberung().getName().equals("Waschbrettbauch")) p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                    if(shopItem.getItemVerzauberung().getName().equals("Barbar")) p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE );

                    if(shopItem.getItemVerzauberung().getName().equals("Panter")) p.removePotionEffect(PotionEffectType.SPEED );
                    if(shopItem.getItemVerzauberung().getName().equals("Hase")) p.removePotionEffect(PotionEffectType.JUMP );
                    if(shopItem.getItemVerzauberung().getName().equals("Fee")) p.removePotionEffect(PotionEffectType.SLOW_FALLING );
                }
            }
        }
    }
}
