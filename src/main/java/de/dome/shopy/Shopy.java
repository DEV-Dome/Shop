package de.dome.shopy;

import de.dome.shopy.commands.SetSpawnCMD;
import de.dome.shopy.listener.lobby.BlockBreakListener;
import de.dome.shopy.listener.lobby.EntityDamageListener;
import de.dome.shopy.listener.lobby.PlayerJoinListener;
import de.dome.shopy.listener.lobby.WorldListener;
import de.dome.shopy.utils.MySQL;
import de.dome.shopy.utils.MySQLDefault;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Shopy extends JavaPlugin {

    private String prefix = "§9Shopy | ";
    private String noperm = prefix + "§cDafür hast du keine Berechtigung.";
    private static Shopy instance;
    private Location serverSpawn;
    MySQL mySQLConntion;


    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;

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

        Bukkit.getConsoleSender().sendMessage(prefix + "§aPlugin gestartet");
    }

    @Override
    public void onDisable() {
        super.onDisable();

        Bukkit.getConsoleSender().sendMessage(prefix + "§cPlugin getsoppt");
    }

    private void registerListener(){
        new BlockBreakListener();
        new EntityDamageListener();
        new PlayerJoinListener();
        new WorldListener();
    }

    private void registerCommands(){
        getCommand("setspawn").setExecutor(new SetSpawnCMD());
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
}
