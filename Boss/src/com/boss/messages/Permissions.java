package com.boss.messages;

import org.bukkit.configuration.file.FileConfiguration;

import com.boss.config.ConfigManager;

public class Permissions {

	public static FileConfiguration permissions = ConfigManager.getConfig("permissions").configuration;
	
	public static final String DEFAULT = permissions.getString("Permissions.default");
	public static final String ADMIN = permissions.getString("Permissions.admin");
	
}
