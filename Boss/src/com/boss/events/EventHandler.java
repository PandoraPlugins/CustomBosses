package com.boss.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

import com.boss.main.Boss;
import com.boss.spawn.EggUseEvent;

public class EventHandler {

    public static List<SubEvent> events = new ArrayList<SubEvent>();
	
    private Plugin plugin = Boss.instance;
    
	public void setup() {
		
		events.add(new EggUseEvent());
		events.add(new InputHandler());
		
		events.forEach(i -> plugin.getServer().getPluginManager().registerEvents(i, plugin));
	}
	
}
