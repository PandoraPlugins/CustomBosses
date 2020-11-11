package com.boss.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.boss.utils.Communication;

public class InputHandler extends SubEvent{

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "input handler";
	}
	
	@Override
	public String info() {
		// TODO Auto-generated method stub
		return "handles input for components";
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(Communication.players.containsKey(e.getPlayer())) {
			System.out.println("FOUNDDD");
			Communication.players.get(e.getPlayer()).onRecieve(e.getMessage());
			Communication.players.remove(e.getPlayer());
			e.setCancelled(true);
		}
	}

}
