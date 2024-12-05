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
import de.dome.shopy.listener.shop.*;
import de.dome.shopy.listener.shop.clicklistener.InventoryClickListenerItemLager;
import de.dome.shopy.listener.shop.clicklistener.InventoryClickListenerKundenKauf;
import de.dome.shopy.listener.shop.clicklistener.InventoryClickListenerRessourenMarkplatz;
import de.dome.shopy.listener.shop.clicklistener.InventoryClickListenerWerkbank;
import de.dome.shopy.utils.*;
import de.dome.shopy.utils.shop.Shop;
import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.SkinTrait;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.joml.Matrix2d;

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

        registerNPC();
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
        for (NPCRegistry npcRegistry : CitizensAPI.getNPCRegistries()){
            for(NPC npc : npcRegistry.sorted()){
                npc.despawn();
                npc.destroy();
            }
            npcRegistry.saveToStore();
        }
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
        new InventoryClickListener();
        new PlayerDeathListener();
        new de.dome.shopy.listener.lobby.PlayerRespawnListener();

        /* Shop Welt */
        new de.dome.shopy.listener.shop.BlockBreakListener();
        new PlayerInteractListener();
        new BlockPlaceListener();

        new InventoryClickListenerWerkbank();
        new InventoryClickListenerRessourenMarkplatz();
        new InventoryClickListenerItemLager();
        new InventoryClickListenerKundenKauf();

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
    }

    private void registerNPC(){
        /*NPC Config*/
        File configFile = new File(getInstance().getDataFolder(), "npc.yml");
        FileConfiguration npcConfig = YamlConfiguration.loadConfiguration(configFile);

        /*NPC Spawnen */
        if(npcConfig.contains("npc.ersteller.location")){
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§aShop Verwalter");
            npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("ShopVerwalter", "kFvWhSu+ruqbkPUdekzeNTjFC6b/I7OwGiwZhvSu9N57cxmavieuMnKvOgMb5/B04f5ZbvsfOw8nM50HFZyYcNLIZBJKfTcr+N7hg1Co0sq106fgUi1cWI/5HfyoRKhHHw4owKhH/RcqgS2U6GElVgAdO/fyJMwq2U59Vqbo9rMN3eVBLulTjAXy/IfcefeOKvoDc/CSN/gLK4inw8cSD5R86uDuXCr1hUwNQUnt9t/tLXwiQ99ktRcR6nSKnu+L6u71hE7dzRFhokc7fXab4xSdZ2YXYQVilPlNZkgQJzn0CNIbgu8ZD1nn9lYHDFcsmhhnPKyCNlP8f70ZqkGUC8BM0WFedpn3cabOUHjRYnx9OrmKpDKHjktVn+VcH6Xat/8QFwbn6X8mCjfKPBMqhx1KxRhv73TgbWRu/EEVsEnO8ZQPZvaiKEjLVSrGgJblRhN76ojvBBv6QZ8u0bo7OAPBsAXsKQzjEBOria7zb03XNY+0CaPQ+nnKgOPzts6KOG83HQxSYazkztJ0JyBmmS+PCUtHZkgd660ni+thfHlFw44A1y0HcqJ3ZE1WaJdx8XVWnEw33ZX8FLj3s9JYFyZuYTk2U0avhR59yog2ZQHtsfRhHw2U7QBXXWZ/Z1GHRQeEp+Lql3+Y1onR2OQ9mY8aHA45IeW4ZaJJS8sWKJM=", "ewogICJ0aW1lc3RhbXAiIDogMTY5ODE1NjMwMzMxMCwKICAicHJvZmlsZUlkIiA6ICJlN2E3MzZhMjFlM2I0YzA2YmVhOGVmMjVmODg0MmJhZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJKZWVwMDIwNiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84ZjJmNGMxNmEwNjc3YzQ0NjI4NTBhZDQxODRjZDA5MGQ0NWM0YThmNGE5ZTk4ZWNhODMyZGU4Y2M3MWYyNmM0IgogICAgfQogIH0KfQ==");

            npc.spawn(npcConfig.getLocation("npc.ersteller.location"));
        }

        if(npcConfig.contains("npc.dungeonhändler.location")){
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§5Dungeon Händler");
            npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("DungeonHaendler","YXhhAM+MXF8y/ZhdrLf+TwhTY4Atl+himHLknAh+YB2aUNkP7yoWeYj1hmBEOxTJoKW1JRTwcsGm9MTuySiHTES0gkmC2zwuc2nQKhe/2ScROhbG6Z3uxqeZTI2ckCxJI03dnMjeck3TWOKRiwvQng1Pl2sKfdXPHA4nBIQDF71agD62ko5J5or/uc7xcKK9YaqJA0H+PoxAt344J/egyJQGoya+3U/xvrBooEKIMLuyvlTFWnuUyJ5QL+f2bhx7L+9ko+wrby8ei7XukSqLL9A0fJkRJnreGvwlNPx/mJ9kreh5yOe2FfhmWEFlG1L/q3iE1bLwhpS2GVi+OIdMQq+GvCWE8bZzshK0weIojFxEZ2chEPl5tloaEkqCvcux4k4Ub+hNKZPYeF3XFzGQh1XSYqEbJIKkgrVKxsRiqlTMQz/rQKRYlr/mocarV835sG97PB8Ur5N39ZuS/v7sVOYJuUv2U5YjOn8FMruzdpV8XN/UxCbqrIOaaGhwanfS3FN+qCS1LFJnieM8NUS6G2e+DijNoFUqk+Ml0msFwbiZZnc1dpU65oy/ldKEEJ0S7uSRd+s05DNdoDBe5QXsYP7rsJUXcYRRkvvfbek7ZAKuH3Jh9PZ5oxHpRmGH0Ys5Phbm4A2lMunCQhS5i5qM0iF6E1OvYFBSDnrjOG+a5v4=", "ewogICJ0aW1lc3RhbXAiIDogMTYxOTIwNDU2NzU3OSwKICAicHJvZmlsZUlkIiA6ICJiYzRlZGZiNWYzNmM0OGE3YWM5ZjFhMzlkYzIzZjRmOCIsCiAgInByb2ZpbGVOYW1lIiA6ICI4YWNhNjgwYjIyNDYxMzQwIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZiMjBlMDBhMGE5ZGZmMjAwNWU0OWJkMjIxNWVmYWI5N2U5NjcyYTNkMTE1NzYxOGJkODY5MjVhNWJmNDFlMTIiCiAgICB9CiAgfQp9");

            Equipment eq = new Equipment();
            eq.linkToNPC(npc);
            eq.set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.STONE_SWORD));
            eq.set(4, new ItemStack(Material.SHIELD));

            npc.getTraitOptional(Equipment.class).or(eq);

            npc.spawn(npcConfig.getLocation("npc.dungeonhändler.location"));
        }
        if(npcConfig.contains("npc.mona.location")){
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§dEinhorn Prinessin Mona");
            npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("EinhornPrinessinMona", "J75uKO2HmSjBtST9m50VmKlMntdltIuP+tC7tim+93moC3YIe20pPm/Seld6Du8UQ+0ThGMXq5HOCW3Tq5HQbA1d2bp5bH81HxWegxmwN8QeOiiNlub+mSf9AV3R6oxmPgX+7xntfQfFnylTwOwYWsPw8ASE1Jy8FpTGxFR9vhJnNSxk7uREtIlo1G1j+8D42Cw5Ho2GuG+tBCDng4Eq03tQQeDA+IQJoRV+McOcwzGHIsh+Ky3xIHumecAOzC8Yt4EBkB34eaFRjOykdqQEXDQEK9A85lHqqFOPDPprhGCW+rk3Zx+7l/duU2s//ROuRYW6f7lenzxSIfYmnaFf5NTDYsIsaQJ61hpPjep9ThUDJ3ntaMGGevaQpcAKo6B/UFLLN/AwCrxQmXO38X+6n5Lttkw/+5LERe0YUznBaJkkJAkbQVyiTuLAwbCLF0qctRYWUj+8cMb7isOdKvTT+vlwjnS2WcWS5a8+mwJvtcCwkSOWTl4E8cFgGWdg0I6QalxMRiHhSayu2rbuKk9d6yfl1UR9lecH7aAnFuOLZVv7UHJEN4lhRugd7DhfKIHx8j0p9weesCc8xe3ABGdNkKzj/aZszqGa0Mk2obnKm7BCmGdAoze7s2ClQM4ouHSljJZKJ0z/TokJ+5VTflpK+e2d8jsOJy+XS0G0ENJDzkE=", "eyJ0aW1lc3RhbXAiOjE1ODgwMTM1OTc3OTcsInByb2ZpbGVJZCI6IjIzZjFhNTlmNDY5YjQzZGRiZGI1MzdiZmVjMTA0NzFmIiwicHJvZmlsZU5hbWUiOiIyODA3Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84NzgyNTkwMWJlMjZkOWM4OWIxN2Q1MjZjYTI4NTUwMjZhMDg0NjZiYjRkNGFhMDAzZmJhMjIxMjZiNTk0NDM3IiwibWV0YWRhdGEiOnsibW9kZWwiOiJzbGltIn19fX0=");

            Equipment eq = new Equipment();
            eq.linkToNPC(npc);
            eq.set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.ECHO_SHARD));
            eq.set(Equipment.EquipmentSlot.CHESTPLATE, new ItemStack(Material.DIAMOND_CHESTPLATE));
            eq.onSpawn();

            npc.getTraitOptional(Equipment.class).or(eq);

            npc.spawn(npcConfig.getLocation("npc.mona.location"));
        }
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


    public Location getLocationFromString(String locationString) {
        Location ret = null;

        String[] dataArray = locationString.split(",");

        String worldname = dataArray[0].split("name=")[1];
        worldname = worldname.substring(0, worldname.length() - 1);
        World world = Bukkit.getWorld(worldname);

        double x =      Double.parseDouble(dataArray[1].split("=")[1]);
        double y =      Double.parseDouble(dataArray[2].split("=")[1]);
        double z =      Double.parseDouble(dataArray[3].split("=")[1]);
        float pitch =   Float.parseFloat(dataArray[3].split("=")[1]);
        float yaw =     Float.parseFloat(dataArray[3].split("=")[1]);

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
}
