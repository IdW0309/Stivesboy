package me.idw0309.custom.configs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.idw0309.custom.Custom;
import me.idw0309.custom.message.Message;
import org.bukkit.ChatColor;

import java.io.File;

public class Config {

    final Custom plugin = Custom.getInstance();

    public void check() {

        Message.sendToConsole("Checking if config exist...");

        if(!new File(plugin.getDataFolder().getAbsolutePath(), "config.yml").exists()) {
            Message.sendToConsole("Creating a new config...");

            plugin.getConfig().options().copyDefaults(true);
            plugin.getConfig().options().copyHeader(true);
            plugin.getConfig().options().header("Custom plugin V1.0 Config || Copyright (C) 2023 IdW0309");

            plugin.saveConfig();
        }
        Message.sendToConsole(ChatColor.GREEN + "Config exist!");
    }
}
