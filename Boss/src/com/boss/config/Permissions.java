package com.boss.config;

import org.bukkit.configuration.file.FileConfiguration;

import com.boss.identification.ConfigID;

public class Permissions extends Config{

	@Override
	public int version() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return "permissions config";
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "permissions";
	}

	@Override
	public ConfigID indentification() {
		// TODO Auto-generated method stub
		return ConfigID.PERMISSIONS;
	}

	@Override
	public FileConfiguration getInstance() {
		// TODO Auto-generated method stub
		return configuration;
	}

	@Override
	public boolean exception() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String folder() {
		// TODO Auto-generated method stub
		return null;
	}

}
