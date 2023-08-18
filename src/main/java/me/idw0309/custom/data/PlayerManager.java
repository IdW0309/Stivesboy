package me.idw0309.custom.data;
import lombok.SneakyThrows;
import me.idw0309.custom.Custom;
import me.idw0309.custom.message.Message;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {

    private final Map<Player, PlayerData> playerData = new ConcurrentHashMap<>();

    public void loadPlayerData(Player player) {
        File f = new File(Custom.path, "PlayerData.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        //Checking if the player exists
        if (cfg.getString("Playerdata." + player.getUniqueId() + ".Username") == null) {
            PlayerData data = new PlayerData(player.getUniqueId() + "", player.getName(), 0, 0);
            playerData.put(player, data);
            return;
        }

        int strenght = cfg.getInt("Playerdata." + player.getUniqueId() + ".Strenght");
        int resistance = cfg.getInt("Playerdata." + player.getUniqueId() + ".Resistance");

        PlayerData data = new PlayerData(player.getUniqueId() + "", player.getName(), strenght, resistance);
        playerData.put(player, data);
    }

    @SneakyThrows
    public void savePlayerData(Player player) {
        File f = new File(Custom.path, "PlayerData.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        PlayerData playerData = getPlayerData(player);

        cfg.set("Playerdata." + player.getUniqueId() + ".Username", player.getName());
        cfg.set("Playerdata." + player.getUniqueId() + ".Strenght", playerData.getStrength());
        cfg.set("Playerdata." + player.getUniqueId() + ".Resistance", playerData.getResistance());

        cfg.save(f);
    }

    public void unloadPlayer(Player player) {
        playerData.remove(player);
    }

    public PlayerData getPlayerData(Player player) {
        return playerData.get(player);
    }
}
