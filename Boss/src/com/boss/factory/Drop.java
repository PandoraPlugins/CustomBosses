package com.boss.factory;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Drop {

	private Material drop;
	private int minRange;
	private int maxRange;
	
	public Drop(Material drop, int minRange, int maxRange) {
		this.drop = drop;
		this.minRange = minRange;
		this.maxRange = maxRange;
	}

	public Material getDrop() {
		return drop;
	}

	public void setDrop(Material drop) {
		this.drop = drop;
	}

	public int getMinRange() {
		return minRange;
	}

	public void setMinRange(int minRange) {
		this.minRange = minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}
	
	public int getDropAmount() {
		return ThreadLocalRandom.current().nextInt(minRange, maxRange);
	}
	
	public ItemStack get() {
		ItemStack stack = new ItemStack(drop, this.getDropAmount());
		return stack;
	}
	
}
