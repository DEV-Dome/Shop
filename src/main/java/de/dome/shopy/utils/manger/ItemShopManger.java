package de.dome.shopy.utils.manger;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.shop.Shop;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemShopManger {

    private static ItemShopManger INSTANCE;

    private ItemShopManger(){}

    public void openUebersicht(Player p){
        RyseInventory.builder().title(NpcManger.INSTANCE().getJahn().getFullName()).rows(4).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                contents.updateOrSet(10, Shopy.getInstance().createItem(Material.OAK_WOOD,"§9Holzblöcke"));
                contents.updateOrSet(11, Shopy.getInstance().createItem(Material.STONE,"§9Steinblöcke"));
                contents.updateOrSet(12, Shopy.getInstance().createItem(Material.GLASS,"§9Glasblöcke"));
                contents.updateOrSet(13, Shopy.getInstance().createItem(Material.WHITE_WOOL,"§9Wollblöcke"));
                contents.updateOrSet(14, Shopy.getInstance().createItem(Material.GRASS_BLOCK,"§9Naturblöcke"));
                contents.updateOrSet(15, Shopy.getInstance().createItem(Material.GLOWSTONE,"§9Lichtblöcke"));
                contents.updateOrSet(16, Shopy.getInstance().createItem(Material.NETHERRACK,"§9Netherblöcke"));

                contents.updateOrSet(19, Shopy.getInstance().createItem(Material.OAK_FENCE,"§9Holzitems"));

                contents.updateOrSet(27, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(p);
    }

    public void openHolzBloeckeAnsicht(Player p){
        RyseInventory.builder().title("§9Item-Shop: Holz").rows(6).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                contents.updateOrSet(10, updateLoreMitPreis(Shopy.getInstance().createItem(Material.OAK_WOOD,"§9Eichenholz"), 10));
                contents.updateOrSet(11, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BIRCH_WOOD,"§9Birkenholz"), 10));
                contents.updateOrSet(12, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SPRUCE_WOOD,"§9Fichtenholz"), 10));
                contents.updateOrSet(13, updateLoreMitPreis(Shopy.getInstance().createItem(Material.JUNGLE_WOOD,"§9Jungelholz"), 10));
                contents.updateOrSet(14, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ACACIA_WOOD,"§9Akazienholz"), 10));
                contents.updateOrSet(15, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHERRY_WOOD,"§9Krischblüttenholz"), 10));
                contents.updateOrSet(16, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MANGROVE_WOOD,"§9Mangoevenholz"), 10));

                contents.updateOrSet(19, updateLoreMitPreis(Shopy.getInstance().createItem(Material.OAK_PLANKS,"§9Birkenholzbretter"), 8));
                contents.updateOrSet(20, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BIRCH_PLANKS,"§9Fichtenholzbretter"), 8));
                contents.updateOrSet(21, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SPRUCE_PLANKS,"§9Jungelholzbretter"), 8));
                contents.updateOrSet(22, updateLoreMitPreis(Shopy.getInstance().createItem(Material.JUNGLE_PLANKS,"§9Akazienholzbretter"), 8));
                contents.updateOrSet(23, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ACACIA_PLANKS,"§9Krischblüttenholzbretter"), 8));
                contents.updateOrSet(24, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHERRY_PLANKS,"§9Mangoevenholzbretter"), 8));
                contents.updateOrSet(25, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MANGROVE_PLANKS,"§9Mangoevenholzbretter"), 8));

                contents.updateOrSet(28, updateLoreMitPreis(Shopy.getInstance().createItem(Material.OAK_SLAB,"§9Eichenholzstufe"), 4));
                contents.updateOrSet(29, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BIRCH_SLAB,"§9Birkenholzstufe"), 4));
                contents.updateOrSet(30, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SPRUCE_SLAB,"§9Fichtenholzstufe"), 4));
                contents.updateOrSet(31, updateLoreMitPreis(Shopy.getInstance().createItem(Material.JUNGLE_SLAB,"§9Jungelholzstufe"), 4));
                contents.updateOrSet(32, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ACACIA_SLAB,"§9Akazienholzstufe"), 4));
                contents.updateOrSet(33, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHERRY_SLAB,"§9Krischblüttenholzstufe"), 4));
                contents.updateOrSet(34, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MANGROVE_SLAB,"§9Mangoevenholzstufe"), 4));

                contents.updateOrSet(37, updateLoreMitPreis(Shopy.getInstance().createItem(Material.OAK_STAIRS,"§9Eichenholztreppe"), 6));
                contents.updateOrSet(38, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BIRCH_STAIRS,"§9Birkenholztreppe"), 6));
                contents.updateOrSet(39, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SPRUCE_STAIRS,"§9Fichtenholztreppe"), 6));
                contents.updateOrSet(40, updateLoreMitPreis(Shopy.getInstance().createItem(Material.JUNGLE_STAIRS,"§9Jungelholztreppe"), 6));
                contents.updateOrSet(41, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ACACIA_STAIRS,"§9Akazienholztreppe"), 6));
                contents.updateOrSet(42, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHERRY_STAIRS,"§9Krischblüttenholztreppe"), 6));
                contents.updateOrSet(43, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MANGROVE_STAIRS,"§9Mangoevenholztreppe"), 6));

                contents.updateOrSet(45, Shopy.getInstance().createItem(Material.BARRIER,"§7Schlissen"));
                contents.updateOrSet(46, Shopy.getInstance().createItem(Material.ARROW,"§7Zurück"));

            }
        }).build(Shopy.getInstance()).open(p);
    }

    public void openHolzItemsAnsicht(Player p){
        RyseInventory.builder().title("§9Item-Shop: Holzitems").rows(6).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                contents.updateOrSet(10, updateLoreMitPreis(Shopy.getInstance().createItem(Material.OAK_FENCE,"§9Eichenholz Zaun"), 12));
                contents.updateOrSet(11, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BIRCH_FENCE,"§9Birkenholz Zaun"), 12));
                contents.updateOrSet(12, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SPRUCE_FENCE,"§9Fichtenholz Zaun"), 12));
                contents.updateOrSet(13, updateLoreMitPreis(Shopy.getInstance().createItem(Material.JUNGLE_FENCE,"§9Jungelholz Zaun"), 12));
                contents.updateOrSet(14, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ACACIA_FENCE,"§9Akazienholz Zaun"), 12));
                contents.updateOrSet(15, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHERRY_FENCE,"§9Krischblüttenholz Zaun"), 12));
                contents.updateOrSet(16, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MANGROVE_FENCE,"§9Mangoevenholz Zaun"), 12));

                contents.updateOrSet(19, updateLoreMitPreis(Shopy.getInstance().createItem(Material.OAK_FENCE_GATE,"§9Eichenholz Zauntor"), 16));
                contents.updateOrSet(20, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BIRCH_FENCE_GATE,"§9Birkenholz Zauntor"), 16));
                contents.updateOrSet(21, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SPRUCE_FENCE_GATE,"§9Fichtenholz Zauntor"), 16));
                contents.updateOrSet(22, updateLoreMitPreis(Shopy.getInstance().createItem(Material.JUNGLE_FENCE_GATE,"§9Jungelholz Zauntor"), 16));
                contents.updateOrSet(23, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ACACIA_FENCE_GATE,"§9Akazienholz Zauntor"), 16));
                contents.updateOrSet(24, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHERRY_FENCE_GATE,"§9Krischblüttenholz Zauntor"), 16));
                contents.updateOrSet(25, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MANGROVE_FENCE_GATE,"§9Mangoevenholz Zauntor"), 16));

                contents.updateOrSet(28, updateLoreMitPreis(Shopy.getInstance().createItem(Material.OAK_DOOR,"§9Eichenholztür"), 12));
                contents.updateOrSet(29, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BIRCH_DOOR,"§9Birkenholztür"), 12));
                contents.updateOrSet(30, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SPRUCE_DOOR,"§9Fichtenholztür"), 12));
                contents.updateOrSet(31, updateLoreMitPreis(Shopy.getInstance().createItem(Material.JUNGLE_DOOR,"§9Jungelholztür"), 12));
                contents.updateOrSet(32, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ACACIA_DOOR,"§9Akazienholztür"), 12));
                contents.updateOrSet(33, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHERRY_DOOR,"§9Krischblüttenholztür"), 12));
                contents.updateOrSet(34, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MANGROVE_DOOR,"§9Mangoevenholztür"), 12));

                contents.updateOrSet(37, updateLoreMitPreis(Shopy.getInstance().createItem(Material.OAK_TRAPDOOR,"§9Eichenholzfalltür"), 12));
                contents.updateOrSet(38, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BIRCH_TRAPDOOR,"§9Birkenholzfalltür"), 12));
                contents.updateOrSet(39, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SPRUCE_TRAPDOOR,"§9Fichtenholzfalltür"), 12));
                contents.updateOrSet(40, updateLoreMitPreis(Shopy.getInstance().createItem(Material.JUNGLE_TRAPDOOR,"§9Jungelholzfalltür"), 12));
                contents.updateOrSet(41, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ACACIA_TRAPDOOR,"§9Akazienholzfalltür"), 12));
                contents.updateOrSet(42, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHERRY_TRAPDOOR,"§9Krischblüttenholzfalltür"), 12));
                contents.updateOrSet(43, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MANGROVE_TRAPDOOR,"§9Mangoevenholzfalltür"), 12));

                contents.updateOrSet(45, Shopy.getInstance().createItem(Material.BARRIER,"§7Schlissen"));
                contents.updateOrSet(46, Shopy.getInstance().createItem(Material.ARROW,"§7Zurück"));

            }
        }).build(Shopy.getInstance()).open(p);
    }

    private ItemStack updateLoreMitPreis(ItemStack item, int preis){
        List<String> lore = new ArrayList<>();
        lore.add(0, "§7Kosten: §e"+preis+" §7€");
        lore.add(1, "");
        item.setLore(lore);

        return item;
    }

    public static ItemShopManger INSTANCE() {
        if(INSTANCE == null) INSTANCE = new ItemShopManger();

        return INSTANCE;
    }
}
