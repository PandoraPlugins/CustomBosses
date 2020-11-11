package com.boss.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.boss.abilities.Abilities;
import com.boss.abilities.None;
import com.boss.config.ConfigManager;
import com.boss.error.Error;
import com.boss.main.Boss;
import com.boss.messages.SpawnerMeta;
import com.boss.utils.ComponentBuilder;
import com.mobs.bosses.BossEntitiy;
import com.mobs.bosses.BossMeta;

import de.tr7zw.nbtapi.NBTItem;

public class BossFactory {

	public static FileConfiguration config = ConfigManager.getConfig("boss").configuration;
	
	public static List<BossMeta> Entities;
	
	@SuppressWarnings("unchecked")
	public static void register() {
		
		List<String> entities = new ArrayList<String>();
		
		config.getConfigurationSection("Bosses.Presets").getKeys(false).forEach(entities::add);

		BossFactory.Entities = new ArrayList<BossMeta>();

		for(String i : entities) {
			ConfigurationSection path = config.getConfigurationSection("Bosses.Presets." + i);
			
			String name = path.getString("Name");
			EntityType Entity = EntityType.valueOf(path.getString("Entity").toUpperCase());
			List<Abilities> abbilities = com.boss.abilities.Abilities.manager.parse(path.getStringList("Abbilities"));
			int minXP = path.getConfigurationSection("XP").getInt("MinXp");
			int maxXP = path.getConfigurationSection("XP").getInt("MaxXp");
			XP xp = new XP(minXP, maxXP);
	
			List<ItemStack> stack = new ArrayList<ItemStack>();
			
			ConfigurationSection items = path.getConfigurationSection("Items");
			
			List<String> body = new ArrayList<String>();
			
			items.getKeys(false).forEach(body::add);
			
			for(int x = 0; x < body.size(); x++) {
				
				ConfigurationSection currentItem = items.getConfigurationSection(body.get(x));
				
				ItemStack item = new ItemStack(Material.valueOf(currentItem.getString("item")));

				if(item.getType() == Material.AIR) {
					stack.add(new ItemStack(Material.AIR));
					continue;
				}
				
				List<Map<?, ?>> map = currentItem.getMapList("enchants");
				
				for(Map<?,?> current : map) {
				
					current.keySet().forEach(o -> {
					
						int level = Integer.parseInt(current.get(o).toString());
						
						item.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.getByName(o.toString()), level);
						
					});
				
				}
				
				stack.add(item);
				
			}
			
			List<Map<?, ?>> map = path.getMapList("Drops");
			
			List<Drop> dropList = new ArrayList<Drop>();
			
			for(Map<?,?> current : map) {
			
				current.keySet().forEach(o -> {
				
					List<Integer> range = (List<Integer>) convertObjectToList(current.get(o));
					
					dropList.add(new Drop(Material.valueOf(o.toString()), range.get(0), range.get(1)));
					
				});
			
			}
			
			ConfigurationSection sounds = path.getConfigurationSection("Sounds");

			com.boss.factory.Sound spawnSound = new com.boss.factory.Sound(sounds.getString("Spawn"));
			com.boss.factory.Sound attackSound = new com.boss.factory.Sound(sounds.getString("Attack"));
			com.boss.factory.Sound deathSound = new com.boss.factory.Sound(sounds.getString("Death"));
			com.boss.factory.Sound hurtSound = new com.boss.factory.Sound(sounds.getString("Hurt"));
			
			Sounds allSounds = new Sounds(spawnSound, attackSound, deathSound, hurtSound);
			
			BossMeta entitiy = new BossMeta(i, name, Entity, abbilities, stack, dropList, xp, allSounds);
			
			Entities.add(entitiy);
		}
		
	}
	
	public static void deletePreset(String preset) {
		ConfigurationSection path = config.getConfigurationSection("Bosses.Presets");
		if(path.contains(preset)) {
			path.set(preset, null);
			ConfigManager.save(Boss.instance);
		}
	}
	public static com.boss.error.Error createPreset(String preset) {
		ConfigurationSection path = config.getConfigurationSection("Bosses.Presets");

		try {
			BossFactory.prefixGet(preset).isNull();
			return Error.Exists;
		}catch (Exception e) {}
		
		if(preset.contains(" ")) {
			return Error.Space;
		}
		
		path.createSection(preset);
		path = config.getConfigurationSection("Bosses.Presets." + preset);
		
		path.set("Name", "New preset.");
		path.set("Entity", "ZOMBIE");
		path.set("Abbilities", new String[] {new None().names()[0]});
		
		path.createSection("Items");
		path.createSection("Items.hand");
		path.createSection("Items.head");
		path.createSection("Items.chest");
		path.createSection("Items.legs");
		path.createSection("Items.boots");
		
		for(String i : path.getConfigurationSection("Items").getKeys(false)) {
			ConfigurationSection items = path.getConfigurationSection("Items." + i);
			items.set("item", "AIR");
			items.set("enchants", new String[] {});
		}
		
		Map<String, Integer[]> dropMap = new HashMap<String, Integer[]>();
		
		dropMap.put("DIRT", new Integer[] {1, 64});

		List<Map<?, ?>> drops = new ArrayList<Map<?, ?>>();
		
		drops.add(dropMap);
		
		path.set("Drops", drops);
		
		path.createSection("XP");
		ConfigurationSection xpPath = path.getConfigurationSection("XP");
		xpPath.set("MinXp", 10);
		xpPath.set("MaxXp", 20);
		
		path.createSection("Sounds");
		ConfigurationSection soundPath = path.getConfigurationSection("Sounds");
		soundPath.set("Spawn", "None");
		soundPath.set("Attack", "None");
		soundPath.set("Death", "None");
		soundPath.set("Hurt", "None");
		
		ConfigManager.save(Boss.instance);
		
		BossFactory.register();
		
		return com.boss.error.Error.None;
	}
	
	public static void renamePreset(String name, String preset) {
		ConfigurationSection section = config.getConfigurationSection("Bosses.Presets." + preset);
		section.set("Name", name);
		ConfigManager.save(Boss.instance);
		BossFactory.register();
	}
	
	public static boolean setMobToPreset(String type, String preset) {
		try {
			EntityType entityType = EntityType.valueOf(type);
			ConfigurationSection section = config.getConfigurationSection("Bosses.Presets." + preset);
			section.set("Entity", entityType.name());
			ConfigManager.save(Boss.instance);
			BossFactory.register();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addAbilityToPreset(Abilities ability, String preset) {
		try {
			ConfigurationSection section = config.getConfigurationSection("Bosses.Presets." + preset);
			
			List<String> abilities = section.getStringList("Abbilities");
			
			if(abilities.contains(ability.names()[0])) {
				return true;
			}
			
			abilities.add(ability.names()[0]);
			section.set("Abbilities", abilities);
			ConfigManager.save(Boss.instance);
			BossFactory.register();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean removeAbilityToPreset(Abilities ability, String preset) {
		try {
			ConfigurationSection section = config.getConfigurationSection("Bosses.Presets." + preset);
			
			List<String> abilities = section.getStringList("Abbilities");
			
			if(!abilities.contains(ability.names()[0])) {
				return true;
			}
			
			abilities.remove(ability.names()[0]);
			section.set("Abbilities", abilities);
			ConfigManager.save(Boss.instance);
			BossFactory.register();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void setString(String location, String data) {
		config.getConfigurationSection("Bosses.Presets").set(location, data);
		ConfigManager.save(Boss.instance);
	}
	
	private static List<?> convertObjectToList(Object obj) {
	    List<?> list = new ArrayList<>();
	    if (obj.getClass().isArray()) {
	        list = Arrays.asList((Object[])obj);
	    } else if (obj instanceof Collection) {
	        list = new ArrayList<>((Collection<?>)obj);
	    }
	    return list;
	}
	
	public static BossEntitiy create(String preset) {
		BossMeta meta = BossFactory.Entities.stream().filter(i -> i.getPreset().equalsIgnoreCase(preset)).findFirst().get();
		
		List<ItemStack> items = new ArrayList<ItemStack>(); 
		
		items.add(meta.getHand());
		items.add(meta.getHelmet());
		items.add(meta.getChestplate());
		items.add(meta.getLeggings());
		items.add(meta.getBoots());
		
		BossMeta clone = new BossMeta(meta.getPreset(), meta.getName(), meta.getEntity(), meta.getAbbilities(), items, meta.getDrops(), meta.getXp(), meta.getSounds());
		BossEntitiy boss = new BossEntitiy(clone);
		return boss;
	}
	
	@SuppressWarnings("deprecation")
	public static BossMeta getSpawnEgg(String preset) {
		
		try {
			
			BossMeta i = BossFactory.prefixGet(preset);
			
			String[] remainder = preset.toLowerCase().split(i.getPreset().toLowerCase());
			
			int amount = 1;
			
			if(remainder.length > 0) {
				amount = Integer.parseInt(ComponentBuilder.build(remainder));
				if(amount < 0) {
					amount = 0;
				}
			}
			
			SpawnerMeta spawnerMeta = SpawnerMeta.create(i);
			
			ItemStack spawner = new ItemStack(Material.MONSTER_EGG, amount, (byte) i.getEntity().getTypeId());
			
			ItemMeta meta = spawner.getItemMeta();
			
			meta.setDisplayName(spawnerMeta.getName());
			meta.setLore(spawnerMeta.getLore());
			
			spawner.setItemMeta(meta);
			
			NBTItem tag = new NBTItem(spawner);
			tag.setString("preset", i.getPreset());
			tag.applyNBT(spawner);
			
			Egg egg = new Egg(spawner);
			i.setEgg(egg);
			
			return i;
			
			
		}catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static BossMeta get(String preset) {
		return BossFactory.Entities.stream().filter(i -> i.getPreset().equals(preset)).findFirst().get();
	}
	public static BossMeta prefixGet(String preset) {
		
		for(BossMeta i : BossFactory.Entities) {
			
			String[] presets = preset.split(" ");
			
			for(int x = 0; x < presets.length; x++) {
		
				String current = ComponentBuilder.build(Arrays.copyOf(presets, (x+1)));
				
				System.out.println("Checking for " + current);
				
				if(i.getPreset().equalsIgnoreCase(current)) {
					i.setIndexInInput(x);
					return i;
				}
				
			}
			
		}
		
		return null;
	}
	

	
	
}
