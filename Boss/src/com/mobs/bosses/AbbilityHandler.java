package com.mobs.bosses;

import java.util.List;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.boss.abilities.Abilities;

public class AbbilityHandler {

	private List<Abilities> abbilities;
	
	public AbbilityHandler(List<Abilities> abbilities) {
		this.setAbbilities(abbilities);
	}
	
	public void update(EntityDamageEvent damageEvent) {
		abbilities.forEach(i -> {
			i.onEntityDamageEvent(damageEvent);			
		});
	}
	
	public void update(EntityDamageByEntityEvent damageEvent) {
		abbilities.forEach(i -> {
			i.onEntityDamageByEntityEvent(damageEvent);			
		});
	}
	
	public void onSpawn() {
		abbilities.forEach(i -> {
			i.onEntitySpawn();			
		});
	}

	public List<Abilities> getAbbilities() {
		return abbilities;
	}

	public boolean has(Abilities i) {
		for(Abilities x : this.abbilities) {
			if(x.material() == i.material()) {
				return true;
			}
		}
		return false;
	}
	
	public void setAbbilities(List<Abilities> abbilities) {
		this.abbilities = abbilities;
	}
	
	public void register(BossMeta meta) {
		abbilities.forEach(i -> i.setBossMeta(meta));
	}
	
}
