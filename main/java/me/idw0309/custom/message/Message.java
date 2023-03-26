package me.idw0309.custom.message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {
	
	final static String prefix2;
	
	static {
		prefix2 = ChatColor.RED + ChatColor.BOLD.toString() + "Customplugin " + ChatColor.WHITE;
	}
	
	public static void sendToConsole(String message) {

		Bukkit.getConsoleSender().sendMessage(prefix2 + message);

	}
	
	public static void sendToPlayer(CommandSender sender, String message) {
		sender.sendMessage(prefix2 + message);
	}

	public static void sendErrorToPlayer(Player pl, String message) {
		pl.sendMessage(prefix2 + message);
	}

}
