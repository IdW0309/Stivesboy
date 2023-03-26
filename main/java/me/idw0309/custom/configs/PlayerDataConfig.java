package me.idw0309.custom.configs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.idw0309.custom.Custom;
import me.idw0309.custom.message.Message;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
public class PlayerDataConfig {

    final Custom plugin = Custom.getInstance();

    public void check() {

        Message.sendToConsole("Checking if PlayerData config exist...");

        File file = new File(plugin.getDataFolder().getAbsolutePath(), "PlayerData.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        //Checks if file exist, otherwise it will make one
        if(!file.exists()) {

            config.options().header("Custom plugin V1.0 PlayerData Config || Copyright (C) 2023 IdW0309");
            try {
                //Try's to save the config, otherwise it will put out a error
                config.save(file);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        Message.sendToConsole(ChatColor.GREEN + "PlayerData config exist!");
    }
}
