
package com.boss.commands;

import org.bukkit.entity.Player;

import com.boss.main.Boss;
import com.boss.messages.Permissions;
import com.boss.utils.MessageUtils;

public class Help extends SubCommand {

	private Boss plugin = Boss.getInstance();

    @Override
    public void onCommand(Player player, String[] args) {
    	MessageUtils.sendHelp(player);
    }

    @Override

    public String name() {
        return plugin.CommandManager.help;
    }

    @Override
    public String info() {
        return "Displays the help command.";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String permission() {
		return Permissions.DEFAULT;	
	}
	

}