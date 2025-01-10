package de.dome.shopy;

import de.dome.shopy.commands.*;
import de.dome.shopy.commands.admin.*;
import de.dome.shopy.commands.dungeon.addDungeonCMD;
import de.dome.shopy.commands.dungeon.DungeonCMD;
import de.dome.shopy.commands.dungeon.setDungeonCMD;
import de.dome.shopy.commands.welt.*;
import de.dome.shopy.listener.dungeon.*;
import de.dome.shopy.listener.dungeon.PlayerRespawnListener;
import de.dome.shopy.listener.lobby.EntityDamageListener;
import de.dome.shopy.listener.lobby.NPCInteractListener;
import de.dome.shopy.listener.lobby.PlayerDeathListener;
import de.dome.shopy.listener.lobby.*;
import de.dome.shopy.listener.lobby.BlockBreakListener;
import de.dome.shopy.listener.lobby.clicklistener.*;
import de.dome.shopy.listener.shop.*;
import de.dome.shopy.listener.shop.clicklistener.*;
import de.dome.shopy.utils.*;
import de.dome.shopy.utils.manger.DropManger;
import de.dome.shopy.utils.manger.NpcManger;
import de.dome.shopy.utils.manger.ScoreboardManger;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import de.dome.shopy.utils.shop.ShopItemVorlage;
import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class Shopy extends JavaPlugin {

    private String prefix = "§9Shopy | §7";
    private String noperm = prefix + "§cDafür hast du keine Berechtigung.";
    private String konatktSupport = prefix + "§cHier ist etwas schiefgelaufen. Versuche es bitte später erneut, oder kontaktieren den Support.";
    private static Shopy instance;
    private Location serverSpawn;
    MySQL mySQLConntion;
    HashMap<UUID, Shop> spielerShops;
    ArrayList<Player> playersNotTeleport;
    InventoryManager inventoryManager;
    public static HashMap<UUID, ArrayList<World>> geladeneTempWelten;
    private HashMap<UUID, Dungeon> spielerDungeon;
    private ScoreboardManger scoreboardManger;
    private DropManger dropManger;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        playersNotTeleport = new ArrayList<>();
        spielerShops = new HashMap<>();
        geladeneTempWelten = new HashMap<>();
        spielerDungeon = new HashMap<>();
        inventoryManager = new InventoryManager(getInstance());
        inventoryManager.invoke();

        scoreboardManger = new ScoreboardManger();
        dropManger = new DropManger();

        registerListener();
        registerCommands();

        //lobby welt einstellungen
        Bukkit.getWorld("world").setDifficulty(Difficulty.PEACEFUL);
        Bukkit.getWorld("world").setClearWeatherDuration(0);
        Bukkit.getWorld("world").setTime(0);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

        /* Lade serverspawn */
        File configFile = new File(getInstance().getDataFolder(), "spawn.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        if(config.isLocation("server.spawn")){
            serverSpawn = config.getLocation("server.spawn");
        }else serverSpawn = null;

        /* Das nicht mit laden lassen, um leistung zu sparen */
        if(Bukkit.getWorld("world_the_end") != null)  Bukkit.unloadWorld(Bukkit.getWorld("world_the_end"), false);

        /* Verbindung zu datenbank*/
        mySQLConntion = new MySQL();
        MySQLDefault.getInstance().sqlStartUp();

        NpcManger.INSTANCE().spawnNPC();
        Bukkit.getConsoleSender().sendMessage(prefix + "§aPlugin gestartet");
    }

    @Override
    public void onDisable() {
        super.onDisable();

        /* Alle spieler beim Reload kicken */
        for(Player all : Bukkit.getOnlinePlayers()){
            TextComponent component = Component.text("§7Der Server wird neu geladen, damit das ohne Umstände passieren kann wurdest du gekickt. Du kannst gleich weiter spielen.");

            if(Shopy.getInstance().getSpielerDungeon().containsKey(all.getUniqueId())){
                all.getInventory().clear();
                for(ItemStack item : Shopy.getInstance().getSpielerDungeon().get(all.getUniqueId()).getSpielerInventrar()){
                    if(item == null) continue;
                    all.getInventory().addItem(item);
                }
                for (PotionEffect effect : all.getActivePotionEffects()) {
                    all.removePotionEffect(effect.getType());
                }

                all.setHealth(all.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                all.setFireTicks(0);
            }

            all.kick(component);
        }

        /* Lösche alle NPC die es schon gibt */
        NpcManger.INSTANCE().loescheAlleNpc();

        Bukkit.getConsoleSender().sendMessage(prefix + "§cPlugin getsoppt");
    }

    private void registerListener(){
        /* Lobby welt / Allgemeine Events */
        new BlockBreakListener();
        new EntityDamageListener();
        new PlayerJoinListener();
        new PlayerQuitListener();
        new WorldListener();
        new NPCInteractListener();
        new PlayerDeathListener();
        new de.dome.shopy.listener.lobby.PlayerRespawnListener();


        new InventoryClickListenerMona();
        new InventoryClickListenerShop();
        new InventoryClickListenerLara();
        new InventoryClickListenerDungeonHaendler();
        new InventoryClickListenerPaul();
        new InventoryClickListenerSiegfried();

        /* Shop Welt */
        new de.dome.shopy.listener.shop.BlockBreakListener();
        new PlayerInteractListener();
        new BlockPlaceListener();

        new InventoryClickListenerWerkbank();
        new InventoryClickListenerRessourenMarkplatz();
        new InventoryClickListenerItemLager();
        new InventoryClickListenerKundenKauf();
        new InventoryClickListenerArmorStand();
        new EntityDamageByEntityListener();
        new PlayerInteractAtEntityListener();
        new EntityPlaceListener();

        new de.dome.shopy.listener.shop.NPCInteractListener();

        /* Dungeon */
        new EntityDeathListener();
        new EntityCombustListener();
        new PlayerRespawnListener();
        new InventoryCloseListener();
        new PlayerDropItemListener();
        new de.dome.shopy.listener.dungeon.EntityDamageListener();
        new de.dome.shopy.listener.dungeon.NPCInteractListener();
    }

    private void registerCommands(){
        getCommand("setspawn").setExecutor(new SetSpawnCMD());
        getCommand("loaddefaultvalue").setExecutor(new LoadDefaultValue());
        getCommand("setnpcspawn").setExecutor(new SetNpcSpawn());
        getCommand("shop").setExecutor(new ShopCMD());
        getCommand("spawn").setExecutor(new SpawnCMD());
        getCommand("setshopzone").setExecutor(new SetShopZoneCMD());
        getCommand("ressouren").setExecutor(new RessourenCMD());
        getCommand("money").setExecutor(new moneyCMD());
        getCommand("setRessource").setExecutor(new setRessourceCMD());
        getCommand("itemlager").setExecutor(new ItemLagerCMD());
        getCommand("zeigewelten").setExecutor(new ZeigeWeltenCMD());
        getCommand("ladewelt").setExecutor(new LadeWeltCMD());
        getCommand("welttp").setExecutor(new WeltTpCD());
        getCommand("adddungeon").setExecutor(new addDungeonCMD());
        getCommand("setdungeon").setExecutor(new setDungeonCMD());
        getCommand("dungeon").setExecutor(new DungeonCMD());
        getCommand("setshopposition").setExecutor(new SetShopPosition());
        getCommand("npcreload").setExecutor(new NpcReloadCMD());
    }


    public ItemStack createItem(Material m, String Name) {
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Name);

        item.setItemMeta(meta);

        return item;
    }
    public ItemStack createItemWithLore(Material m, String name, ArrayList<String> lore) { return createItemWithLore(m, name, lore, false, false); }
    public ItemStack createItemWithLore(Material m, String name, ArrayList<String> lore, boolean verzaubert, boolean ohneSchaden) {
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(lore);

        if(verzaubert) meta.addEnchant(Enchantment.ARROW_FIRE, 10, true);
        if(ohneSchaden){
            meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
            meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);

            AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);

            AttributeModifier speedModifier = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", 0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, speedModifier);
        }

        item.setItemMeta(meta);

        return item;
    }

    public ItemStack createItemWithLore(Material m, String name, ArrayList<String> lore, int menge) {
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(lore);


        item.setItemMeta(meta);
        item.setAmount(menge);

        return item;
    }


    public String getPrefix() {
        return prefix;
    }

    public String getNoperm() {
        return noperm;
    }

    public static Shopy getInstance() {
        return instance;
    }

    public Location getServerSpawn() {
        return serverSpawn;
    }

    public MySQL getMySQLConntion() {
        return mySQLConntion;
    }

    public HashMap<UUID, Shop> getSpielerShops() {
        return spielerShops;
    }

    public ArrayList<Player> getPlayersNotTeleport() {
        return playersNotTeleport;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public static HashMap<UUID, ArrayList<World>> getGeladeneTempWelten() {
        return geladeneTempWelten;
    }

    public HashMap<UUID, Dungeon> getSpielerDungeon() {
        return spielerDungeon;
    }

    public ScoreboardManger getScoreboardManger() {
        return scoreboardManger;
    }
    public DropManger getDropManger() {
        return dropManger;
    }

    public String getKonatktSupport() {
        return konatktSupport;
    }

    public Location getLocationFromString(String locationString) {
        Location ret = null;

        String[] dataArray = locationString.split(",");

        String worldname = dataArray[0].split("name=")[1];
        worldname = worldname.substring(0, worldname.length() - 1);
        World world = Bukkit.getWorld(worldname);

        double x =      Double.parseDouble(dataArray[1].split("=")[1]);
        double y =      Double.parseDouble(dataArray[2].split("=")[1]);
        double z =      Double.parseDouble(dataArray[3].split("=")[1]);
        float pitch =   Float.parseFloat(dataArray[4].split("=")[1]);
        float yaw =     Float.parseFloat(dataArray[5].split("=")[1].split("}")[0]);

        ret = new Location(world, x,y,z,yaw,pitch);

        return ret;
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

    public boolean rekursivLoeschen(File file) {
        if (!file.exists()) {
            return true;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    rekursivLoeschen(f);
                }
            }
        }

        return file.delete();
    }

    public boolean isWahrscheinlichkeit(double wahrscheinlichkeit) {
        if (wahrscheinlichkeit < 0 || wahrscheinlichkeit > 1) {
            throw new IllegalArgumentException("wahrscheinlichkeit must be between 0 and 1");
        }

        Random random = new Random();
        return random.nextDouble() <= wahrscheinlichkeit;
    }

    public Equipment.EquipmentSlot getEquipmentSlot(Material material) {
        if (material == null) {
            return null;
        }

        // Prüfen auf spezifische Ausrüstungstypen
        if (material.name().endsWith("_HELMET")) {
            return Equipment.EquipmentSlot.HELMET;
        } else if (material.name().endsWith("_CHESTPLATE")) {
            return Equipment.EquipmentSlot.CHESTPLATE;
        } else if (material.name().endsWith("_LEGGINGS")) {
            return Equipment.EquipmentSlot.LEGGINGS;
        } else if (material.name().endsWith("_BOOTS")) {
            return Equipment.EquipmentSlot.BOOTS;
        } else if (material.name().contains("SWORD") || material.name().contains("AXE") || material.name().contains("SHOVEL")
                || material.name().contains("PICKAXE") || material.name().contains("HOE")) {
            return Equipment.EquipmentSlot.HAND;
        } else if (material.name().contains("SHIELD") || material.name().contains("TRIDENT") || material.name().contains("BOW")
                || material.name().contains("CROSSBOW")) {
            return Equipment.EquipmentSlot.HAND;
        }

        // Standard: Rückgabe null, falls keine Zuordnung möglich
        return null;
    }
}
