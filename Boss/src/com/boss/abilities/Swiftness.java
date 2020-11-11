package com.boss.abilities;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Swiftness extends Abilities{

	@Override
	public int id() {
		return 1;
	}

	@Override
	public String[] names() {
		// TODO Auto-generated method stub
		return new String[] {"speed", "swiftness"};
	}

	@Override
	public boolean onEntitySpawn() {
		LivingEntity entity = this.meta.getBoss();
		entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1));
		return true;
	}

	@Override
	public Material material() {
		// TODO Auto-generated method stub
		return Material.IRON_BOOTS;
	}

}
