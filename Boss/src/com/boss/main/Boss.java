package com.boss.main;
import java.io.File;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.boss.config.ConfigManager;
import com.boss.factory.BossFactory;


public class Boss extends JavaPlugin implements Listener{


    public static Boss instance;
    public com.boss.commands.CommandManager CommandManager;
	public static File ParentFolder;
	
	@Override
	public void onEnable(){
		
		ParentFolder = getDataFolder();
	    instance = this;
		
	    //ConfigManager.setup(this);
	    //ConfigManager.save(this);
	    
	    BossFactory.register();
	    
	    getServer().getPluginManager().registerEvents(this, this);
	    
	    this.CommandManager = new com.boss.commands.CommandManager();
	    
	    this.CommandManager.setup(this);
	    
	    com.boss.events.EventHandler events = new com.boss.events.EventHandler();
	    events.setup();

	}
	
	@Override
	public void onDisable(){
		
		
	}


	public static Boss getInstance() {
		return instance;
	}
		
	
}
