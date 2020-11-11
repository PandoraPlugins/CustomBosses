package com.boss.messages;

import org.bukkit.configuration.file.FileConfiguration;

import com.boss.config.ConfigManager;
import com.boss.utils.MessageUtils;

public class GenericMessages {

	public static FileConfiguration messages = ConfigManager.getConfig("messages").configuration;

	public static final String space = "\n" + "\n";
	
	public static final String PREFIX = MessageUtils.translateAlternateColorCodes(messages.getString("Messages.PREFIX"));
	public static final String INVALID_SYNTEX = MessageUtils.translateAlternateColorCodes(messages.getString("Messages.INVALID_SYNTEX")).replace("%PREFIX%", PREFIX);
	public static final String ERROR = MessageUtils.translateAlternateColorCodes(messages.getString("Messages.ERROR")).replace("%PREFIX%", PREFIX);
	public static final String INAVLID_ENTITY = MessageUtils.translateAlternateColorCodes(messages.getString("Messages.INAVLID_ENTITY")).replace("%PREFIX%", PREFIX);
	public static final String INAVLID_PERMISSION = MessageUtils.translateAlternateColorCodes(messages.getString("Messages.INAVLID_PERMISSION")).replace("%PREFIX%", PREFIX);
	
	
}
