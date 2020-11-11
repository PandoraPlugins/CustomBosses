package com.boss.gui;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.boss.main.Boss;
import com.boss.messages.GenericMessages;
import com.boss.utils.MessageUtils;

public abstract class GUI implements Listener{

	Player player;
	Inventory inv;
	
	public GUI(Player player) {
		this.player = player;
		Boss.instance.getServer().getPluginManager().registerEvents(this, Boss.instance);
		inv = Bukkit.createInventory(player, size(), MessageUtils.translateAlternateColorCodes(name()));
	}
	
	@EventHandler()
	public void onOpen(InventoryOpenEvent e) {
		if(!e.getPlayer().hasPermission(permission())) {
			MessageUtils.sendMessage(player, GenericMessages.INAVLID_PERMISSION);
			e.setCancelled(true);
			return;
		}
		onOpenInventory(e);
	}
	
	@EventHandler()
	public void onClose(InventoryCloseEvent e) {
		this.onCloseInventory(e);
		this.close();
	}
	
	@EventHandler()
	public void onClick(InventoryClickEvent e) {
		e.setCancelled(!canTakeItems());
		if(e.getCurrentItem() == null) {
			return;
		}
		this.onClickInventory(e);
	}
	
	public abstract String name();
	public abstract String permission();
	
	public abstract int size();
	
	public abstract Sound sound();
	public abstract float soundLevel();
	public abstract boolean canTakeItems();
	
	public abstract void onClickInventory(InventoryClickEvent e);
	public abstract void onOpenInventory(InventoryOpenEvent e);
	public abstract void onCloseInventory(InventoryCloseEvent e);
	public abstract void Contents(Inventory inv);
	
	public void open() {
		player.getWorld().playSound(player.getLocation(), sound(), soundLevel(), soundLevel());
	    Contents(inv);
	    player.openInventory(inv);
	}
	
	public void addItem(int index, ItemStack item) {
		inv.setItem(index, item);
	}
	
	public void fill(ItemStack stack) {
		for(int i = 0; i < this.size(); i++) {
			if(this.inv.getItem(i) == null) {
				this.inv.setItem(i, stack);
			}
		}
	}
	
	public void close() {
		HandlerList.unregisterAll(this);
		player.closeInventory();
	}
	
}
