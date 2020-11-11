package com.boss.abilities;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.mobs.bosses.BossMeta;

public abstract class Abilities {

	public static AbilityHandler manager = new AbilityHandler(); //im lazy...
	
	public BossMeta meta;
	
	public abstract int id();
	public abstract String[] names();
	public abstract Material material();
	
	public boolean onEntityDamageEvent(EntityDamageEvent e) {return false;};
	public boolean onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {return false;};
	public boolean onEntitySpawn() {return false;};
	
	
	public void setBossMeta(BossMeta i) {
		this.meta = i;
	}
	
}
