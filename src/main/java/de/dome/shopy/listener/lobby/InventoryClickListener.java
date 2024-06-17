package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Dungeon;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.shop.Shop;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
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

                                Shopy.getInstance().getMySQLConntion().query("UPDATE shop SET shop_ordner = '" + weltName +"' WHERE id = " + shop_id);

                                /*Shop werte */
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shop_id + "', 'ressourcen_lager', '5')");
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_werte (shop, schlussel, inhalt) VALUES ('" + shop_id + "', 'item_lager', '10')");

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

                        /* Starter Itemes geben */
                        String buildingHinweis  = "§7Dieser Gegenstand kann nur in der Shop-Welt platziert werden. Und entfaltet da einen besonderen Effet der über das, gewöhnlich Maß in Minecraft hinaus geht.";

                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§5Mithilfe dieses Gegenstandes können Ressourcen gekauft werden.");
                        beschreibung.add("");
                        beschreibung.add(buildingHinweis);

                        p.getInventory().addItem(Shopy.getInstance().createItemWithLore(Material.LECTERN, "§9Ressourcen Mark", beschreibung));

                        beschreibung = new ArrayList<>();
                        beschreibung.add("§5Auf diesem Gegenstand können mächtige Waffen und Werkzeuge hergestellt werden.");
                        beschreibung.add("");
                        beschreibung.add(buildingHinweis);

                        p.getInventory().addItem(Shopy.getInstance().createItemWithLore(Material.CRAFTING_TABLE, "§9Werkbank", beschreibung));

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
                if (item.getType() == Material.OAK_WOOD) Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openRessourenUbersicht("STANDART");
                if (item.getType() == Material.AMETHYST_SHARD) Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openRessourenUbersicht("DUNGEON-LOOT");
                if (item.getType() == Material.ECHO_SHARD) Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openRessourenUbersicht("SPECIAL");
                if (item.getType() == Material.BLUE_DYE) Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).openRessourenUbersicht("AUFWERTER");
            }
        }

        if (e.getView().getTitle().equals("§5Dungeon Händler")) {
            if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                switch (item.getType()){
                    case ZOMBIE_SPAWN_EGG:
                        Shopy.getInstance().getSpielerDungeon().put(p.getUniqueId(), new Dungeon(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()), 1));
                        break;
                    case VINDICATOR_SPAWN_EGG:
                        Shopy.getInstance().getSpielerDungeon().put(p.getUniqueId(), new Dungeon(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()), 2));
                        break;
                    case BLAZE_SPAWN_EGG:
                        Shopy.getInstance().getSpielerDungeon().put(p.getUniqueId(), new Dungeon(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()), 3));
                        break;
                    case EVOKER_SPAWN_EGG:
                        Shopy.getInstance().getSpielerDungeon().put(p.getUniqueId(), new Dungeon(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()), 4));
                        break;
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
