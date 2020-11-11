package com.boss.factory;

import org.bukkit.inventory.ItemStack;

public class Egg {

	private ItemStack stack;
	
	public Egg(ItemStack stack) {
		this.setStack(stack);
	}

	public ItemStack getStack() {
		return stack;
	}

	public void setStack(ItemStack stack) {
		this.stack = stack;
	}
	
}
