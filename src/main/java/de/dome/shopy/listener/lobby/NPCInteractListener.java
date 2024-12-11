package de.dome.shopy.listener.lobby;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.manger.NpcManger;
import de.dome.shopy.utils.manger.ShopDefaultItemsManger;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItemKategorie;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

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


        if(NpcManger.INSTANCE().getErsteller().getUniqueId() == npc.getUniqueId()){
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
            return;
        }
        /* Wenn kein Shop vorhanden */
        if(!Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) {
            p.sendMessage(Shopy.getInstance().getPrefix() + "§cUm diesen Händler Benutzen zu können, musst erst einen Shop gründen!");
            return;
        }

        if(NpcManger.INSTANCE().getDungeonHaendler().getUniqueId() == npc.getUniqueId()){
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
        }else if(NpcManger.INSTANCE().getMona().getUniqueId() == npc.getUniqueId()){
            RyseInventory.builder().title("§dEinhorn Prinessin Mona").rows(4).provider(new InventoryProvider() {
                @Override
                public void init(Player player, InventoryContents contents) {
                    Shop spielerShop =  Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

                    if(spielerShop.getZones().size() < spielerShop.getShopTemplateMaxGroße()){
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Kosten: §e1 Einhornkristall");
                        beschreibung.add("");
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
                        beschreibung.add("§7Kosten: §e1 Einhornkristall");
                        beschreibung.add("");
                        beschreibung.add("§7Schalte eine neue Item Kategorie Frei");
                        beschreibung.add("");
                        beschreibung.add("§7Folgende sind noch nicht freigeschaltet:");
                        beschreibung.add("§e" + nochNichtFreigeschlatetKategorien);

                        contents.updateOrSet(11, Shopy.getInstance().createItemWithLore(Material.CRAFTING_TABLE, "§5Crafting Kategorie Freischalten", beschreibung));
                    }else {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Du hast, alle Kategorien freigeschaltet!");

                        contents.updateOrSet(11, Shopy.getInstance().createItemWithLore(Material.CALIBRATED_SCULK_SENSOR, "§aGlückwunsch", beschreibung));
                    }


                    contents.updateOrSet(12, Shopy.getInstance().createItem(Material.NETHERITE_SWORD, "§5Neue Item Stufe Freischalten"));

                    /* Kunden Perks */
                    /* Spawnzeit Reduzieren */
                    if(spielerShop.getReduzierteKundenSpawnZeit() == 15){
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Du hast die Maximal Spawnzeit Reduzierung erreicht!");
                        beschreibung.add("");
                        beschreibung.add("§7Spawnzeit (in Sekunden): §e" + (60 - spielerShop.getReduzierteKundenSpawnZeit()) + " §7/ §e45");

                        contents.updateOrSet(19, Shopy.getInstance().createItemWithLore(Material.CLOCK, "§aGlückwunsch", beschreibung));
                    }else {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Kosten: §e1 §7Einhornkristall");
                        beschreibung.add("");
                        beschreibung.add("§7Hier kannst du die Spawnzeit der Kunden Verbessern:");
                        beschreibung.add("");
                        beschreibung.add("§7Spawnzeit (in Sekunden): §e" + (60 - spielerShop.getReduzierteKundenSpawnZeit()) + " §7/ §e45");

                        contents.updateOrSet(19, Shopy.getInstance().createItemWithLore(Material.CLOCK, "§5Spawnzeit Reduzieren", beschreibung));
                    }

                    /* Kunden Menge pro Grundstück */
                    if(spielerShop.getZusaetzlicheKundenProGrunstueck() == 2 ){
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Du hast die Maximalen Kunden");
                        beschreibung.add("§7pro Grundstück erreicht!");
                        beschreibung.add("");
                        beschreibung.add("§7Kunden pro Grundstück: §e" + (spielerShop.getZusaetzlicheKundenProGrunstueck() + 2) + " §7/ §e4");

                        contents.updateOrSet(20, Shopy.getInstance().createItemWithLore(Material.ARMOR_STAND, "§aGlückwunsch", beschreibung));
                    }else {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Kosten: §e1 §7Einhornkristall");
                        beschreibung.add("");
                        beschreibung.add("§7Erhöhe die Maximal anzahl an kunden,");
                        beschreibung.add("");
                        beschreibung.add("§7Kunden pro Grundstück: §e" + (spielerShop.getZusaetzlicheKundenProGrunstueck() + 2) + " §7/ §e4");

                        contents.updateOrSet(20, Shopy.getInstance().createItemWithLore(Material.ARMOR_STAND, "§5Maximale Kunden Menge pro Grundstück", beschreibung));
                    }

                    /* Wahrscheinlichkeit das Kunden kommen */
                    if(spielerShop.getZusaetzlicheKundenWahrscheinlichkeit() == 15){
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Du hast die Maximale Wahrscheinlichkeit");
                        beschreibung.add("§7das Kunden kommen erreicht!");
                        beschreibung.add("");
                        beschreibung.add("§7Wahrscheinlichkeit: §e" + (spielerShop.getZusaetzlicheKundenWahrscheinlichkeit() + 55) + "% §7/ §e70%");

                        contents.updateOrSet(21, Shopy.getInstance().createItemWithLore(Material.WARPED_FUNGUS_ON_A_STICK, "§aGlückwunsch", beschreibung));
                    }else {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Kosten: §e1 §7Einhornkristall");
                        beschreibung.add("");
                        beschreibung.add("§7Erhöhe die Wahrscheinlichkeit dass");
                        beschreibung.add("§7kunden kommen:");
                        beschreibung.add("");
                        beschreibung.add("§7Wahrscheinlichkeit: §e" + (spielerShop.getZusaetzlicheKundenWahrscheinlichkeit() + 55) + "% §7/ §e70%");

                        contents.updateOrSet(21, Shopy.getInstance().createItemWithLore(Material.CARROT_ON_A_STICK, "§5Wahrscheinlichkeit das Kunden kommen", beschreibung));
                    }

                    /* Wahrscheinlichkeit auf zusätzliches Item*/
                    if(spielerShop.getZusaetzlichesItemWahrscheinlichkeit() == 15){
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Du hast die Maximale Wahrscheinlichkeit");
                        beschreibung.add("§7auf zusätzliches Item erreicht!");
                        beschreibung.add("");
                        beschreibung.add("§7Wahrscheinlichkeit: §e" + (spielerShop.getZusaetzlichesItemWahrscheinlichkeit() + 10) + "% §7/ §e25%");

                        contents.updateOrSet(22, Shopy.getInstance().createItemWithLore(Material.TOTEM_OF_UNDYING, "§aGlückwunsch", beschreibung));
                    }else {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Kosten: §e1 §7Einhornkristall");
                        beschreibung.add("");
                        beschreibung.add("§7Erhöhe die Wahrscheinlichkeit dass");
                        beschreibung.add("§7kunden Mehrere Item kaufen:");
                        beschreibung.add("");
                        beschreibung.add("§7Wahrscheinlichkeit: §e" + (spielerShop.getZusaetzlichesItemWahrscheinlichkeit() + 10) + "% §7/ §e25%");

                        contents.updateOrSet(22, Shopy.getInstance().createItemWithLore(Material.TOTEM_OF_UNDYING, "§5Wahrscheinlichkeit auf zusätzliches Item", beschreibung));
                    }

                    /* Wahrscheinlichkeit auf zusätzliche Kategorie*/
                    if(spielerShop.getZusaetzlicheKategorieWahrscheinlichkeit() == 15){
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Du hast die Maximale Wahrscheinlichkeit");
                        beschreibung.add("§7auf zusätzliche Kategorie erreicht!");
                        beschreibung.add("");
                        beschreibung.add("§7Wahrscheinlichkeit: §e" + (spielerShop.getZusaetzlicheKategorieWahrscheinlichkeit() + 25) + "% §7/ §e40%");

                        contents.updateOrSet(23, Shopy.getInstance().createItemWithLore(Material.BRUSH, "§aGlückwunsch", beschreibung));
                    }else {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Kosten: §e1 §7Einhornkristall");
                        beschreibung.add("");
                        beschreibung.add("§7Erhöhe die Wahrscheinlichkeit dass kunden");
                        beschreibung.add("§7an Mehrere Kategorien Intresse haben:");
                        beschreibung.add("");
                        beschreibung.add("§7Wahrscheinlichkeit: §e" + (spielerShop.getZusaetzlicheKategorieWahrscheinlichkeit() + 25) + "% §7/ §e40%");

                        contents.updateOrSet(23, Shopy.getInstance().createItemWithLore(Material.BRUSH, "§5Wahrscheinlichkeit auf zusätzliche Kategorie", beschreibung));
                    }

                    /* Mehr Verkaufs erlöse */
                    if(spielerShop.getZusaetzlicherVerkaufserlös() == 15){
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Du hast die Maximale anzahl am");
                        beschreibung.add("§7erhöhten Verkaufs erlöse erreicht!");
                        beschreibung.add("");
                        beschreibung.add("§7Wahrscheinlichkeit: §e" + spielerShop.getZusaetzlicherVerkaufserlös() + "% §7/ §e15%");

                        contents.updateOrSet(24, Shopy.getInstance().createItemWithLore(Material.NETHERITE_CHESTPLATE, "§aGlückwunsch", beschreibung));
                    }else {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Kosten: §e1 §7Einhornkristall");
                        beschreibung.add("");
                        beschreibung.add("§7Erhöhe den Gewinn beim Verkauf");
                        beschreibung.add("§7von Item an kunden");
                        beschreibung.add("");
                        beschreibung.add("§7Wahrscheinlichkeit: §e" + spielerShop.getZusaetzlicherVerkaufserlös() + "% §7/ §e15%");

                        contents.updateOrSet(24, Shopy.getInstance().createItemWithLore(Material.STONE_SWORD, "§5Mehr Verkaufs erlöse", beschreibung));
                    }

                    /* Materialien Kosten reduzieren */
                    if(spielerShop.getReduzierteMaterialienKosten() == 15){
                        ArrayList<String>beschreibung = new ArrayList<>();
                        beschreibung.add("§7Du hast die Maximale anzahl an");
                        beschreibung.add("§7Materialien Kosten reduzieren erreicht!");
                        beschreibung.add("");
                        beschreibung.add("§7Wahrscheinlichkeit: §e" + spielerShop.getReduzierteMaterialienKosten() + "% §7/ §e15%");

                        contents.updateOrSet(25, Shopy.getInstance().createItemWithLore(Material.NETHERITE_INGOT, "§aGlückwunsch", beschreibung));
                    }else {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Kosten: §e1 §7Einhornkristall");
                        beschreibung.add("");
                        beschreibung.add("§7Reduziere die Kosten");
                        beschreibung.add("§7von Materialien");
                        beschreibung.add("");
                        beschreibung.add("§7Wahrscheinlichkeit: §e" + spielerShop.getReduzierteMaterialienKosten() + "% §7/ §e15%");

                        contents.updateOrSet(25, Shopy.getInstance().createItemWithLore(Material.COPPER_INGOT, "§5Materialien Kosten reduzieren", beschreibung));
                    }

                }

                @Override
                public void update(Player player, InventoryContents contents) {
                    init(player, contents);
                }
            }).build(Shopy.getInstance()).open(p);

        }else if(NpcManger.INSTANCE().getLara().getUniqueId() == npc.getUniqueId()){
            RyseInventory.builder().title("§bIngenieurin Lara").rows(4).provider(new InventoryProvider() {
                @Override
                public void init(Player player, InventoryContents contents) {
                    contents.updateOrSet(10, updateLoreMitPreis(ShopDefaultItemsManger.INSTANCE().getRessourcenMark(), 300));
                    contents.updateOrSet(11, updateLoreMitPreis(ShopDefaultItemsManger.INSTANCE().getWerkbank(), 300));
                    contents.updateOrSet(12, updateLoreMitPreis(ShopDefaultItemsManger.INSTANCE().getRessourcenLager(), 750));
                    contents.updateOrSet(13, updateLoreMitPreis(ShopDefaultItemsManger.INSTANCE().getItemLager(), 750));
                }
                @Override
                public void update(Player player, InventoryContents contents) {
                    init(player, contents);
                }
            }).build(Shopy.getInstance()).open(p);
        }else if(NpcManger.INSTANCE().getPaul().getUniqueId() == npc.getUniqueId()){
            RyseInventory.builder().title("§2Handwerksmeister Paul").rows(3).provider(new InventoryProvider() {
                @Override
                public void init(Player player, InventoryContents contents) {
                    Shop spielerShop =  Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

                    contents.updateOrSet(10, Shopy.getInstance().createItemWithLore(Material.FLOWER_BANNER_PATTERN, "§9Aufgabe 1", spielerShop.getShopHandwerksAufgabe().get(0).getBschreibung()));

                    ArrayList<String> beschreibungAufgabe2 = new ArrayList<>();
                    beschreibungAufgabe2.add("");
                    beschreibungAufgabe2.add("§7Erledige Handwerksaufgaben");
                    beschreibungAufgabe2.add("§e" + spielerShop.getErledigteHandwerksAufgaben() + " §7/§e 100");

                    contents.updateOrSet(13, Shopy.getInstance().createItemWithLore(Material.GRAY_DYE, "§eAufgabe noch nicht freigeschaltet", beschreibungAufgabe2));

                    ArrayList<String> beschreibungAufgabe3 = new ArrayList<>();
                    beschreibungAufgabe3.add("");
                    beschreibungAufgabe3.add("§7Erledige Handwerksaufgaben");
                    beschreibungAufgabe3.add("§e" + spielerShop.getErledigteHandwerksAufgaben() + " §7/§e 250");

                    contents.updateOrSet(15, Shopy.getInstance().createItemWithLore(Material.GRAY_DYE, "§eAufgabe noch nicht freigeschaltet", beschreibungAufgabe3));
                }
                @Override
                public void update(Player player, InventoryContents contents) {
                    init(player, contents);
                }
            }).build(Shopy.getInstance()).open(p);
        }
    }

    private ItemStack updateLoreMitPreis(ItemStack item, int preis){
        List<String> lore = item.getLore();
        lore.add(0, "§7Kosten: §e"+preis+" §7€");
        lore.add(1, "");
        item.setLore(lore);

        return item;
    }
}
