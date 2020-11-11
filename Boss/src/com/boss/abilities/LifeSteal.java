package com.boss.abilities;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.boss.messages.GenericMessages;
import com.boss.utils.Luck;
import com.boss.utils.MessageUtils;

public class LifeSteal extends Abilities{

	@Override
	public int id() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public String[] names() {
		// TODO Auto-generated method stub
		return new String[] {"lifesteal"};
	}

	@Override
	public boolean onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		
		if(!Luck.chance(0.2)) return false;
		
		Player player = (Player) e.getEntity();
		                                   
		double originalHealth = ((LivingEntity)e.getDamager()).getHealth();
		
		double damage = e.getFinalDamage();
		
		double newHealth = originalHealth+damage > 20 ? 20 : originalHealth+damage;
	
		MessageUtils.sendMessage(player, GenericMessages.PREFIX + " Your life force is being stolen away... " + Math.round(damage) + "♥ " );
	
		((LivingEntity)e.getDamager()).setHealth(newHealth);
	
		return true;
	}

	@Override
	public Material material() {
		// TODO Auto-generated method stub
		return Material.REDSTONE;
	}
		

}
