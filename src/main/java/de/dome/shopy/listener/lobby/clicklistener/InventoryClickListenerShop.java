package de.dome.shopy.listener.lobby.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.manger.ShopDefaultItemsManger;
import de.dome.shopy.utils.shop.Shop;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class InventoryClickListenerShop implements Listener {

    public InventoryClickListenerShop() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (e.getView().getTitle().equals("§aShop Erstellen")) {
            if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
                if(item.getType() == Material.GRASS_BLOCK || item.getType() == Material.ICE || item.getType() == Material.SAND){
                    /* Welche Karte soll erstellt werden */
                    int templateID;

                    if(item.getType() == Material.GRASS_BLOCK) templateID = 1;
                    else if(item.getType() == Material.ICE) templateID = 2;
                    else if(item.getType() == Material.SAND) templateID = 3;
                    else return;


                    p.sendMessage(Shopy.getInstance().getPrefix() + "Dein Shop wird erstellt. Das kann einen §eAugenblick§7 dauern, du wirst anschließend dort hin §eteleportiert§7.");
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
                    p.closeInventory();

                    /* Erstelle einen neuen Spieler Shop*/
                    CompletableFuture<Void> shopErstellen = CompletableFuture.runAsync(() -> {
                        try {
                            String weltName = "sps_" + p.getUniqueId() + "_";
                            String vorlagenName = "";
                            Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop (owner, template, shop_level, shop_ordner, shop_zones) VALUES ('" + p.getUniqueId() + "', '" + templateID + "', 1, NULL, 1)");

                            String query = "SELECT * FROM shop WHERE owner = '" + p.getUniqueId() + "' ORDER BY id DESC";
                            ResultSet result =  Shopy.getInstance().getMySQLConntion().resultSet(query);

                            if (result.next()){
                                int shop_id = result.getInt("id");
                                weltName += shop_id;

                                String queryTemplate = "SELECT * FROM shop_template WHERE id = " + templateID;
                                ResultSet resultTemplate =  Shopy.getInstance().getMySQLConntion().resultSet(queryTemplate);

                                if (resultTemplate.next()){
                                    vorlagenName = resultTemplate.getString("template_ordner");
                                }else {
                                    p.sendMessage(Shopy.getInstance().getKonatktSupport());
                                    return;
                                }

                                File von = new File(Shopy.getInstance().getDataFolder().getPath() + "/image/" + vorlagenName);
                                File zu = new File(Shopy.getInstance().getDataFolder().getPath() + "/shop_welten/" + weltName);

                                Shopy.getInstance().kopiereOrdner(von, zu);

                                Shopy.getInstance().getMySQLConntion().query("UPDATE shop SET shop_ordner = '" + weltName +"' WHERE id = " + shop_id);

                                /*Shop werte */
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shop_id + "', 'ressourcen_lager', '5')");
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shop_id + "', 'item_lager', '10')");

                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shop_id + "', 'zusätzliche_kunden_spawn_zeit', '0')");
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shop_id + "', 'zusätzliche_kunden_pro_grundstück', '0')");
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shop_id + "', 'zusätzliche_kunden_wahrscheinlichkeit', '0')");
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shop_id + "', 'zusätzliches_item_wahrscheinlichkeit', '0')");
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shop_id + "', 'zusätzliche_kategorie_wahrscheinlichkeit', '0')");
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shop_id + "', 'zusätzlicher_verkaufserlös', '0')");
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shop_id + "', 'reduzierte_materialien_kosten', '0')");

                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shop_id + "', 'erledigte_handwerks_aufgaben', '0')");

                                /*Start geld*/
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_ressource (shop, ressource, menge) VALUES ('" + shop_id + "', '" + Ressource.getRessoureByName("Geld").getId() +"', '100')");
                            }else {
                                p.sendMessage(Shopy.getInstance().getPrefix() + "§cBeim Erstellen deines Shops ist ein Fehler aufgetreten, wende dich bitte an den §eSupport§c.");
                            }
                        } catch (SQLException erro) {} catch (IOException ex) {}
                    });
                    /* Wenn neue Shop Kopiert und gerneriert wurde, kann weiters passiern */
                    shopErstellen.thenRun(() -> {
                        new Shop(p, true);
                        p.sendMessage(Shopy.getInstance().getPrefix() + "Dein Shop wurde erstellt. Du kannst nun Loslegen.");

                        p.getInventory().addItem(ShopDefaultItemsManger.INSTANCE().getRessourcenMark());
                        p.getInventory().addItem(ShopDefaultItemsManger.INSTANCE().getWerkbank());
                        p.getInventory().addItem(ShopDefaultItemsManger.INSTANCE().getItemLager());
                        p.getInventory().addItem(ShopDefaultItemsManger.INSTANCE().getRessourcenLager());
                    });

                    return;
                }
            }
            e.setCancelled(true);
        }

        if (e.getView().getTitle().equals("§9Shop")) {
            if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
                if(item.getType() == Material.BARRIER) p.closeInventory();
                if(item.getType() == Material.NETHER_STAR){

                    if (!Shopy.getInstance().getPlayersNotTeleport().contains(p)) {
                        /* Countdown, damit Spieler erst nach 5 Sekunden zum Spawn teleportiert wird */
                        p.closeInventory();
                        Shopy.getInstance().getPlayersNotTeleport().add(p);

                        new BukkitRunnable() {
                            int countdownTime = 3;

                            @Override
                            public void run() {
                                if (countdownTime <= 0) {
                                    cancel();
                                    p.teleport(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopSpawn());
                                    p.sendMessage(Shopy.getInstance().getPrefix() + "§aDu wurdest zu deinem Shop Teleporiert.");
                                    if(Shopy.getInstance().getPlayersNotTeleport().contains(p)) Shopy.getInstance().getPlayersNotTeleport().remove(p);

                                    if(Shopy.getInstance().getGeladeneTempWelten().containsKey(p.getUniqueId())){
                                        for(World world : Shopy.getInstance().getGeladeneTempWelten().get(p.getUniqueId())){
                                            File file = world.getWorldFolder();
                                            Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
                                                Bukkit.unloadWorld(world, true);

                                                Shopy.getInstance().rekursivLoeschen(file);
                                            });
                                        }
                                        Shopy.getInstance().getGeladeneTempWelten().remove(p.getUniqueId());
                                    }

                                    /* Scoreboard Updateten */
                                    Shopy.getInstance().getScoreboardManger().setScoreBoard(p);
                                    if(Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
                                        p.getInventory().clear();
                                        for(ItemStack item : Shopy.getInstance().getSpielerDungeon().get(p.getUniqueId()).getSpielerInventrar()){
                                            if(item == null) continue;
                                            p.getInventory().addItem(item);
                                        }
                                        for (PotionEffect effect : p.getActivePotionEffects()) {
                                            p.removePotionEffect(effect.getType());
                                        }

                                        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                                        p.setFireTicks(0);

                                        Shopy.getInstance().getSpielerDungeon().remove(p.getUniqueId());
                                    }
                                } else {
                                    p.sendMessage(Shopy.getInstance().getPrefix() + "Du wirst in " + countdownTime + " Sekunden zu deinem Shop Teleporiert.");
                                    countdownTime--;
                                }
                            }
                        }.runTaskTimer(Shopy.getInstance(), 0L, 20L);
                    }else {
                        p.sendMessage(Shopy.getInstance().getPrefix() + "§cDu kannst dich zurzeit nicht Teleporieren");
                    }
                }else if(item.getType() == Material.HEART_OF_THE_SEA) {
                    Bukkit.dispatchCommand(p, "ressouren");
                }else if(item.getType() == Material.TRAPPED_CHEST) {
                    Bukkit.dispatchCommand(p, "itemlager");
                } else {
                    e.setCancelled(true);
                }
            }
        }
        if (e.getView().getTitle().equals("§9Shop - Ressourcen Übersicht")) {
            if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() &&  Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) {
                if (item.getType() == Material.OAK_WOOD) Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopInventarManger().openRessourenUbersicht("STANDART");
                if (item.getType() == Material.AMETHYST_SHARD) Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopInventarManger().openRessourenUbersicht("DUNGEON-LOOT");
                if (item.getType() == Material.ECHO_SHARD) Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopInventarManger().openRessourenUbersicht("SPECIAL");
                if (item.getType() == Material.BLUE_DYE) Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopInventarManger().openRessourenUbersicht("AUFWERTER");
            }
        }
    }
}
