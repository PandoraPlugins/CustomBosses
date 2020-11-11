package com.boss.gui;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.boss.abilities.Abilities;
import com.boss.abilities.AbilityHandler;
import com.boss.factory.BossFactory;
import com.boss.messages.Permissions;
import com.boss.utils.ComponentBuilder;
import com.boss.utils.MessageUtils;
import com.mobs.bosses.BossMeta;

public class AbilityPicker extends BossGUI{

	private AbilityHandler handler = new AbilityHandler();
	
	public AbilityPicker(Player player, BossMeta meta) {
		super(player, meta);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "&5Toggle abilities.";
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
			
			Abilities ability = handler.getAbbility(stack.getType());
			
			if(e.getClick() == ClickType.LEFT) {
				meta.getAbbilities().add(ability);	
				BossFactory.addAbilityToPreset(ability, meta.getPreset());
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(MessageUtils.translateAlternateColorCodes("&6" + ability.names()[0] + " &7(&aEnabled&7)"));
				stack.setItemMeta(meta);
			}
			
			if(e.getClick() == ClickType.RIGHT) {
				meta.getAbbilities().remove(ability);
				BossFactory.removeAbilityToPreset(ability, meta.getPreset());
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(MessageUtils.translateAlternateColorCodes("&6" + ability.names()[0] + " &7(&cDisabled&7)"));
				stack.setItemMeta(meta);
			}
			
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
		
		for(int i = 0; i < handler.types.size(); i++) {
	
			Abilities ability = handler.types.get(i);
			
			ItemStack spawner = new ItemStack(ability.material(), 1);
			ItemMeta meta = spawner.getItemMeta();
			
			if(this.meta.getAbilityHandler().has(ability)) {
				meta.setDisplayName(MessageUtils.translateAlternateColorCodes("&6" + ability.names()[0] + " &7(&aEnabled&7)"));	
			}else {
				meta.setDisplayName(MessageUtils.translateAlternateColorCodes("&6" + ability.names()[0] + " &7(&cDisabled&7)"));
			}
			
			meta.setLore(ComponentBuilder.createLore("&3Right click: &aEnable", "&3Left click: &cDisable"));
			spawner.setItemMeta(meta);
			
			inv.addItem(spawner);
			
		}
	
	}

}
