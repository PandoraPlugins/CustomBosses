package com.boss.gui;

import org.bukkit.entity.Player;

import com.mobs.bosses.BossMeta;

public abstract class BossGUI extends GUI{

	protected BossMeta meta;
	
	public BossGUI(Player player, BossMeta meta) {
		super(player);
		this.meta = meta;
	}

}
