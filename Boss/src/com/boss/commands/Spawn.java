package com.boss.commands;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.boss.factory.BossFactory;
import com.boss.messages.GenericMessages;
import com.boss.messages.Permissions;
import com.boss.utils.MessageUtils;
import com.mobs.bosses.BossMeta;

public class Spawn extends SubCommand{

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "give";
	}

	@Override
	public String permission() {
		// TODO Auto-generated method stub
		return Permissions.ADMIN;
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return "Recieve boss spawn eggs.";
	}

	@Override
	public String[] aliases() {
		// TODO Auto-generated method stub
		return new String[] {"spawn"};
	}

	@Override
	public void onCommand(Player player, String[] args) {
		
		if(args.length > 1) {
			
			
			StringBuilder builder = new StringBuilder();
			
			for(int i = 1; i < args.length; i++) {
				builder.append(args[i] + " ");
			}
			
			String contents = builder.toString().trim();
			
			try {
				
				BossMeta meta = BossFactory.getSpawnEgg(contents);
				ItemStack spawn = meta.getEgg().getStack();
				
				player.getInventory().addItem(spawn);
				
				MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &AYou have recieved " + spawn.getAmount() + "x " + meta.getPreset() + ".");
			
			}catch (Exception e) {
				MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &cFailed to give preset " + contents + ".");
			}
			
			return;
		}
		
		new com.boss.gui.Spawn(player).open();
		
	}

	
}
