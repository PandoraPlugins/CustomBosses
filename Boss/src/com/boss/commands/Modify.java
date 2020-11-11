package com.boss.commands;

import org.bukkit.entity.Player;

import com.boss.gui.BossModify;
import com.boss.messages.Permissions;

public class Modify extends SubCommand{

	@Override
	public void onCommand(Player player, String[] args) {
		BossModify gui = new BossModify(player);
		gui.open();
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "modify";
	}

	@Override
	public String permission() {
		// TODO Auto-generated method stub
		return Permissions.ADMIN;
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return "Modify and create bosses.";
	}

	@Override
	public String[] aliases() {
		// TODO Auto-generated method stub
		return new String[] {"modify", "edit", "create", "add"};
	}

}
