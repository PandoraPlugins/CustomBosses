package com.boss.gui;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.boss.messages.GenericMessages;
import com.boss.messages.Permissions;
import com.boss.utils.MessageUtils;
import com.mobs.bosses.BossMeta;

@SuppressWarnings("deprecation")
public class MobPicker extends BossGUI{

	public MobPicker(Player player, BossMeta meta) {
		super(player, meta);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "&5Select a mob.";
	}

	@Override
	public String permission() {
		// TODO Auto-generated method stub
		return Permissions.DEFAULT;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 27;
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
		
		ItemStack stack = e.getCurrentItem();
		
		if(stack.getType() != Material.AIR) {
			
			this.close();
			
			EntityType type = EntityType.valueOf(stack.getItemMeta().getDisplayName().substring(2));
			
            MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &aOperation complete!");
            BossMeta meta = this.meta;
            meta.setEntity(type);
            BossEdit gui = new BossEdit(player, meta);
            gui.open();
			
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

	@Override
	public void Contents(Inventory inv) {
		
		
		CreatureType[] entities = CreatureType.values();
		
		for(int i = 0; i < entities.length; i++) {
			ItemStack spawner = new ItemStack(Material.MONSTER_EGG, 1, (byte) entities[i].getTypeId());
			ItemMeta meta = spawner.getItemMeta();
			meta.setDisplayName(MessageUtils.translateAlternateColorCodes("&6" + entities[i]));
			spawner.setItemMeta(meta);
			
			inv.addItem(spawner);
		}
		
	}

}
