package me.idw0309.custom;

import lombok.Getter;
import lombok.Setter;
import me.idw0309.custom.configs.Config;

import me.idw0309.custom.configs.PlayerDataConfig;
import me.idw0309.custom.data.PlayerData;
import me.idw0309.custom.data.PlayerManager;
import me.idw0309.custom.events.PlayerDeathListener;

import me.idw0309.custom.events.PlayerJoinQuitListener;
import me.idw0309.custom.events.PlayerRespawnListener;
import me.idw0309.custom.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public final class Custom extends JavaPlugin {

    public static Custom plugin;
    private static @Getter @Setter Custom instance;

    public static String path;

    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        setInstance(this);

        plugin = this;

        path = instance.getDataFolder().getAbsolutePath();

        playerManager = new PlayerManager();

        Events();
        createConfig();

        saveData();

        for (Player target : Bukkit.getServer().getOnlinePlayers()) {
            playerManager.loadPlayerData(target);
        }

    }

    @Override
    public void onDisable() {
        for (Player target : Bukkit.getServer().getOnlinePlayers()) {
            playerManager.savePlayerData(target);
        }
    }

    private void saveData() {

        //Saves all player data every 10Minutes
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    playerManager.savePlayerData(target);
                }
                Message.sendToConsole("Saved all player data");
            }
        }.runTaskTimer(plugin, 0L ,  20 * 60 * 10);

    }

    private void Events() {
        getServer().getPluginManager().registerEvents(new PlayerJoinQuitListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(playerManager), this);
    }

    private void createConfig() {
        Message.sendToConsole("Checking if datafolder exist...");

        if(!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        Message.sendToConsole(ChatColor.GREEN + "Datafolder exist!");

        Config config = new Config();
        PlayerDataConfig playerDataConfig = new PlayerDataConfig();

        config.check();
        playerDataConfig.check();

        Message.sendToConsole(ChatColor.GREEN + "Plugin assets loaded!");
    }
}
