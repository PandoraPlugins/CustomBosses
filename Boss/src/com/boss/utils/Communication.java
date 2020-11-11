package com.boss.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.entity.Player;

import com.boss.messages.GenericMessages;

public class Communication {
	
	public static Map<Player, Input> players = new HashMap<Player, Input>();
	
	public static void applyInput(Player player, Input execution) {
		players.put(player, execution);
		
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(players.containsKey(player)) {
					MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &cOperation timed out!");
					players.remove(player);	
				}
			}
		}, 60000);
		
		
		
	}
	
	
	

}
