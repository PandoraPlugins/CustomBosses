package com.boss.abilities;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageEvent;

public class FireBall extends Abilities{

	@Override
	public int id() {
		return 4;
	}

	@Override
	public String[] names() {
		return new String[] {"fireball"};
	}

	@Override
	public boolean onEntityDamageEvent(EntityDamageEvent e) {
		return false;
	}

	@Override
	public Material material() {
		// TODO Auto-generated method stub
		return Material.FIREBALL;
	}
	
	

}
