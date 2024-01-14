package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressoure;
import de.dome.shopy.utils.RessourenShopManger;
import de.dome.shopy.utils.Shop;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class InventoryClickListener implements Listener {

    public InventoryClickListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (e.getView().getTitle().equals("§aShop Erstellen")) {
            if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
                if(item.getType() == Material.GRASS_BLOCK){
                    p.sendMessage(Shopy.getInstance().getPrefix() + "Dein Shop wird erstellt. Das kann einen §eAugenblick§7 dauern, du wirst anschließend dort hin §eteleportiert§7.");
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
                    p.closeInventory();

                    /* Erstelle einen neuen Spieler Shop*/
                    CompletableFuture<Void> shopErstellen = CompletableFuture.runAsync(() -> {
                        try {
                            String weltName = "sps_" + p.getUniqueId() + "_";
                            Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop (owner, template, shop_level, shop_ordner, shop_zones) VALUES ('" + p.getUniqueId() + "', 1, 1, NULL, 1)");

                            String query = "SELECT * FROM shop WHERE owner = '" + p.getUniqueId() + "' ORDER BY id DESC";
                            ResultSet result =  Shopy.getInstance().getMySQLConntion().resultSet(query);

                            if (result.next()){
                                int shop_id = result.getInt("id");
                                weltName += shop_id;

                                File von = new File(Shopy.getInstance().getDataFolder().getPath() + "/image/vorlage1");
                                File zu = new File(Shopy.getInstance().getDataFolder().getPath() + "/shop_welten/" + weltName);

                                kopiereOrdner(von, zu);
                                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + " " + zu.toPath().toString());

                                Shopy.getInstance().getMySQLConntion().query("UPDATE shop SET shop_ordner = '" + weltName +"' WHERE id = " + shop_id);

                                /*Shop werte */
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, wert, value) VALUES ('" + shop_id + "', 'ressourcen_lager', '5')");
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, wert, value) VALUES ('" + shop_id + "', 'item_lager', '10')");

                                /*Start geld*/
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_ressource (shop, ressource, menge) VALUES ('" + shop_id + "', '" + Ressoure.getRessoureByName("Geld").getId() +"', '100')");

                            }else {
                                p.sendMessage(Shopy.getInstance().getPrefix() + "§cBeim Erstellen deines Shops ist ein Fehler aufgetreten, wende dich bitte an den §eSupport§c.");
                            }
                        } catch (SQLException erro) {} catch (IOException ex) {}
                    });
                    /* Wenn neue Shop Kopiert und gerneriert wurde, kann weiters passiern */
                    shopErstellen.thenRun(() -> {
                        new Shop(p.getUniqueId(), true);
                        p.sendMessage(Shopy.getInstance().getPrefix() + "Dein Shop wurde erstellt. Du kannst nun Loslegen.");

                        /* Starter Itemes geben */
                        String buildingHinweis  = "§7Dieser Gegenstand kann nur in der Shop-Welt platziert werden. Und entfaltet da einen besonderen Effet der über das, gewöhnlich Maß in Minecraft hinaus geht.";

                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§5Mithilfe dieses Gegenstandes können Ressourcen gekauft werden.");
                        beschreibung.add("");
                        beschreibung.add(buildingHinweis);

                        p.getInventory().addItem(Shopy.getInstance().createItemWithLore(Material.LECTERN, "§9Ressourcen Mark", beschreibung));

                        beschreibung = new ArrayList<>();
                        beschreibung.add("§5Pro Item lager können 10 hergestellte Items gelagert werden.");
                        beschreibung.add("");
                        beschreibung.add(buildingHinweis);

                        p.getInventory().addItem(Shopy.getInstance().createItemWithLore(Material.TRAPPED_CHEST, "§9Item Lager", beschreibung));

                        beschreibung = new ArrayList<>();
                        beschreibung.add("§5Pro Ressourcen lager können 10 Ressourcen gelagert werden");
                        beschreibung.add("");
                        beschreibung.add(buildingHinweis);

                        p.getInventory().addItem(Shopy.getInstance().createItemWithLore(Material.BARREL, "§9Ressourcen Lager", beschreibung));
                    });

                    return;
                }
            }
            e.setCancelled(true);
        }

        if (e.getView().getTitle().equals("§9Shop")) {
            if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
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
                                    p.teleport(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getSpawnLocation());
                                    p.sendMessage(Shopy.getInstance().getPrefix() + "§aDu wurdest zu deinem Shop Teleporiert.");
                                    if(Shopy.getInstance().getPlayersNotTeleport().contains(p)) Shopy.getInstance().getPlayersNotTeleport().remove(p);
                                } else {
                                    p.sendMessage(Shopy.getInstance().getPrefix() + "Du wirst in " + countdownTime + " Sekunden zum Spawn Teleporiert.");
                                    countdownTime--;
                                }
                            }
                        }.runTaskTimer(Shopy.getInstance(), 0L, 20L);
                    }else {
                        p.sendMessage(Shopy.getInstance().getPrefix() + "§cDu kannst dich zurzeit nicht Teleporieren");
                    }
                }else if(item.getType() == Material.HEART_OF_THE_SEA) {
                    Bukkit.dispatchCommand(p, "ressouren");
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }



    public void kopiereOrdner(File quelle, File ziel) throws IOException {
        // Prüfen, ob die Quelle ein Verzeichnis ist
        if (quelle.isDirectory()) {
            // Wenn das Zielverzeichnis nicht existiert, erstellen wir es
            if (!ziel.exists()) {
                ziel.mkdir();
            }

            // Liste der Dateien und Unterverzeichnisse im Quellverzeichnis abrufen
            String[] dateien = quelle.list();

            if (dateien != null) {
                for (String datei : dateien) {
                    // Rekursiv jeden Eintrag kopieren
                    kopiereOrdner(new File(quelle, datei), new File(ziel, datei));
                }
            }
        } else {
            // Wenn die Quelle eine Datei ist, diese kopieren
            Path quellePfad = quelle.toPath();
            Path zielPfad = ziel.toPath();
            Files.copy(quellePfad, zielPfad, StandardCopyOption.REPLACE_EXISTING);
        }
    }

}
