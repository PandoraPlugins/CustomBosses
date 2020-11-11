package com.boss.gui;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.boss.factory.BossFactory;
import com.boss.messages.Permissions;
import com.mobs.bosses.BossMeta;

public class Spawn extends GUI{

	public Spawn(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "&cSpawn eggs";
	}

	@Override
	public String permission() {
		// TODO Auto-generated method stub
		return Permissions.ADMIN;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 45;
	}

	@Override
	public Sound sound() {
		// TODO Auto-generated method stub
		return Sound.CLICK;
	}

	@Override
	public float soundLevel() {
		// TODO Auto-generated method stub
		return 1f;
	}

	@Override
	public boolean canTakeItems() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClickInventory(InventoryClickEvent e) {
		// TODO Auto-generated method stub
		if(e.getSlot() < BossFactory.Entities.size()) {
			player.getInventory().addItem(this.inv.getItem(e.getSlot()));
		}
		
	}

	@Override
	public void onOpenInventory(InventoryOpenEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCloseInventory(InventoryCloseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void Contents(Inventory inv) {
		
		for(BossMeta i : BossFactory.Entities) {
			inv.addItem(BossFactory.getSpawnEgg(i.getPreset()).getEgg().getStack());
		}
		
		for(int i = BossFactory.Entities.size(); i < this.size(); i++) {
			
			ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());
			
			empty.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
			
			inv.setItem(i, empty);
		}
		
	}

}
