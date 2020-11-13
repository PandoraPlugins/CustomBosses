package com.boss.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.boss.factory.BossFactory;
import com.boss.messages.GenericMessages;
import com.boss.messages.Permissions;
import com.boss.utils.BasicOperation;
import com.boss.utils.ComponentBuilder;
import com.boss.utils.MessageUtils;
import com.mobs.bosses.BossMeta;

public class BossInventoryModifyGUI extends BossGUI{

	private final int xOffset = 1;
	
	public BossInventoryModifyGUI(Player player, BossMeta meta) {
		super(player, meta);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "&cInventory";
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
		return true;
	}

	@Override
	public void onClickInventory(InventoryClickEvent e) {
		
		
		if(!BasicOperation.contains(e.getSlot(), 4, 13, 22, 31, 21) && !(e.getClickedInventory() instanceof PlayerInventory)) {
			
			if(e.getSlot() == size()-18-xOffset){ //copy
				inv.setItem(4, player.getInventory().getHelmet());
				inv.setItem(13, player.getInventory().getChestplate());
				inv.setItem(22, player.getInventory().getLeggings());
				inv.setItem(31, player.getInventory().getBoots());
				inv.setItem(21, player.getInventory().getItemInHand());
			}
			if(e.getSlot() == size()-9-xOffset){ //back
				this.close();
				GUI gui = new BossEdit(player, meta);
				gui.open();
			}
			if(e.getSlot() == size()-xOffset){ //accept
				
				List<ItemStack> stacks = new ArrayList<ItemStack>();
				
				stacks.add(inv.getItem(21)); //hand
				stacks.add(inv.getItem(4)); //head
				stacks.add(inv.getItem(13)); //chest
				stacks.add(inv.getItem(22)); //legs
				stacks.add(inv.getItem(31)); //boots
				
				if(BossFactory.setInventoryToPreset(stacks, meta.getPreset())) {
					MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &aInventory set!");
					BossFactory.load(player);
					this.meta.setHand(inv.getItem(21));
					this.meta.setHelmet(inv.getItem(4));
					this.meta.setChestplate(inv.getItem(13));
					this.meta.setLeggings(inv.getItem(22));
					this.meta.setBoots(inv.getItem(31));
				}else {
					MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &cAn error has accured! (Code 51)");
				}
				
				this.close();
				GUI gui = new BossEdit(player, meta);
				gui.open();
				
			}
			
			
			
			
			e.setCancelled(true);
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
		
		inv.setItem(4, meta.getHelmet());
		inv.setItem(13, meta.getChestplate());
		inv.setItem(22, meta.getLeggings());
		inv.setItem(31, meta.getBoots());
		inv.setItem(21, meta.getHand());
		
		inv.setItem(size()-18-xOffset, ComponentBuilder.createItem(Material.STAINED_GLASS_PANE, DyeColor.ORANGE, "&6Paste", "&3Copies your inventory."));
		inv.setItem(size()-9-xOffset, ComponentBuilder.createItem(Material.STAINED_GLASS_PANE, DyeColor.RED, "&cBack", "&3Return back to the edit menu."));
		inv.setItem(size()-xOffset, ComponentBuilder.createItem(Material.STAINED_GLASS_PANE, DyeColor.GREEN, "&aAccept"));
		
		this.fill(ComponentBuilder.createItem(Material.STAINED_GLASS_PANE, DyeColor.BLACK, "", ""), 4, 13, 22, 31, 21);
		
		
	}

}
