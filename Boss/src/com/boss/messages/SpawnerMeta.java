package com.boss.messages;

import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

import com.boss.factory.BossFactory;
import com.boss.tags.TagFactory;
import com.boss.utils.MessageUtils;
import com.mobs.bosses.BossMeta;

public class SpawnerMeta {

	private String name;
	private List<String> lore; 
	
	public SpawnerMeta(String name, List<String> lore) {
		this.name = MessageUtils.translateAlternateColorCodes(name);
		this.lore = MessageUtils.translateAlternateColorCodes(lore);
	}
	
	public static SpawnerMeta create(BossMeta meta) {
		
		ConfigurationSection path = BossFactory.config.getConfigurationSection("Bosses.Eggs");
		
		String name = path.getString("Name");
		List<String> lore = path.getStringList("lore");
		
		TagFactory nameFactory = new TagFactory(name);
		nameFactory.setPreset(meta.getPreset());
		
		TagFactory loreFactory = new TagFactory(lore);
		loreFactory.setPreset(meta.getPreset());
		loreFactory.setAbilities(meta.getAbbilities());
		
		SpawnerMeta spawner = new SpawnerMeta(nameFactory.parse(), loreFactory.listParse());
		
		return spawner;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getLore() {
		return lore;
	}

	public void setLore(List<String> lore) {
		this.lore = lore;
	}
	
}
