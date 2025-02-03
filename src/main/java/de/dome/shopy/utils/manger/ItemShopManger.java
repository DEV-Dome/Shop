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
                contents.updateOrSet(20, Shopy.getInstance().createItem(Material.COBBLESTONE_WALL,"§9Steinitems"));
                contents.updateOrSet(21, Shopy.getInstance().createItem(Material.OAK_SIGN,"§9Schilder"));
                contents.updateOrSet(22, Shopy.getInstance().createItem(Material.WHITE_CONCRETE,"§9Ton"));

                contents.updateOrSet(27, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(p);
    }

    public void openHolzBloeckeAnsicht(Player p){
        RyseInventory.builder().title("§9Item-Shop: Holzblöcke").rows(6).provider(new InventoryProvider() {
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

    public void openSteinBloeckeAnsicht(Player p){
        RyseInventory.builder().title("§9Item-Shop: Steinblöcke").rows(6).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                contents.updateOrSet(10, updateLoreMitPreis(Shopy.getInstance().createItem(Material.COBBLESTONE,"§9Cobblestone"), 10));
                contents.updateOrSet(11, updateLoreMitPreis(Shopy.getInstance().createItem(Material.STONE,"§9Stein"), 10));
                contents.updateOrSet(12, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SMOOTH_STONE,"§9Glatterstein"), 10));
                contents.updateOrSet(13, updateLoreMitPreis(Shopy.getInstance().createItem(Material.STONE_BRICKS,"§9Steinziegel"), 10));
                contents.updateOrSet(14, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHISELED_STONE_BRICKS,"§9Steinziegel"), 10));
                contents.updateOrSet(15, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DEEPSLATE_BRICKS,"§9Dunkler Steinziegel"), 10));
                contents.updateOrSet(16, updateLoreMitPreis(Shopy.getInstance().createItem(Material.PURPUR_BLOCK,"§9Lila Block"), 10));

                contents.updateOrSet(19, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DIORITE,"§9Diorite"), 10));
                contents.updateOrSet(20, updateLoreMitPreis(Shopy.getInstance().createItem(Material.POLISHED_DIORITE,"§9Diorite"), 10));
                contents.updateOrSet(21, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ANDESITE,"§9Andesite"), 10));
                contents.updateOrSet(22, updateLoreMitPreis(Shopy.getInstance().createItem(Material.POLISHED_ANDESITE,"§9Andesite"), 10));
                contents.updateOrSet(23, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GRANITE,"§9Granite"), 10));
                contents.updateOrSet(24, updateLoreMitPreis(Shopy.getInstance().createItem(Material.POLISHED_GRANITE,"§9Granite"), 10));
                contents.updateOrSet(25, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MUD_BRICKS,"§9Schlammziegel"), 12));

                contents.updateOrSet(28, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SANDSTONE,"§9Sandstein"), 12));
                contents.updateOrSet(29, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SMOOTH_SANDSTONE,"§9Sandstein"), 14));
                contents.updateOrSet(30, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHISELED_SANDSTONE,"§9Sandstein"), 14));
                contents.updateOrSet(31, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CUT_SANDSTONE,"§9Sandstein"), 14));
                contents.updateOrSet(32, updateLoreMitPreis(Shopy.getInstance().createItem(Material.RED_SANDSTONE,"§9Roter Sandstein"), 13));
                contents.updateOrSet(33, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SMOOTH_RED_SANDSTONE,"§9Roter Sandstein"), 15));
                contents.updateOrSet(34, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHISELED_RED_SANDSTONE,"§9Roter Sandstein"), 15));

                contents.updateOrSet(37, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BRICKS,"§9Steinziegel"), 12));
                contents.updateOrSet(38, updateLoreMitPreis(Shopy.getInstance().createItem(Material.PRISMARINE,"§9Prismarine"), 20));
                contents.updateOrSet(39, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DARK_PRISMARINE,"§9Prismarine"), 18));
                contents.updateOrSet(40, updateLoreMitPreis(Shopy.getInstance().createItem(Material.COPPER_BLOCK,"§9Kupferblock"), 20));
                contents.updateOrSet(41, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CUT_COPPER,"§9Kupferblock"), 20));
                contents.updateOrSet(42, updateLoreMitPreis(Shopy.getInstance().createItem(Material.QUARTZ_BLOCK,"§9Quarz Block"), 18));
                contents.updateOrSet(43, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BAMBOO_BLOCK,"§9Bambusblock"), 20));

                contents.updateOrSet(45, Shopy.getInstance().createItem(Material.BARRIER,"§7Schlissen"));
                contents.updateOrSet(46, Shopy.getInstance().createItem(Material.ARROW,"§7Zurück"));

            }
        }).build(Shopy.getInstance()).open(p);
    }
    public void openGlasBloeckeAnsicht(Player p){
        RyseInventory.builder().title("§9Item-Shop: Glasblöcke").rows(6).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                contents.updateOrSet(10, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GLASS,"§9Glasblock"), 13));
                contents.updateOrSet(11, updateLoreMitPreis(Shopy.getInstance().createItem(Material.TINTED_GLASS,"§9Glasblock"), 15));
                contents.updateOrSet(12, updateLoreMitPreis(Shopy.getInstance().createItem(Material.WHITE_STAINED_GLASS,"§9Glasblock"), 15));
                contents.updateOrSet(13, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GRAY_STAINED_GLASS,"§9Glasblock"), 15));
                contents.updateOrSet(14, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLACK_STAINED_GLASS,"§9Glasblock"), 15));
                contents.updateOrSet(15, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BROWN_STAINED_GLASS,"§9Glasblock"), 15));
                contents.updateOrSet(16, updateLoreMitPreis(Shopy.getInstance().createItem(Material.RED_STAINED_GLASS,"§9Glasblock"), 15));

                contents.updateOrSet(19, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ORANGE_STAINED_GLASS,"§9Glasblock"), 15));
                contents.updateOrSet(20, updateLoreMitPreis(Shopy.getInstance().createItem(Material.YELLOW_STAINED_GLASS,"§9Glasblock"), 15));
                contents.updateOrSet(21, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIME_STAINED_GLASS,"§9Glasblock"), 15));
                contents.updateOrSet(22, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GREEN_STAINED_GLASS,"§9Glasblock"), 15));
                contents.updateOrSet(23, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CYAN_STAINED_GLASS,"§9Glasblock"), 15));
                contents.updateOrSet(24, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIGHT_BLUE_STAINED_GLASS,"§9Glasblock"), 15));
                contents.updateOrSet(25, updateLoreMitPreis(Shopy.getInstance().createItem(Material.PURPLE_STAINED_GLASS,"§9Glasblock"), 15));

                contents.updateOrSet(28, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GLASS_PANE,"§9Glasscheibe"), 7));
                contents.updateOrSet(29, updateLoreMitPreis(Shopy.getInstance().createItem(Material.WHITE_STAINED_GLASS_PANE,"§9Glasscheibe"), 9));
                contents.updateOrSet(30, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE,"§9Glasscheibe"), 9));
                contents.updateOrSet(31, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GRAY_STAINED_GLASS_PANE,"§9Glasscheibe"), 9));
                contents.updateOrSet(32, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLACK_STAINED_GLASS_PANE,"§9Glasscheibe"), 9));
                contents.updateOrSet(33, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BROWN_STAINED_GLASS_PANE,"§9Glasscheibe"), 9));
                contents.updateOrSet(34, updateLoreMitPreis(Shopy.getInstance().createItem(Material.RED_STAINED_GLASS_PANE,"§9Glasscheibe"), 9));

                contents.updateOrSet(37, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ORANGE_STAINED_GLASS_PANE,"§9Glasscheibe"), 9));
                contents.updateOrSet(38, updateLoreMitPreis(Shopy.getInstance().createItem(Material.YELLOW_STAINED_GLASS_PANE,"§9Glasscheibe"), 9));
                contents.updateOrSet(39, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIME_STAINED_GLASS_PANE,"§9Glasscheibe"), 9));
                contents.updateOrSet(40, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GREEN_STAINED_GLASS_PANE,"§9Glasscheibe"), 9));
                contents.updateOrSet(41, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE,"§9Glasscheibe"), 9));
                contents.updateOrSet(42, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLUE_STAINED_GLASS_PANE,"§9Glasscheibe"), 9));
                contents.updateOrSet(43, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MAGENTA_STAINED_GLASS_PANE,"§9Glasscheibe"), 9));

                contents.updateOrSet(45, Shopy.getInstance().createItem(Material.BARRIER,"§7Schlissen"));
                contents.updateOrSet(46, Shopy.getInstance().createItem(Material.ARROW,"§7Zurück"));

            }
        }).build(Shopy.getInstance()).open(p);
    }
    public void openWolleBloeckeAnsicht(Player p){
        RyseInventory.builder().title("§9Item-Shop: Wollblöcke").rows(6).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                contents.updateOrSet(10, updateLoreMitPreis(Shopy.getInstance().createItem(Material.WHITE_WOOL,"§9Wollblock"), 7));
                contents.updateOrSet(11, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIGHT_GRAY_WOOL,"§9Wollblock"), 7));
                contents.updateOrSet(12, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GRAY_WOOL,"§9Wollblock"), 7));
                contents.updateOrSet(13, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLACK_WOOL,"§9Wollblock"), 7));
                contents.updateOrSet(14, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BROWN_WOOL,"§9Wollblock"), 7));
                contents.updateOrSet(15, updateLoreMitPreis(Shopy.getInstance().createItem(Material.RED_WOOL,"§9Wollblock"), 7));
                contents.updateOrSet(16, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ORANGE_WOOL,"§9Wollblock"), 7));

                contents.updateOrSet(19, updateLoreMitPreis(Shopy.getInstance().createItem(Material.YELLOW_WOOL,"§9Wollblock"), 7));
                contents.updateOrSet(20, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIME_WOOL,"§9Wollblock"), 7));
                contents.updateOrSet(21, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GREEN_WOOL,"§9Wollblock"), 7));
                contents.updateOrSet(22, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CYAN_WOOL,"§9Wollblock"), 7));
                contents.updateOrSet(23, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIGHT_BLUE_WOOL,"§9Wollblock"), 7));
                contents.updateOrSet(24, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLUE_WOOL,"§9Wollblock"), 7));
                contents.updateOrSet(25, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MAGENTA_WOOL,"§9Wollblock"), 7));

                contents.updateOrSet(28, updateLoreMitPreis(Shopy.getInstance().createItem(Material.WHITE_CARPET,"§9Teppich"), 4));
                contents.updateOrSet(29, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIGHT_GRAY_CARPET,"§9Teppich"), 4));
                contents.updateOrSet(30, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GRAY_CARPET,"§9Teppich"), 4));
                contents.updateOrSet(31, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLACK_CARPET,"§9Teppich"), 4));
                contents.updateOrSet(32, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BROWN_CARPET,"§9Teppich"), 4));
                contents.updateOrSet(33, updateLoreMitPreis(Shopy.getInstance().createItem(Material.RED_CARPET,"§9Teppich"), 4));
                contents.updateOrSet(34, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ORANGE_CARPET,"§9Teppich"), 4));

                contents.updateOrSet(37, updateLoreMitPreis(Shopy.getInstance().createItem(Material.YELLOW_CARPET,"§9Teppich"), 4));
                contents.updateOrSet(38, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIME_CARPET,"§9Teppich"), 4));
                contents.updateOrSet(39, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GREEN_CARPET,"§9Teppich"), 4));
                contents.updateOrSet(40, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CYAN_CARPET,"§9Teppich"), 4));
                contents.updateOrSet(41, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIGHT_BLUE_CARPET,"§9Teppich"), 4));
                contents.updateOrSet(42, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLUE_CARPET,"§9Teppich"), 4));
                contents.updateOrSet(43, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MAGENTA_CARPET,"§9Teppich"), 4));

                contents.updateOrSet(45, Shopy.getInstance().createItem(Material.BARRIER,"§7Schlissen"));
                contents.updateOrSet(46, Shopy.getInstance().createItem(Material.ARROW,"§7Zurück"));

            }
        }).build(Shopy.getInstance()).open(p);
    }
    public void openNaturBloeckeAnsicht(Player p){
        RyseInventory.builder().title("§9Item-Shop: Naturblöcke").rows(6).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                contents.updateOrSet(10, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DIRT,"§9Erde"), 1));
                contents.updateOrSet(11, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GRASS_BLOCK,"§9Erde"), 2));
                contents.updateOrSet(12, updateLoreMitPreis(Shopy.getInstance().createItem(Material.COARSE_DIRT,"§9Erde"), 2));
                contents.updateOrSet(13, updateLoreMitPreis(Shopy.getInstance().createItem(Material.PODZOL,"§9Erde"), 2));
                contents.updateOrSet(14, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MYCELIUM,"§9Mycelium"), 4));
                contents.updateOrSet(15, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DIRT_PATH,"§9Erde"), 2));
                contents.updateOrSet(16, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ROOTED_DIRT,"§9Erde"), 2));

                contents.updateOrSet(19, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SAND,"§9Sand"), 2));
                contents.updateOrSet(20, updateLoreMitPreis(Shopy.getInstance().createItem(Material.RED_SAND,"§9Sand"), 2));
                contents.updateOrSet(21, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GRAVEL,"§9Kies"), 2));
                contents.updateOrSet(22, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MUD,"§9Matsch"), 2));
                contents.updateOrSet(23, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SNOW_BLOCK,"§9Schnee"), 5));
                contents.updateOrSet(24, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MOSS_BLOCK,"§9Moss"), 5));
                contents.updateOrSet(25, updateLoreMitPreis(Shopy.getInstance().createItem(Material.OBSIDIAN,"§9Obsidian"), 10));

                contents.updateOrSet(28, updateLoreMitPreis(Shopy.getInstance().createItem(Material.COAL_ORE,"§9Erz"), 6));
                contents.updateOrSet(29, updateLoreMitPreis(Shopy.getInstance().createItem(Material.IRON_ORE,"§9Erz"), 6));
                contents.updateOrSet(30, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GOLD_ORE,"§9Erz"), 6));
                contents.updateOrSet(31, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DIAMOND_ORE,"§9Erz"), 6));
                contents.updateOrSet(32, updateLoreMitPreis(Shopy.getInstance().createItem(Material.EMERALD_ORE,"§9Erz"), 6));
                contents.updateOrSet(33, updateLoreMitPreis(Shopy.getInstance().createItem(Material.REDSTONE_ORE,"§9Erz"), 6));
                contents.updateOrSet(34, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LAPIS_ORE,"§9Erz"), 6));

                contents.updateOrSet(37, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DEEPSLATE_COAL_ORE,"§9Erz"), 8));
                contents.updateOrSet(38, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DEEPSLATE_IRON_ORE,"§9Erz"), 8));
                contents.updateOrSet(39, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DEEPSLATE_GOLD_ORE,"§9Erz"), 8));
                contents.updateOrSet(40, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DEEPSLATE_DIAMOND_ORE,"§9Erz"), 8));
                contents.updateOrSet(41, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DEEPSLATE_EMERALD_ORE,"§9Erz"), 8));
                contents.updateOrSet(42, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DEEPSLATE_REDSTONE_ORE,"§9Erz"), 8));
                contents.updateOrSet(43, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DEEPSLATE_LAPIS_ORE,"§9Erz"), 8));

                contents.updateOrSet(45, Shopy.getInstance().createItem(Material.BARRIER,"§7Schlissen"));
                contents.updateOrSet(46, Shopy.getInstance().createItem(Material.ARROW,"§7Zurück"));

            }
        }).build(Shopy.getInstance()).open(p);
    }
    public void openLichtBloeckeAnsicht(Player p){
        RyseInventory.builder().title("§9Item-Shop: Lichtblöcke").rows(6).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                contents.updateOrSet(10, updateLoreMitPreis(Shopy.getInstance().createItem(Material.TORCH,"§9Fackel"), 3));
                contents.updateOrSet(11, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SOUL_TORCH,"§9Fackel"), 3));
                contents.updateOrSet(12, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LANTERN,"§9Laterne"), 5));
                contents.updateOrSet(13, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SOUL_LANTERN,"§9Laterne"), 5));
                contents.updateOrSet(14, updateLoreMitPreis(Shopy.getInstance().createItem(Material.END_ROD,"§9Laterne"), 6));
                contents.updateOrSet(15, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SEA_LANTERN,"§9Laterne"), 5));
                contents.updateOrSet(16, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GLOWSTONE,"§9Glowstone"), 8));

                contents.updateOrSet(19, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CAMPFIRE,"§9Lagerfeuer"), 25));
                contents.updateOrSet(20, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SOUL_CAMPFIRE,"§§9Lagerfeuer"), 25));
                contents.updateOrSet(21, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SHROOMLIGHT,"§9Leuchtpliz"), 10));
                contents.updateOrSet(22, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MAGMA_BLOCK,"§9Lavablock"), 10));


                contents.updateOrSet(46, Shopy.getInstance().createItem(Material.BARRIER,"§7Schlissen"));
                contents.updateOrSet(47, Shopy.getInstance().createItem(Material.ARROW,"§7Zurück"));

            }
        }).build(Shopy.getInstance()).open(p);
    }
    public void openNetherBloeckeAnsicht(Player p){
        RyseInventory.builder().title("§9Item-Shop: Netherblöcke").rows(6).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                contents.updateOrSet(10, updateLoreMitPreis(Shopy.getInstance().createItem(Material.NETHERRACK,"§9Höllenerde"), 6));
                contents.updateOrSet(11, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CRIMSON_NYLIUM,"§9Höllenerde"), 6));
                contents.updateOrSet(12, updateLoreMitPreis(Shopy.getInstance().createItem(Material.WARPED_NYLIUM,"§9Höllenerde"), 6));
                contents.updateOrSet(13, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SOUL_SAND,"§9Selensand"), 12));
                contents.updateOrSet(14, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SOUL_SOIL,"§9Selensand"), 12));
                contents.updateOrSet(15, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CRIMSON_STEM,"§9Holz"), 12));
                contents.updateOrSet(16, updateLoreMitPreis(Shopy.getInstance().createItem(Material.WARPED_STEM,"§9Holz"), 12));

                contents.updateOrSet(19, updateLoreMitPreis(Shopy.getInstance().createItem(Material.NETHER_BRICKS,"§9Höllenstein"), 12));
                contents.updateOrSet(20, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHISELED_NETHER_BRICKS,"§9Höllenstein"), 14));
                contents.updateOrSet(21, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CRACKED_NETHER_BRICKS,"§9Höllenstein"), 14));
                contents.updateOrSet(22, updateLoreMitPreis(Shopy.getInstance().createItem(Material.NETHER_BRICK_SLAB,"§9Höllenstein"), 8));
                contents.updateOrSet(23, updateLoreMitPreis(Shopy.getInstance().createItem(Material.NETHER_BRICK_STAIRS,"§9Höllenstein Treppe"), 15));
                contents.updateOrSet(24, updateLoreMitPreis(Shopy.getInstance().createItem(Material.NETHER_BRICK_FENCE,"§9Höllenstein Zaun"), 17));
                contents.updateOrSet(25, updateLoreMitPreis(Shopy.getInstance().createItem(Material.NETHER_BRICK_WALL,"§9Höllenstein Wand"), 17));

                contents.updateOrSet(28, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLACKSTONE,"§9Schwarzstein"), 12));
                contents.updateOrSet(29, updateLoreMitPreis(Shopy.getInstance().createItem(Material.POLISHED_BLACKSTONE,"§9Schwarzstein"), 14));
                contents.updateOrSet(30, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHISELED_POLISHED_BLACKSTONE,"§9Schwarzstein"), 14));
                contents.updateOrSet(31, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLACKSTONE_SLAB,"§9Schwarzstein"), 8));
                contents.updateOrSet(32, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLACKSTONE_STAIRS,"§9Schwarzstein Treppe"), 15));
                contents.updateOrSet(33, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLACKSTONE_WALL,"§9Schwarzstein Wand"), 17));
                contents.updateOrSet(34, updateLoreMitPreis(Shopy.getInstance().createItem(Material.POLISHED_BLACKSTONE_WALL,"§9Schwarzstein Wand"), 17));

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

    public void openSteinItemsAnsicht(Player p){
        RyseInventory.builder().title("§9Item-Shop: Steinitems").rows(6).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                contents.updateOrSet(10, updateLoreMitPreis(Shopy.getInstance().createItem(Material.COBBLESTONE_WALL,"§9Steinmauer"), 15));
                contents.updateOrSet(11, updateLoreMitPreis(Shopy.getInstance().createItem(Material.STONE_BRICK_WALL,"§9Steinmauer"), 15));
                contents.updateOrSet(12, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ANDESITE_WALL,"§9Steinmauer"), 15));
                contents.updateOrSet(13, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DIORITE_WALL,"§9Steinmauer"), 15));
                contents.updateOrSet(14, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GRANITE_WALL,"§9Steinmauer"), 15));
                contents.updateOrSet(15, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BRICK_WALL,"§9Steinmauer"), 15));
                contents.updateOrSet(16, updateLoreMitPreis(Shopy.getInstance().createItem(Material.PRISMARINE_WALL,"§9Prismarinemauer"), 16));

                contents.updateOrSet(19, updateLoreMitPreis(Shopy.getInstance().createItem(Material.COBBLESTONE_SLAB,"§9Steinstufe"), 8));
                contents.updateOrSet(20, updateLoreMitPreis(Shopy.getInstance().createItem(Material.STONE_BRICK_SLAB,"§9Steinstufe"), 8));
                contents.updateOrSet(21, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ANDESITE_SLAB,"§9Steinstufe"), 8));
                contents.updateOrSet(22, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DIORITE_SLAB,"§9Steinstufe"), 8));
                contents.updateOrSet(23, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GRANITE_SLAB,"§9Steinstufe"), 8));
                contents.updateOrSet(24, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BRICK_SLAB,"§9Steinstufe"), 8));
                contents.updateOrSet(25, updateLoreMitPreis(Shopy.getInstance().createItem(Material.PRISMARINE_SLAB,"§9Prismarinestufe"), 10));

                contents.updateOrSet(45, Shopy.getInstance().createItem(Material.BARRIER,"§7Schlissen"));
                contents.updateOrSet(46, Shopy.getInstance().createItem(Material.ARROW,"§7Zurück"));

            }
        }).build(Shopy.getInstance()).open(p);
    }

    public void openSchilderAnsicht(Player p){
        RyseInventory.builder().title("§9Item-Shop: Schilder").rows(6).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                contents.updateOrSet(10, updateLoreMitPreis(Shopy.getInstance().createItem(Material.OAK_SIGN,"§9Schild"), 13));
                contents.updateOrSet(11, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BIRCH_SIGN,"§9Schild"), 13));
                contents.updateOrSet(12, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SPRUCE_SIGN,"§9Schild"), 13));
                contents.updateOrSet(13, updateLoreMitPreis(Shopy.getInstance().createItem(Material.JUNGLE_SIGN,"§9Schild"), 13));
                contents.updateOrSet(14, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ACACIA_SIGN,"§9Schild"), 13));
                contents.updateOrSet(15, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DARK_OAK_SIGN,"§9Schild"), 13));
                contents.updateOrSet(16, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MANGROVE_SIGN,"§9Schild"), 13));

                contents.updateOrSet(19, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHERRY_SIGN,"§9Schild"), 13));
                contents.updateOrSet(20, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BAMBOO_SIGN,"§9Schild"), 13));
                contents.updateOrSet(21, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CRIMSON_SIGN,"§9Schild"), 13));
                contents.updateOrSet(22, updateLoreMitPreis(Shopy.getInstance().createItem(Material.WARPED_SIGN,"§9Schild"), 13));

                contents.updateOrSet(28, updateLoreMitPreis(Shopy.getInstance().createItem(Material.OAK_HANGING_SIGN,"§9Schild"), 13));
                contents.updateOrSet(29, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BIRCH_HANGING_SIGN,"§9Schild"), 13));
                contents.updateOrSet(30, updateLoreMitPreis(Shopy.getInstance().createItem(Material.SPRUCE_HANGING_SIGN,"§9Schild"), 13));
                contents.updateOrSet(31, updateLoreMitPreis(Shopy.getInstance().createItem(Material.JUNGLE_HANGING_SIGN,"§9Schild"), 13));
                contents.updateOrSet(32, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ACACIA_HANGING_SIGN,"§9Schild"), 13));
                contents.updateOrSet(33, updateLoreMitPreis(Shopy.getInstance().createItem(Material.DARK_OAK_HANGING_SIGN,"§9Schild"), 13));
                contents.updateOrSet(34, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MANGROVE_HANGING_SIGN,"§9Schild"), 13));

                contents.updateOrSet(37, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CHERRY_HANGING_SIGN,"§9Schild"), 13));
                contents.updateOrSet(38, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BAMBOO_HANGING_SIGN,"§9Schild"), 13));
                contents.updateOrSet(39, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CRIMSON_HANGING_SIGN,"§9Schild"), 13));
                contents.updateOrSet(40, updateLoreMitPreis(Shopy.getInstance().createItem(Material.WARPED_HANGING_SIGN,"§9Schild"), 13));

                contents.updateOrSet(45, Shopy.getInstance().createItem(Material.BARRIER,"§7Schlissen"));
                contents.updateOrSet(46, Shopy.getInstance().createItem(Material.ARROW,"§7Zurück"));

            }
        }).build(Shopy.getInstance()).open(p);
    }


    public void openTonBloeckeAnsicht(Player p){
        RyseInventory.builder().title("§9Item-Shop: Tonblöcke").rows(6).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                contents.updateOrSet(10, updateLoreMitPreis(Shopy.getInstance().createItem(Material.WHITE_CONCRETE,"§9Ton"), 10));
                contents.updateOrSet(11, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIGHT_GRAY_CONCRETE,"§9Ton"), 10));
                contents.updateOrSet(12, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GRAY_CONCRETE,"§9Ton"), 10));
                contents.updateOrSet(13, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLACK_CONCRETE,"§9Ton"), 10));
                contents.updateOrSet(14, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BROWN_CONCRETE,"§9Ton"), 10));
                contents.updateOrSet(15, updateLoreMitPreis(Shopy.getInstance().createItem(Material.RED_CONCRETE,"§9Ton"), 10));
                contents.updateOrSet(16, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ORANGE_CONCRETE,"§9Ton"), 10));

                contents.updateOrSet(19, updateLoreMitPreis(Shopy.getInstance().createItem(Material.YELLOW_CONCRETE,"§9Ton"), 10));
                contents.updateOrSet(20, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIME_CONCRETE,"§9Ton"), 10));
                contents.updateOrSet(21, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CYAN_CONCRETE,"§9Ton"), 10));
                contents.updateOrSet(22, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIGHT_BLUE_CONCRETE,"§9Ton"), 10));
                contents.updateOrSet(23, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLUE_CONCRETE,"§9Ton"), 10));
                contents.updateOrSet(24, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MAGENTA_CONCRETE,"§9Ton"), 10));
                contents.updateOrSet(25, updateLoreMitPreis(Shopy.getInstance().createItem(Material.PINK_CONCRETE,"§9Ton"), 10));

                contents.updateOrSet(28, updateLoreMitPreis(Shopy.getInstance().createItem(Material.WHITE_CONCRETE_POWDER,"§9Ton"), 12));
                contents.updateOrSet(29, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIGHT_GRAY_CONCRETE_POWDER,"§9Ton"), 12));
                contents.updateOrSet(30, updateLoreMitPreis(Shopy.getInstance().createItem(Material.GRAY_CONCRETE_POWDER,"§9Ton"), 12));
                contents.updateOrSet(31, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLACK_CONCRETE_POWDER,"§9Ton"), 12));
                contents.updateOrSet(32, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BROWN_CONCRETE_POWDER,"§9Ton"), 12));
                contents.updateOrSet(33, updateLoreMitPreis(Shopy.getInstance().createItem(Material.RED_CONCRETE_POWDER,"§9Ton"), 12));
                contents.updateOrSet(34, updateLoreMitPreis(Shopy.getInstance().createItem(Material.ORANGE_CONCRETE_POWDER,"§9Ton"), 12));

                contents.updateOrSet(37, updateLoreMitPreis(Shopy.getInstance().createItem(Material.YELLOW_CONCRETE_POWDER,"§9Ton"), 12));
                contents.updateOrSet(38, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIME_CONCRETE_POWDER,"§9Ton"), 12));
                contents.updateOrSet(39, updateLoreMitPreis(Shopy.getInstance().createItem(Material.CYAN_CONCRETE_POWDER,"§9Ton"), 12));
                contents.updateOrSet(40, updateLoreMitPreis(Shopy.getInstance().createItem(Material.LIGHT_BLUE_CONCRETE_POWDER,"§9Ton"), 12));
                contents.updateOrSet(41, updateLoreMitPreis(Shopy.getInstance().createItem(Material.BLUE_CONCRETE_POWDER,"§9Ton"), 12));
                contents.updateOrSet(42, updateLoreMitPreis(Shopy.getInstance().createItem(Material.MAGENTA_CONCRETE_POWDER,"§9Ton"), 12));
                contents.updateOrSet(43, updateLoreMitPreis(Shopy.getInstance().createItem(Material.PINK_CONCRETE_POWDER,"§9Ton"), 12));

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
