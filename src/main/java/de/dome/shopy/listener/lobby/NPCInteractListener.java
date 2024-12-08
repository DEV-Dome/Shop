package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItemKategorie;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class NPCInteractListener implements Listener {

    public NPCInteractListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onNPCClick(NPCRightClickEvent event){
        Player p = event.getClicker();
        NPC npc = event.getNPC();

        /* Wenn der NPC keinen namen hat, soll er ignoiert werden */
        if(npc.getName().isEmpty()) return;

        if(npc.getFullName().equals("§aShop Verwalter")){
            if(!Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) {
                RyseInventory.builder().title("§aShop Erstellen").rows(3).provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {
                        ArrayList beschreibungNormalerShop = new ArrayList<>();
                        beschreibungNormalerShop.add("§7Hier findest du einen Shop, welcher in einer ganz normalen Umgebung ist.");

                        ArrayList beschreibungNochNichtVerfuegbar = new ArrayList<>();
                        beschreibungNochNichtVerfuegbar.add("§7Dieses Template, kann nicht genutzt werden. Es wird zu Späteren nachgereicht.");

                        contents.set(10, Shopy.getInstance().createItemWithLore(Material.BARRIER, "§cNoch nicht verfügbar", beschreibungNochNichtVerfuegbar));
                        contents.set(13, Shopy.getInstance().createItemWithLore(Material.GRASS_BLOCK, "§aNormaler Shop", beschreibungNormalerShop));
                        contents.set(16, Shopy.getInstance().createItemWithLore(Material.BARRIER, "§cNoch nicht verfügbar", beschreibungNochNichtVerfuegbar));
                    }

                }).build(Shopy.getInstance()).open(p);
            }else {
                Bukkit.dispatchCommand(p, "shop");
            }
        }else if(npc.getFullName().equals("§5Dungeon Händler")){
            RyseInventory.builder().title("§5Dungeon Händler").rows(3).provider(new InventoryProvider() {
                @Override
                public void init(Player player, InventoryContents contents) {
                    ArrayList beschreibung = new ArrayList<>();
                    beschreibung.add("§7Betrete einen Dungeon der Stufe 1,");
                    beschreibung.add("§7um besondere Ausrüstung zu bekommen.");
                    beschreibung.add("");
                    beschreibung.add("§7Für einen Dungeon der Stufe 1");
                    beschreibung.add("§7gelten folgende Bedingungen:");
                    beschreibung.add("");
                    beschreibung.add("§7  - 25 Monster (Monster Materialien Drop)");
                    beschreibung.add("§7  - Abschlussbelohnung (Besondere Materialien)");

                    contents.set(10, Shopy.getInstance().createItemWithLore(Material.ZOMBIE_SPAWN_EGG, "§5Dungeon Stufe 1", beschreibung));

                    beschreibung.clear();
                    beschreibung.add("§7Betrete einen Dungeon der Stufe 2,");
                    beschreibung.add("§7um besondere Ausrüstung zu bekommen.");
                    beschreibung.add("");
                    beschreibung.add("§7(Alle Erhört angaben beziehen sich auf Stufe 1)");
                    beschreibung.add("");
                    beschreibung.add("§7Für einen Dungeon der Stufe 2");
                    beschreibung.add("§7gelten folgende Bedingungen:");
                    beschreibung.add("");
                    beschreibung.add("§7  - 50 Monster (Monster Materialien Drop)");
                    beschreibung.add("§7  - + Erhöhte Chance Monster Materialien zu dropen");
                    beschreibung.add("§7  - Abschlussbelohnung (Besondere Materialien)");
                    beschreibung.add("§7  - + Erhöhte Chance Abschlussbelohnung zu dropen");

                    contents.set(12, Shopy.getInstance().createItemWithLore(Material.VINDICATOR_SPAWN_EGG, "§5Dungeon Stufe 2", beschreibung));

                    beschreibung.clear();
                    beschreibung.add("§7Betrete einen Dungeon der Stufe 3,");
                    beschreibung.add("§7um besondere Ausrüstung zu bekommen.");
                    beschreibung.add("");
                    beschreibung.add("§7(Alle Erhört angaben beziehen sich auf Stufe 2)");
                    beschreibung.add("");
                    beschreibung.add("§7Für einen Dungeon der Stufe 3");
                    beschreibung.add("§7gelten folgende Bedingungen:");
                    beschreibung.add("");
                    beschreibung.add("§7  - 100 Monster (Monster Materialien Drop)");
                    beschreibung.add("§7  - + Erhöhte Chance Monster Materialien zu dropen");
                    beschreibung.add("§7  - + Erhöhte Chance Speziale Materialien zu dropen");
                    beschreibung.add("§7  - Abschlussbelohnung (Besondere Materialien)");
                    beschreibung.add("§7  - + Erhöhte Chance Abschlussbelohnung zu dropen");

                    contents.set(14, Shopy.getInstance().createItemWithLore(Material.BLAZE_SPAWN_EGG, "§5Dungeon Stufe 3", beschreibung));

                    beschreibung.clear();
                    beschreibung.add("§7Betrete einen Dungeon der Stufe 4,");
                    beschreibung.add("§7um besondere Ausrüstung zu bekommen.");
                    beschreibung.add("");
                    beschreibung.add("§7(Alle Erhört angaben beziehen sich auf Stufe 3)");
                    beschreibung.add("");
                    beschreibung.add("§7Für einen Dungeon der Stufe 4");
                    beschreibung.add("§7gelten folgende Bedingungen:");
                    beschreibung.add("");
                    beschreibung.add("§7  - 200 Monster (Monster Materialien Drop)");
                    beschreibung.add("§7  - + Erhöhte Chance Monster Materialien zu dropen");
                    beschreibung.add("§7  - + Erhöhte Chance Speziale Materialien zu dropen");
                    beschreibung.add("§7  - Abschlussbelohnung (Besondere Materialien)");
                    beschreibung.add("§7  - + Erhöhte Chance Abschlussbelohnung zu dropen");
                    beschreibung.add("§7  - Es ist möglich set Materialien zu finden");

                    contents.set(16, Shopy.getInstance().createItemWithLore(Material.EVOKER_SPAWN_EGG, "§5Dungeon Stufe 4", beschreibung));

                }

            }).build(Shopy.getInstance()).open(p);
        }else if(npc.getFullName().equals("§dEinhorn Prinessin Mona")){
            RyseInventory.builder().title("§dEinhorn Prinessin Mona").rows(4).provider(new InventoryProvider() {
                @Override
                public void init(Player player, InventoryContents contents) {
                    Shop spielerShop =  Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

                    if(spielerShop.getZones().size() < spielerShop.getShopTemplateMaxGroße()){
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Erweiter deinen Shop, um mehr Kunden");
                        beschreibung.add("§7anzuziehen und mehr Geräte bauen zu können!");
                        beschreibung.add("");
                        beschreibung.add("§7Shopfläche: §e" + spielerShop.getZones().size() + " §7/ §e" + spielerShop.getShopTemplateMaxGroße());

                        contents.updateOrSet(10, Shopy.getInstance().createItemWithLore(Material.DIRT, "§5Shop Erweitern", beschreibung));
                    }else {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Du hast, die Maximale ausbaue stufe deines Shops erreicht!");

                        contents.updateOrSet(10, Shopy.getInstance().createItemWithLore(Material.GRASS_BLOCK, "§aGlückwunsch!", beschreibung));
                    }

                    String nochNichtFreigeschlatetKategorien = "";
                    int freigeschlateKategorien = 0;
                    for(ShopItemKategorie shopItemKategorie : spielerShop.getShopItemKategorie().values()){
                        if(!shopItemKategorie.isFreigeschaltet()){
                            nochNichtFreigeschlatetKategorien += shopItemKategorie.getItemKategorie().getName() + ", ";
                        }else freigeschlateKategorien++;
                    }

                    if(freigeschlateKategorien != spielerShop.getShopItemKategorie().size()){
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Schalte eine neue Item Kategorie Frei");
                        beschreibung.add("");
                        beschreibung.add("§7Folgende sind noch nicht freigeschaltet:");
                        beschreibung.add("§e" + nochNichtFreigeschlatetKategorien);

                        contents.updateOrSet(11, Shopy.getInstance().createItemWithLore(Material.CRAFTING_TABLE, "§5Crafting Kategorie Freischalten", beschreibung));
                    }else {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Du hast, alle Kategorien freigeschaltet!");

                        contents.updateOrSet(11, Shopy.getInstance().createItemWithLore(Material.CRAFTING_TABLE, "§aGlückwunsch", beschreibung));
                    }


                    contents.set(12, Shopy.getInstance().createItem(Material.NETHERITE_SWORD, "§5Neue Item Stufe Freischalten"));
                }

                @Override
                public void update(Player player, InventoryContents contents) {
                    init(player, contents);
                }
            }).build(Shopy.getInstance()).open(p);

        }

    }

}
