package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Shop;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String weltName = "sps_" + p.getUniqueId() + "_";
                                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop (owner, shop_level, shop_ordner) VALUES ('" + p.getUniqueId() + "', 1, NULL)");

                                String query = "SELECT * FROM shop WHERE owner = '" + p.getUniqueId() + "' ORDER BY id DESC";
                                ResultSet result =  Shopy.getInstance().getMySQLConntion().resultSet(query);

                                if (result.next()){
                                    int shop_id = result.getInt("id");
                                    weltName += shop_id;

                                    File von = new File(Shopy.getInstance().getDataFolder().getPath() + "/image/vorlage1");
                                    File zu = new File(Shopy.getInstance().getDataFolder().getPath() + "/shop_welten/" + weltName);

                                    kopiereOrdner(von, zu);
                                    Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + " " + zu.toPath().toString());

                                    Shopy.getInstance().getServer().getScheduler().runTask(Shopy.getInstance(), new Runnable() {
                                        @Override
                                        public void run() {
                                            WorldCreator creator = new WorldCreator(zu.getPath());

                                            creator.environment(World.Environment.NORMAL);
                                            creator.type(WorldType.NORMAL);

                                            World neueWelt = creator.createWorld();

                                            p.teleport(neueWelt.getSpawnLocation());
                                            p.sendMessage(Shopy.getInstance().getPrefix() + "Dein Shop wurde erstellt. Du kannst nun Loslegen.");
                                            new Shop(p.getUniqueId());
                                        }
                                    });

                                    Shopy.getInstance().getMySQLConntion().query("UPDATE shop SET shop_ordner = '" + weltName +"' WHERE id = " + shop_id);
                                }else {
                                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cBeim Erstellen deines Shops ist ein Fehler aufgetreten, wende dich bitte an den §eSupport§c.");
                                }
                            } catch (SQLException e) {} catch (IOException ex) {}
                        }

                    }).start();

                    return;
                }
            }
            e.setCancelled(true);
        }

        if (e.getView().getTitle().equals("§9Shop")) {
            if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
                if(item.getType() == Material.NETHER_STAR){

                    p.teleport(Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getWorld().getSpawnLocation());

                }else {
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
