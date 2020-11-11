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
import org.bukkit.inventory.meta.ItemMeta;

import com.boss.factory.BossFactory;
import com.boss.messages.Permissions;
import com.boss.utils.ComponentBuilder;
import com.boss.utils.MessageUtils;
import com.mobs.bosses.BossMeta;

public class Delete extends BossGUI{

	public Delete(Player player, BossMeta meta) {
		super(player, meta);
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "&cConfirmation";
	}

	@Override
	public String permission() {
		// TODO Auto-generated method stub
		return Permissions.ADMIN;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 36;
	}

	@Override
	public Sound sound() {
		// TODO Auto-generated method stub
		return Sound.NOTE_STICKS;
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
		
		this.player.closeInventory();
		
		if(e.getSlot() == 21) {
			BossFactory.deletePreset(this.meta.getPreset());
			BossFactory.Entities.remove(this.meta);
			
			MessageUtils.sendMessage(this.player, "&aSuccessfully delete the preset &c" + this.meta.getPreset());
		}
		
		BossModify modify = new BossModify(this.player);
		modify.open();
		
		
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
		
		ItemStack accept = new ItemStack(Material.WOOL, 1, DyeColor.LIME.getData());
		
		ItemMeta acceptMeta = accept.getItemMeta();
		acceptMeta.setDisplayName(MessageUtils.translateAlternateColorCodes("&aAccept"));
		acceptMeta.setLore(ComponentBuilder.createLore("&2Deletes the preset &a" + this.meta.getPreset()));
		accept.setItemMeta(acceptMeta);
		
		ItemStack reject = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
		
		ItemMeta rejecttMeta = accept.getItemMeta();
		rejecttMeta.setDisplayName(MessageUtils.translateAlternateColorCodes("&cReject"));
		rejecttMeta.setLore(ComponentBuilder.createLore("&4Cancels this operation."));
		reject.setItemMeta(rejecttMeta);
		
		accept.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		reject.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		
		inv.setItem(21, accept);
		inv.setItem(23, reject);
		
		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());
		
		this.fill(empty);

		
	}
	
}
