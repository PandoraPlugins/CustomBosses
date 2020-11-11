package com.boss.abilities;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.boss.utils.Luck;

public class Beserk extends Abilities{

	@Override
	public int id() {
		return 2;
	}

	@Override
	public String[] names() {
		// TODO Auto-generated method stub
		return new String[] {"beserk"};
	}

	@Override
	public boolean onEntityDamageEvent(EntityDamageEvent e) {
		if(!Luck.chance(0.1)) return false;
		LivingEntity entity = this.meta.getBoss();
		entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 10, 5));
		entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10, 2));
		entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 3));
		return true;
	}

	@Override
	public Material material() {
		// TODO Auto-generated method stub
		return Material.DIAMOND_SWORD;
	}
	
}
