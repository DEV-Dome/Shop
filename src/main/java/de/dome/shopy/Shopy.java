package de.dome.shopy;

import de.dome.shopy.commands.*;
import de.dome.shopy.listener.lobby.*;
import de.dome.shopy.listener.shop.BlockPlaceListener;
import de.dome.shopy.listener.shop.PlayerInteractListener;
import de.dome.shopy.utils.MySQL;
import de.dome.shopy.utils.MySQLDefault;
import de.dome.shopy.utils.Shop;
import dev.sergiferry.playernpc.api.NPC;
import dev.sergiferry.playernpc.api.NPCLib;
import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.Material;
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

        /* Lade serverspawn */
        File configFile = new File(getInstance().getDataFolder(), "spawn.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        if(config.isLocation("server.spawn")){
            serverSpawn = config.getLocation("server.spawn");
        }else serverSpawn = null;

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
            all.kick();
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
        new de.dome.shopy.listener.shop.InventoryClickListener();
        new BlockPlaceListener();
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
    }

    private void registerNPC(){
        /*NPC Config*/
        File configFile = new File(getInstance().getDataFolder(), "npc.yml");
        FileConfiguration npcConfig = YamlConfiguration.loadConfiguration(configFile);

        /*NPC Spawnen */
        if(npcConfig.contains("npc.ersteller.location")){
            NPC.Global npc = NPCLib.getInstance().generateGlobalNPC(getInstance(), "npc-welten-ersteller", npcConfig.getLocation("npc.ersteller.location"));
            npc.setText("§aShop Ersteller");
            npc.setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY5ODE1NjMwMzMxMCwKICAicHJvZmlsZUlkIiA6ICJlN2E3MzZhMjFlM2I0YzA2YmVhOGVmMjVmODg0MmJhZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJKZWVwMDIwNiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84ZjJmNGMxNmEwNjc3YzQ0NjI4NTBhZDQxODRjZDA5MGQ0NWM0YThmNGE5ZTk4ZWNhODMyZGU4Y2M3MWYyNmM0IgogICAgfQogIH0KfQ==", "kFvWhSu+ruqbkPUdekzeNTjFC6b/I7OwGiwZhvSu9N57cxmavieuMnKvOgMb5/B04f5ZbvsfOw8nM50HFZyYcNLIZBJKfTcr+N7hg1Co0sq106fgUi1cWI/5HfyoRKhHHw4owKhH/RcqgS2U6GElVgAdO/fyJMwq2U59Vqbo9rMN3eVBLulTjAXy/IfcefeOKvoDc/CSN/gLK4inw8cSD5R86uDuXCr1hUwNQUnt9t/tLXwiQ99ktRcR6nSKnu+L6u71hE7dzRFhokc7fXab4xSdZ2YXYQVilPlNZkgQJzn0CNIbgu8ZD1nn9lYHDFcsmhhnPKyCNlP8f70ZqkGUC8BM0WFedpn3cabOUHjRYnx9OrmKpDKHjktVn+VcH6Xat/8QFwbn6X8mCjfKPBMqhx1KxRhv73TgbWRu/EEVsEnO8ZQPZvaiKEjLVSrGgJblRhN76ojvBBv6QZ8u0bo7OAPBsAXsKQzjEBOria7zb03XNY+0CaPQ+nnKgOPzts6KOG83HQxSYazkztJ0JyBmmS+PCUtHZkgd660ni+thfHlFw44A1y0HcqJ3ZE1WaJdx8XVWnEw33ZX8FLj3s9JYFyZuYTk2U0avhR59yog2ZQHtsfRhHw2U7QBXXWZ/Z1GHRQeEp+Lql3+Y1onR2OQ9mY8aHA45IeW4ZaJJS8sWKJM=");
            npc.setItemInMainHand(createItem(Material.MAP, ""));
            npc.createAllPlayers();
            npc.show();
        }

        if(npcConfig.contains("npc.dungeonhändler.location")){
            NPC.Global npc = NPCLib.getInstance().generateGlobalNPC(getInstance(), "npc-dungeonhaendler", npcConfig.getLocation("npc.dungeonhändler.location"));
            npc.setText("§5Dungeon Händler");
            npc.setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY5ODE2MDU5OTQ4OCwKICAicHJvZmlsZUlkIiA6ICI4NmRlYmE5ZjBjNTI0MTA0YWFkMjUyOTdhMTAzNjFmNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJFc2hlSG9yY2hhdGEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmQ1ZjBmM2IxYzBhOTg4NzFhYTdiNjUzNDVjZDViNjk5OGY0OTllZDg2MjIxY2EzZjBlZmM5Y2ViMTZkZGMzYiIKICAgIH0KICB9Cn0", "nkUvcz4VxiMu/O0wbaeQvBtdQ9IELF2QFsKzQcSD9jGhTjK9C7902cNYzXiPVzT3ZEkcMefvmpRlshJGUVxCQEyQL4y02mmJ+nOWSGPI8BpuM1oFfqYsNVcfyeeRCPsU3ikrHr40kOXzszdkBsR+r8oqrjjyMtivn+n3U7TEKebVIqndAKZv2ZGqiXevcqqBqmwkPpfW5OFz4+amUIn3oB+3rpzq8LtRP/LSsedmVA3iTW+5F7gP0XySS9uyLE2Ed52kziO3VZjAlK/Eq1oj+1xGAc4cHl34+ou6WVIGhUT/zOlRPmdsxLjPhYFm437eiV5Mwc6h9SCHDfvxF0dHhp/DNyRZmulpCBBUUIwMDZrTNALJhSlMFxfTQEpZ0uU9GCl6b0jL8pEHRQtSXbJIhZmlAS98akwBo6mkGUiTuQTKdDOqOW7m7ASb4dOplVA2gD46NyrtgYWFskPisLNm6SH4KYIzRIP+rWgNUZYfwjV9DybNHqhjyoIudboXvwWKbYU9QrLd3AUf/peYMG52G7ikpR6Ou41BM9Q+yDRfvZmjKKYnpC6keQ/wOEFpFSHQquYiNgCK44WdKT3+2JJIvtrZS10T3R3Qply9Scvuo/M5AEvzG2CJVFw5fB9fUGRhuUvlzyud3M2dGY0JcmrDk4A7gSyEg5lpwL+crUfyNWo=");
            npc.createAllPlayers();
            npc.show();
        }
        if(npcConfig.contains("npc.mona.location")){
            ArrayList<String> npcMonaText = new ArrayList<>();
            npcMonaText.add("§dEinhorn Prinessin Mona");
            npcMonaText.add("§eMythische Händlerin");

            NPC.Global npc = NPCLib.getInstance().generateGlobalNPC(getInstance(), "npc-mona", npcConfig.getLocation("npc.mona.location"));
            npc.setText(npcMonaText);
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
        meta.setUnbreakable(true);

        item.setItemMeta(meta);

        return item;
    }
    public ItemStack createItemWithLore(Material m, String Name, ArrayList<String> lore) {
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Name);
        meta.setUnbreakable(true);
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }
    public ItemStack createItemWithLore(Material m, String Name, ArrayList<String> lore, int menge) {
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Name);
        meta.setUnbreakable(true);
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
