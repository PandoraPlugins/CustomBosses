package com.mobs.bosses;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.boss.factory.Sound;
import com.boss.main.Boss;
import com.boss.tags.TagFactory;

public class BossEntitiy implements Listener{

	private BossMeta bossMeta;
	
	public BossEntitiy(BossMeta bossMeta) {
		this.setBossMeta(bossMeta);
	}
	
	public void spawn(Player player) {
		
		Location target = player.getTargetBlock((Set<Material>) null, 5).getLocation();

		target = target.add(0, 1, 0);
		
		Entity boss = player.getWorld().spawnEntity(target, bossMeta.getEntity());
		
		TagFactory factory = new TagFactory(bossMeta.getName());
		
		factory.setHealth(100);
		
		boss.setCustomName(factory.parse());
		
		this.bossMeta.setBoss(((LivingEntity) boss));
		
		this.bossMeta.getBoss().getEquipment().setItemInHand(bossMeta.getHand());
		this.bossMeta.getBoss().getEquipment().setHelmet(bossMeta.getHelmet());
		this.bossMeta.getBoss().getEquipment().setChestplate(bossMeta.getChestplate());
		this.bossMeta.getBoss().getEquipment().setLeggings(bossMeta.getLeggings());
		this.bossMeta.getBoss().getEquipment().setBoots(bossMeta.getBoots());

		Boss.instance.getServer().getPluginManager().registerEvents(this, Boss.instance);
		
		this.playSound(this.bossMeta.getSounds().getSpawnSound());
		
		this.bossMeta.getAbilityHandler().register(this.bossMeta);
		
		this.bossMeta.getAbilityHandler().onSpawn();
		
	}
	
	
	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent e) {
		if(e.getEntity().getUniqueId() == this.bossMeta.getBoss().getUniqueId()) {
			this.playSound(this.bossMeta.getSounds().getAttackSound());
			bossMeta.getAbilityHandler().update(e);
			this.update(e);
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		if(e.getDamager().getUniqueId() == this.bossMeta.getBoss().getUniqueId() && e.getEntity() instanceof Player) {
			this.playSound(this.bossMeta.getSounds().getHurtSound());
			bossMeta.getAbilityHandler().update(e);
			this.update(e);
		}
	}
	

	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if(e.getEntity().getUniqueId() == this.bossMeta.getBoss().getUniqueId()) {
			
			//e.getDrops().clear();
			
			this.playSound(this.bossMeta.getSounds().getDeathSound());
			
			this.bossMeta.getDrops().forEach(i -> {
				
				ItemStack stack = i.get();
				
				e.getDrops().add(stack);
				
			});
			
			e.setDroppedExp(this.bossMeta.getXp().get());
			
			this.dispose();
		}
	}

	public void update(EntityDamageEvent damage) {

		TagFactory factory = new TagFactory(bossMeta.getName());
		
		double percentage = (this.bossMeta.getBoss().getHealth() - damage.getFinalDamage())/this.bossMeta.getBoss().getMaxHealth();
		
		factory.setHealth((int)(percentage*100));
		
		bossMeta.getBoss().setCustomName(factory.parse());
		
	}
	
	public void dispose() {
		HandlerList.unregisterAll(this);
	}

	public BossMeta getBossMeta() {
		return bossMeta;
	}

	public void setBossMeta(BossMeta bossMeta) {
		this.bossMeta = bossMeta;
	}
	
	//Ignore, idk what i was thinking when making this method...
	public void playSound(Sound sound) {
		this.bossMeta.getBoss().getWorld().playSound(this.bossMeta.getBoss().getLocation(), sound.getSound(), sound.getLevel(), sound.getLevel());
	}
	
}
