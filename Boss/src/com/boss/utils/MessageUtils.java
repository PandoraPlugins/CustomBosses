package com.boss.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.boss.commands.CommandManager;
import com.boss.messages.GenericMessages;

public class MessageUtils {

	public static void consolePrint(String s) {
		System.out.println(s);
	}

	public static String translateAlternateColorCodes(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	public static List<String> translateAlternateColorCodes(List<String> s) {
		List<String> lore = new ArrayList<String>();
		for(String o : s) {
			lore.add(MessageUtils.translateAlternateColorCodes(o));
		}
		return lore;
	}

	public static void sendMessage(Player player, String s) {
		player.sendMessage(MessageUtils.translateAlternateColorCodes(s));
	}

	public static void sendConsoleMessage(String msg) {
		Bukkit.getConsoleSender().sendMessage(MessageUtils.translateAlternateColorCodes(msg));
	}

	public static void sendConsoleMessage(String[] msg) {
		for (int i = 0; i < msg.length; i++)
			System.out.println(MessageUtils.translateAlternateColorCodes(msg[i]));
	}

	public static void sendHelp(Player player) {
		CommandManager.getCommands().forEach(i -> {
			MessageUtils.sendMessage(player, GenericMessages.PREFIX + " " + "&6• &c" + i.name() + "&7 : &b" + i.info());
		});
	}
}
