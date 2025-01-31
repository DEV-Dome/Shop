package de.dome.shopy.listener.dungeon;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.DungeonSetAusgeruestet;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDamageByEntityEventListener implements Listener {

    public EntityDamageByEntityEventListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void entityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if (!Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) return;
            if(!Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())) return;

            ItemStack item = p.getInventory().getItemInMainHand();
            Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());


            if(item.hasItemMeta() && item.getItemMeta().hasLore()) {
                String[] lorearray = item.getItemMeta().getLore().get(0).split(":");
                if (lorearray[0].equals("§7Item-ID")) {
                    int itemID = Integer.parseInt(lorearray[1].substring(1));
                    ShopItem shopItem = spielerShop.getShopItemById(itemID);
                    if(shopItem.getItemVerzauberung() != null){
                        if(shopItem.getItemVerzauberung().getName().equals("Dieb")){
                            double heileAuf = p.getHealth() + 1;
                            if(shopItem.getItemSeltenheit().getId() == 3) heileAuf += 0.5;
                            if(shopItem.getItemSeltenheit().getId() == 4) heileAuf += 1;
                            if(shopItem.getItemSeltenheit().getId() == 5) heileAuf += 1.5;
                            if(heileAuf >= 20) heileAuf = 20;

                            p.setHealth(heileAuf);
                        }
                        if(shopItem.getItemVerzauberung().getName().equals("Kabum")){
                            World exploadWorld = e.getEntity().getLocation().getWorld();
                            Location exploadLocation = e.getEntity().getLocation();

                            float radius = 0.5F;
                            if(shopItem.getItemSeltenheit().getId() == 3) radius = 0.75f;
                            if(shopItem.getItemSeltenheit().getId() == 4) radius = 1;
                            if(shopItem.getItemSeltenheit().getId() == 5) radius = 1.25F;

                            exploadWorld.createExplosion(exploadLocation.getX(), exploadLocation.getY(), exploadLocation.getZ(), radius, false, false);
                        }
                        if(shopItem.getItemVerzauberung().getName().equals("Igel")){
                            LivingEntity livingEntity = (LivingEntity) e.getEntity();

                            double damage = 1;
                            if(shopItem.getItemSeltenheit().getId() == 3) damage = 2;
                            if(shopItem.getItemSeltenheit().getId() == 4) damage = 3;
                            if(shopItem.getItemSeltenheit().getId() == 5) damage = 4;

                            livingEntity.damage(damage);
                        }
                        if(shopItem.getItemVerzauberung().getName().equals("Volltreffer")){
                            double multiply = 1;
                            if(shopItem.getItemSeltenheit().getId() == 3) multiply = 1.5;
                            if(shopItem.getItemSeltenheit().getId() == 4) multiply = 2;
                            if(shopItem.getItemSeltenheit().getId() == 5) multiply = 2.5;

                            e.getEntity().setVelocity(p.getEyeLocation().getDirection().multiply(multiply));
                        }
                    }
                }
            }
        } else if (e.getDamager() instanceof Projectile) {
            if(e.getEntity() instanceof Player ) {
                Player p = (Player) e.getEntity();


                ItemStack leggins = p.getInventory().getLeggings();
                Projectile projectile = (Projectile) e.getDamager();

                if (leggins != null && leggins.hasItemMeta() && leggins.getItemMeta().hasLore()) {

                    String[] legginsLoreArray = leggins.getItemMeta().getLore().get(0).split(":");
                    if (legginsLoreArray[0].equals("§7Item-ID")) {
                        int legginsItemID = Integer.parseInt(legginsLoreArray[1].substring(1));
                        ShopItem legginsShopItem = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopItemById(legginsItemID);

                        if (legginsShopItem.getItemVerzauberung() != null && legginsShopItem.getItemVerzauberung().getName().equals("Spinne")) {
                            if (projectile.getShooter() instanceof Entity) {

                                Entity schooter = (Entity) projectile.getShooter();
                                Location targetLocation = p.getEyeLocation().add(p.getEyeLocation().getDirection().multiply(2));

                                schooter.teleport(targetLocation);
                            }
                        }
                    }
                }
            }
        }

    }
}
