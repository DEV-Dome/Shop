package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.Item;
import de.dome.shopy.utils.items.ItemRessourecenKosten;
import de.dome.shopy.utils.shop.ShopItemVorlage;
import dev.sergiferry.playernpc.api.NPC;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class NPCInteractListener implements Listener {

    public NPCInteractListener() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onNPCInteract(NPC.Events.Interact event){
        Player p = event.getPlayer();
        NPC npc = event.getNPC();

        /* Wenn der NPC keinen namen hat, soll er ignoiert werden */
        if(npc.getText().isEmpty()) return;

        if(npc.getText().get(0).equals("§aShop Verwalter")){
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
        }else if(npc.getText().get(0).equals("§5Dungeon Händler")){
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
        }
    }


}
