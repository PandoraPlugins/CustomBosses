package com.boss.config;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.boss.utils.MessageUtils;

public class ConfigManager {

    public static ArrayList<Config> config = new ArrayList<Config>();
    
    public static void setup(Plugin plugin) {
    	
    	ConfigManager.config.add(new Messages());
    	ConfigManager.config.add(new Permissions());
    	ConfigManager.config.add(new Boss());
          	
    	for(int i = 0; i < config.size(); i++) {
    		config.get(i).setup(plugin);
    		
    		if(config.get(i).folder() == "" || config.get(i).folder() == null) {
    		if(!config.get(i).configuration.contains("version") || (config.get(i).configuration.getInt("version") != config.get(i).version) && (!config.get(i).exception())) {
    			new File(plugin.getDataFolder(), config.get(i).name() + ".yml").delete();
    			plugin.saveResource(config.get(i).name() + ".yml", false);
       			config.get(i).configuration.setDefaults(YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), config.get(i).name() + ".yml")));
       			MessageUtils.sendConsoleMessage(config.get(i).name + " has an invalid version reseting...");
    		}
    		}else {
        		if(!config.get(i).configuration.contains("version") || (config.get(i).configuration.getInt("version") != config.get(i).version) && (!config.get(i).exception())) {
        			new File(plugin.getDataFolder()+"\\"+config.get(i).folder()+"\\", config.get(i).name() + ".yml").delete();
        			plugin.saveResource(config.get(i).folder()+"\\"+config.get(i).name() + ".yml", false);
        			config.get(i).configuration = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), config.get(i).name() + ".yml"));
        			MessageUtils.sendConsoleMessage(config.get(i).name + " has an invalid version reseting...");
        		}
    		}
    	}
    	
    	
    }
    
    public static void reset() {
    	config.forEach(i -> i.configFile.delete());
    }
    
    public static Config getConfig(String name) {
    	return config.stream().filter(i -> i.name().equalsIgnoreCase(name)).findFirst().get();
    }
    
    public static void save(Plugin plugin) {
    	config.forEach(i -> i.save(plugin));
    }
    
}
