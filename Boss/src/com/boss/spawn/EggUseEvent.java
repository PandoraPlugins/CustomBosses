package com.boss.spawn;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.boss.events.SubEvent;
import com.boss.factory.BossFactory;
import com.mobs.bosses.BossEntitiy;

import de.tr7zw.nbtapi.NBTItem;

public class EggUseEvent extends SubEvent{

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "event";
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return "allows for the spawing of bosses.";
	}
	
	@EventHandler()
	public void interact(PlayerInteractEvent e) {
		
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if(e.getItem() == null) return;
		
		NBTItem tag = new NBTItem(e.getItem());
		
		if(!tag.hasKey("preset")) return;
		
		String preset = tag.getString("preset");
		
		BossEntitiy entity = BossFactory.create(preset);
		
		entity.spawn(e.getPlayer());
		
		e.setCancelled(true);
		
		ItemStack hand = e.getPlayer().getInventory().getItemInHand();
		
		if(hand.getAmount() == 1) {
			e.getPlayer().getInventory().removeItem(hand);
			return;
		}
		
		e.getPlayer().getInventory().getItemInHand().setAmount(hand.getAmount()-1);
		
	}

}
