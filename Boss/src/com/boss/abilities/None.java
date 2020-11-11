package com.boss.abilities;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageEvent;

public class None extends Abilities{

	@Override
	public int id() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] names() {
		// TODO Auto-generated method stub
		return new String[] {"nothing"};
	}

	@Override
	public boolean onEntityDamageEvent(EntityDamageEvent e) {
		return false;
	}

	@Override
	public Material material() {
		// TODO Auto-generated method stub
		return Material.DIRT;
	}

}
