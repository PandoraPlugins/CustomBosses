package com.boss.gui;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.boss.factory.BossFactory;
import com.boss.messages.GenericMessages;
import com.boss.messages.Permissions;
import com.boss.utils.Communication;
import com.boss.utils.ComponentBuilder;
import com.boss.utils.MessageUtils;
import com.mobs.bosses.BossMeta;

public class BossEdit extends BossGUI{

	public BossEdit(Player player, BossMeta meta) {
		super(player, meta);
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "&cSettings";
	}

	@Override
	public String permission() {
		// TODO Auto-generated method stub
		return Permissions.ADMIN;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 54;
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
		
		
		if(e.getSlot() == 0) {
			this.close();
			MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &cPlease type in your boss's name.");
			
			Communication.applyInput(player, (i) -> {  
	            BossFactory.renamePreset(i, meta.getPreset());
	            MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &aOperation complete!");
	            this.meta.setName(i);
	          
	            BossEdit gui = new BossEdit(player, meta);
	            gui.open();
	        });
		}
		
		if(e.getSlot() == 9) {
			this.close();
			
			if(e.getClick() == ClickType.RIGHT) {
		
				MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &cPlease type in your boss's entity type.");
				
				Communication.applyInput(player, (i) -> {
					i = i.toUpperCase().replace(" ", "_");
		            if(BossFactory.setMobToPreset(i, meta.getPreset())) {
		                MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &aOperation complete!");
			            this.meta.setEntity(EntityType.valueOf(i));
			            BossEdit gui = new BossEdit(player, meta);
			            gui.open();
		            }else {
		            	MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &cInvalid entity!");
			        }
		        });
				
			}else if(e.getClick() == ClickType.LEFT){
				
				GUI gui = new MobPicker(player, meta);
				gui.open();
				
			}
		}
		
		if(e.getSlot() == 18) {
			this.close();
			
			GUI gui = new AbilityPicker(player, meta);
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

	@SuppressWarnings("deprecation")
	@Override
	public void Contents(Inventory inv) {
		
		ItemStack rename = new ItemStack(Material.NAME_TAG, 1);
		
		ItemMeta renameMeta = rename.getItemMeta();
		renameMeta.setDisplayName(MessageUtils.translateAlternateColorCodes("&5Rename."));
		renameMeta.setLore(ComponentBuilder.createLore("&3Current name: &5" + meta.getName()));
		rename.setItemMeta(renameMeta);
		
		rename.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		
		ItemStack entity = new ItemStack(Material.MOB_SPAWNER, 1);

		ItemMeta entityMeta = entity.getItemMeta();
		entityMeta.setDisplayName(MessageUtils.translateAlternateColorCodes("&5Set mob."));
		entityMeta.setLore(ComponentBuilder.createLore("&3Current mob: &5" + meta.getEntity().name() + ".", "&3Right click: &cType in your mob.", "&3Left click: &cSelect from GUI."));
		entity.setItemMeta(entityMeta);
		
		entity.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		
		ItemStack abilities = new ItemStack(Material.SUGAR, 1);

		ItemMeta abilitiesMeta = abilities.getItemMeta();
		abilitiesMeta.setDisplayName(MessageUtils.translateAlternateColorCodes("&5Ability configure.."));
		abilities.setItemMeta(abilitiesMeta);
		
		abilities.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		
		inv.setItem(0, rename);
		inv.setItem(9, entity);
		inv.setItem(18, abilities);
		
		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());
		
		this.fill(empty);

		
	}
	
}
