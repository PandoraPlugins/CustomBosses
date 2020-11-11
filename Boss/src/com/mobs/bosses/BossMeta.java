package com.mobs.bosses;

import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import com.boss.abilities.Abilities;
import com.boss.factory.Drop;
import com.boss.factory.Egg;
import com.boss.factory.Sounds;
import com.boss.factory.XP;
import com.boss.utils.MessageUtils;

public class BossMeta {

	private String preset;
	private String name;
	private EntityType entity;
	private List<Abilities> abilities;
	private ItemStack helmet;
	private ItemStack chestplate;
	private ItemStack leggings;
	private ItemStack boots;
	private ItemStack hand;
	private List<Drop> drops;
	private XP xp;
	private Sounds sounds;
	private Preset presetObject;
	private int indexInInput;
	private Egg egg;
	
	private AbbilityHandler abilityHandler;
	private LivingEntity boss; 
	
	public BossMeta(String preset, String name, EntityType entity, List<Abilities> abbilities, List<ItemStack> armour, List<Drop> drops, XP xp, Sounds sounds) {
		this.name = MessageUtils.translateAlternateColorCodes(name);
		this.entity = entity;
		this.abilities = abbilities;
		this.hand = armour.get(0);
		this.helmet = armour.get(1);
		this.chestplate = armour.get(2);
		this.leggings = armour.get(3);
		this.boots = armour.get(4);
		this.setPreset(preset);
		this.setDrops(drops);
		this.setXp(xp);
		this.setSounds(sounds);
		this.setAbilityHandler(new AbbilityHandler(abbilities));
		this.setPresetObject(new Preset(preset));
	}

	public BossMeta clone() {
		return new BossMeta(preset, name, entity, null, null, drops, xp, sounds);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EntityType getEntity() {
		return entity;
	}

	public void setEntity(EntityType entity) {
		this.entity = entity;
	}

	public List<Abilities> getAbbilities() {
		return abilities;
	}

	public void setAbbilities(List<Abilities> abbilities) {
		this.abilities = abbilities;
	}

	public ItemStack getHelmet() {
		return helmet;
	}

	public void setHelmet(ItemStack helmet) {
		this.helmet = helmet;
	}

	public ItemStack getChestplate() {
		return chestplate;
	}

	public void setChestplate(ItemStack chestplate) {
		this.chestplate = chestplate;
	}

	public ItemStack getLeggings() {
		return leggings;
	}

	public void setLeggings(ItemStack leggings) {
		this.leggings = leggings;
	}

	public ItemStack getBoots() {
		return boots;
	}

	public void setBoots(ItemStack boots) {
		this.boots = boots;
	}

	public ItemStack getHand() {
		return hand;
	}

	public void setHand(ItemStack hand) {
		this.hand = hand;
	}

	public LivingEntity getBoss() {
		return boss;
	}

	public void setBoss(LivingEntity boss2) {
		this.boss = boss2;
	}

	public AbbilityHandler getAbilityHandler() {
		return abilityHandler;
	}

	public void setAbilityHandler(AbbilityHandler abilityHandler) {
		this.abilityHandler = abilityHandler;
	}

	public String getPreset() {
		return preset;
	}

	public void setPreset(String preset) {
		this.preset = preset;
	}

	public List<Drop> getDrops() {
		return drops;
	}

	public void setDrops(List<Drop> drops) {
		this.drops = drops;
	}

	public XP getXp() {
		return xp;
	}

	public void setXp(XP xp) {
		this.xp = xp;
	}

	public Sounds getSounds() {
		return sounds;
	}

	public void setSounds(Sounds sounds) {
		this.sounds = sounds;
	}

	public Preset getPresetObject() {
		return presetObject;
	}

	public void setPresetObject(Preset presetObject) {
		this.presetObject = presetObject;
	}

	public int getIndexInInput() {
		return indexInInput;
	}

	public void setIndexInInput(int indexInInput) {
		this.indexInInput = indexInInput;
	}

	public Egg getEgg() {
		return egg;
	}

	public void setEgg(Egg egg) {
		this.egg = egg;
	}
	
	public boolean isNull() {
		return this.preset == null;
	}

}
