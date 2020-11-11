package com.boss.abilities;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class IronSkin extends Abilities{

	@Override
	public int id() {
		return 3;
	}

	@Override
	public String[] names() {
		return new String[] {"ironskin"};
	}

	@Override
	public boolean onEntityDamageEvent(EntityDamageEvent e) {
		LivingEntity entity = this.meta.getBoss();
		entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 2));
		return true;
	}

	@Override
	public Material material() {
		// TODO Auto-generated method stub
		return Material.IRON_INGOT;
	}
	
	

}
