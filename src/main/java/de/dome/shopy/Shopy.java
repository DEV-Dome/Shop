package de.dome.shopy;

import de.dome.shopy.commands.*;
import de.dome.shopy.listener.lobby.*;
import de.dome.shopy.listener.lobby.BlockBreakListener;
import de.dome.shopy.listener.shop.*;
import de.dome.shopy.listener.shop.clicklistener.InventoryClickListenerItemLager;
import de.dome.shopy.listener.shop.clicklistener.InventoryClickListenerRessourenMarkplatz;
import de.dome.shopy.listener.shop.clicklistener.InventoryClickListenerWerkbank;
import de.dome.shopy.utils.MySQL;
import de.dome.shopy.utils.MySQLDefault;
import de.dome.shopy.utils.shop.Shop;
import dev.sergiferry.playernpc.api.NPC;
import dev.sergiferry.playernpc.api.NPCLib;
import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        playersNotTeleport = new ArrayList<>();
        spielerShops = new HashMap<>();
        inventoryManager = new InventoryManager(getInstance());
        inventoryManager.invoke();

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

        mySQLConntion = new MySQL();
        MySQLDefault.getInstance().sqlStartUp();

        NPCLib.getInstance().registerPlugin(getInstance());
        registerNPC();

        Bukkit.getConsoleSender().sendMessage(prefix + "§aPlugin gestartet");
    }

    @Override
    public void onDisable() {
        super.onDisable();

        /* Alle spieler beim Reload kicken */
        for(Player all : Bukkit.getOnlinePlayers()){
            TextComponent component = Component.text("§7Der Server wird neu geladen, damit das ohne Umstände passieren kann wurdest du gekickt. Du kannst gleich weiter spielen.");

            all.kick(component);
        }

        Bukkit.getConsoleSender().sendMessage(prefix + "§cPlugin getsoppt");
    }

    private void registerListener(){
        /* Lobby welt */
        new BlockBreakListener();
        new EntityDamageListener();
        new PlayerJoinListener();
        new WorldListener();
        new NPCInteractListener();
        new InventoryClickListener();

        /* Shop Welt */
        new de.dome.shopy.listener.shop.BlockBreakListener();
        new PlayerInteractListener();
        new BlockPlaceListener();

        new InventoryClickListenerWerkbank();
        new InventoryClickListenerRessourenMarkplatz();
        new InventoryClickListenerItemLager();
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
        getCommand("displayloadworlds").setExecutor(new DisplayLoadWorlds());
    }

    private void registerNPC(){
        /*NPC Config*/
        File configFile = new File(getInstance().getDataFolder(), "npc.yml");
        FileConfiguration npcConfig = YamlConfiguration.loadConfiguration(configFile);

        /*NPC Spawnen */
        if(npcConfig.contains("npc.ersteller.location")){
            ArrayList<String> npcText = new ArrayList<>();
            npcText.add("§aShop Verwalter");
            npcText.add("§7Hilft dir bei der Erstellung");
            npcText.add("§7oder Verwaltung deines Shops");

            NPC.Global npc = NPCLib.getInstance().generateGlobalNPC(getInstance(), "npc-welten-ersteller", npcConfig.getLocation("npc.ersteller.location"));
            npc.setText(npcText);
            npc.setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY5ODE1NjMwMzMxMCwKICAicHJvZmlsZUlkIiA6ICJlN2E3MzZhMjFlM2I0YzA2YmVhOGVmMjVmODg0MmJhZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJKZWVwMDIwNiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84ZjJmNGMxNmEwNjc3YzQ0NjI4NTBhZDQxODRjZDA5MGQ0NWM0YThmNGE5ZTk4ZWNhODMyZGU4Y2M3MWYyNmM0IgogICAgfQogIH0KfQ==", "kFvWhSu+ruqbkPUdekzeNTjFC6b/I7OwGiwZhvSu9N57cxmavieuMnKvOgMb5/B04f5ZbvsfOw8nM50HFZyYcNLIZBJKfTcr+N7hg1Co0sq106fgUi1cWI/5HfyoRKhHHw4owKhH/RcqgS2U6GElVgAdO/fyJMwq2U59Vqbo9rMN3eVBLulTjAXy/IfcefeOKvoDc/CSN/gLK4inw8cSD5R86uDuXCr1hUwNQUnt9t/tLXwiQ99ktRcR6nSKnu+L6u71hE7dzRFhokc7fXab4xSdZ2YXYQVilPlNZkgQJzn0CNIbgu8ZD1nn9lYHDFcsmhhnPKyCNlP8f70ZqkGUC8BM0WFedpn3cabOUHjRYnx9OrmKpDKHjktVn+VcH6Xat/8QFwbn6X8mCjfKPBMqhx1KxRhv73TgbWRu/EEVsEnO8ZQPZvaiKEjLVSrGgJblRhN76ojvBBv6QZ8u0bo7OAPBsAXsKQzjEBOria7zb03XNY+0CaPQ+nnKgOPzts6KOG83HQxSYazkztJ0JyBmmS+PCUtHZkgd660ni+thfHlFw44A1y0HcqJ3ZE1WaJdx8XVWnEw33ZX8FLj3s9JYFyZuYTk2U0avhR59yog2ZQHtsfRhHw2U7QBXXWZ/Z1GHRQeEp+Lql3+Y1onR2OQ9mY8aHA45IeW4ZaJJS8sWKJM=");
            npc.setItemInMainHand(createItem(Material.MAP, ""));
            npc.createAllPlayers();
            npc.show();
        }

        if(npcConfig.contains("npc.dungeonhändler.location")){
            ArrayList<String> npcText = new ArrayList<>();
            npcText.add("§5Dungeon Händler");
            npcText.add("§7Bringt dich in ferne Welten");

            NPC.Global npc = NPCLib.getInstance().generateGlobalNPC(getInstance(), "npc-dungeonhaendler", npcConfig.getLocation("npc.dungeonhändler.location"));
            npc.setText(npcText);
            npc.setSkin("ewogICJ0aW1lc3RhbXAiIDogMTYxOTIwNDU2NzU3OSwKICAicHJvZmlsZUlkIiA6ICJiYzRlZGZiNWYzNmM0OGE3YWM5ZjFhMzlkYzIzZjRmOCIsCiAgInByb2ZpbGVOYW1lIiA6ICI4YWNhNjgwYjIyNDYxMzQwIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZiMjBlMDBhMGE5ZGZmMjAwNWU0OWJkMjIxNWVmYWI5N2U5NjcyYTNkMTE1NzYxOGJkODY5MjVhNWJmNDFlMTIiCiAgICB9CiAgfQp9", "YXhhAM+MXF8y/ZhdrLf+TwhTY4Atl+himHLknAh+YB2aUNkP7yoWeYj1hmBEOxTJoKW1JRTwcsGm9MTuySiHTES0gkmC2zwuc2nQKhe/2ScROhbG6Z3uxqeZTI2ckCxJI03dnMjeck3TWOKRiwvQng1Pl2sKfdXPHA4nBIQDF71agD62ko5J5or/uc7xcKK9YaqJA0H+PoxAt344J/egyJQGoya+3U/xvrBooEKIMLuyvlTFWnuUyJ5QL+f2bhx7L+9ko+wrby8ei7XukSqLL9A0fJkRJnreGvwlNPx/mJ9kreh5yOe2FfhmWEFlG1L/q3iE1bLwhpS2GVi+OIdMQq+GvCWE8bZzshK0weIojFxEZ2chEPl5tloaEkqCvcux4k4Ub+hNKZPYeF3XFzGQh1XSYqEbJIKkgrVKxsRiqlTMQz/rQKRYlr/mocarV835sG97PB8Ur5N39ZuS/v7sVOYJuUv2U5YjOn8FMruzdpV8XN/UxCbqrIOaaGhwanfS3FN+qCS1LFJnieM8NUS6G2e+DijNoFUqk+Ml0msFwbiZZnc1dpU65oy/ldKEEJ0S7uSRd+s05DNdoDBe5QXsYP7rsJUXcYRRkvvfbek7ZAKuH3Jh9PZ5oxHpRmGH0Ys5Phbm4A2lMunCQhS5i5qM0iF6E1OvYFBSDnrjOG+a5v4=");
            npc.createAllPlayers();
            npc.show();
        }
        if(npcConfig.contains("npc.mona.location")){
            ArrayList<String> npcText = new ArrayList<>();
            npcText.add("§dEinhorn Prinessin Mona");
            npcText.add("§eMythische Händlerin");

            NPC.Global npc = NPCLib.getInstance().generateGlobalNPC(getInstance(), "npc-mona", npcConfig.getLocation("npc.mona.location"));
            npc.setText(npcText);
            npc.setSkin("eyJ0aW1lc3RhbXAiOjE1ODgwMTM1OTc3OTcsInByb2ZpbGVJZCI6IjIzZjFhNTlmNDY5YjQzZGRiZGI1MzdiZmVjMTA0NzFmIiwicHJvZmlsZU5hbWUiOiIyODA3Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84NzgyNTkwMWJlMjZkOWM4OWIxN2Q1MjZjYTI4NTUwMjZhMDg0NjZiYjRkNGFhMDAzZmJhMjIxMjZiNTk0NDM3IiwibWV0YWRhdGEiOnsibW9kZWwiOiJzbGltIn19fX0=", "J75uKO2HmSjBtST9m50VmKlMntdltIuP+tC7tim+93moC3YIe20pPm/Seld6Du8UQ+0ThGMXq5HOCW3Tq5HQbA1d2bp5bH81HxWegxmwN8QeOiiNlub+mSf9AV3R6oxmPgX+7xntfQfFnylTwOwYWsPw8ASE1Jy8FpTGxFR9vhJnNSxk7uREtIlo1G1j+8D42Cw5Ho2GuG+tBCDng4Eq03tQQeDA+IQJoRV+McOcwzGHIsh+Ky3xIHumecAOzC8Yt4EBkB34eaFRjOykdqQEXDQEK9A85lHqqFOPDPprhGCW+rk3Zx+7l/duU2s//ROuRYW6f7lenzxSIfYmnaFf5NTDYsIsaQJ61hpPjep9ThUDJ3ntaMGGevaQpcAKo6B/UFLLN/AwCrxQmXO38X+6n5Lttkw/+5LERe0YUznBaJkkJAkbQVyiTuLAwbCLF0qctRYWUj+8cMb7isOdKvTT+vlwjnS2WcWS5a8+mwJvtcCwkSOWTl4E8cFgGWdg0I6QalxMRiHhSayu2rbuKk9d6yfl1UR9lecH7aAnFuOLZVv7UHJEN4lhRugd7DhfKIHx8j0p9weesCc8xe3ABGdNkKzj/aZszqGa0Mk2obnKm7BCmGdAoze7s2ClQM4ouHSljJZKJ0z/TokJ+5VTflpK+e2d8jsOJy+XS0G0ENJDzkE=");
            npc.setItemInMainHand(createItem(Material.AMETHYST_SHARD, ""));
            npc.setPose(NPC.Pose.CROUCHING);
            npc.createAllPlayers();
            npc.show();
        }
    }

    public ItemStack createItem(Material m, String Name) {
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Name);

        item.setItemMeta(meta);

        return item;
    }
    public ItemStack createItemWithLore(Material m, String Name, ArrayList<String> lore) {
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Name);
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }
    public ItemStack createItemWithLore(Material m, String Name, ArrayList<String> lore, int menge) {
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Name);
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
}
