package com.boss.gui;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.DyeColor;
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

import com.boss.error.Error;
import com.boss.factory.BossFactory;
import com.boss.messages.GenericMessages;
import com.boss.messages.Permissions;
import com.boss.utils.ComponentBuilder;
import com.boss.utils.MessageUtils;
import com.mobs.bosses.BossMeta;

public class BossModify extends GUI {

	private Map<Integer, BossMeta> presetMapping = new HashMap<Integer, BossMeta>();
	
	public BossModify(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "&cEdit your bosses.";
	}

	@Override
	public String permission() {
		// TODO Auto-generated method stub
		return Permissions.ADMIN;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 45;
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

	@SuppressWarnings("deprecation")
	@Override
	public void onClickInventory(InventoryClickEvent e) {
		
		ItemStack item = this.inv.getItem(e.getSlot());
		
		if(e.getClick() == ClickType.RIGHT) {	
			if(item.getData().getData() ==  DyeColor.GREEN.getData()) {

				BossMeta meta = presetMapping.get(e.getSlot());

				this.player.closeInventory();
				Delete delete = new Delete(this.player, meta);
				delete.open();
			}
		}

		if(e.getClick() == ClickType.LEFT) {	
			if(item.getData().getData() ==  DyeColor.GREEN.getData()) {

				BossMeta meta = presetMapping.get(e.getSlot());

				this.player.closeInventory();
				BossEdit delete = new BossEdit(this.player, meta);
				delete.open();
			}
		}
		
		
		if(e.getSlot() == this.inv.getSize()-1) {	
			this.player.closeInventory();
			
            AnvilGUI gui = new AnvilGUI(player, new AnvilGUI.AnvilClickEventHandler() {
                @Override
                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                        event.setWillClose(true);
                        event.setWillDestroy(true);
                        
                        String preset = event.getName();
                        
                        System.out.println(preset);
                        
                        Error error = BossFactory.createPreset(preset);
                        
                        if(error == Error.None) {
                        	MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &aSuccessfuly made the preset &c" + preset);
                        }else {
                        	
                        	switch (error) {
							case Exists:
	                        	MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &cPreset already exists!");	
								return;
							case Space:
	                        	MessageUtils.sendMessage(player, GenericMessages.PREFIX + " &cPreset contains spaces!");	
								return;
							default:
								break;
							}
                        	
                        }
                        
                    } else {
                        event.setWillClose(false);
                        event.setWillDestroy(false);
                    }
                }
            }, () -> {
            	GUI modify = new BossModify(player);
            	modify.open();
            });

            ItemStack spawn = new ItemStack(Material.MONSTER_EGG);
            ItemMeta meta = spawn.getItemMeta();
            meta.setDisplayName(MessageUtils.translateAlternateColorCodes("Preset name."));
            meta.setLore(ComponentBuilder.createLore("&3Please type the name the preset."));
            spawn.setItemMeta(meta);
            
            gui.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, spawn);

            try {
                gui.open();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            } catch (InstantiationException e2) {
                e2.printStackTrace();
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

	@SuppressWarnings("deprecation")
	@Override
	public void Contents(Inventory inv) {
		
		for(int index = 0; index < BossFactory.Entities.size(); index++) {
			
			BossMeta i = BossFactory.Entities.get(index);
			
			ItemStack boss = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GREEN.getData());
			ItemMeta meta = boss.getItemMeta();
			String name = i.getPreset().replace("_", " ");
			meta.setDisplayName(MessageUtils.translateAlternateColorCodes("&6&l" + name));
			meta.setLore(ComponentBuilder.createLore("&2Left click to modify.", "&cRight click to delete."));
			boss.setItemMeta(meta);
			
			this.presetMapping.put(index, i);
			
			inv.addItem(boss);
			
		}
		
		ItemStack create = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.LIME.getData());
		ItemMeta createMeta = create.getItemMeta();
		createMeta.setDisplayName(MessageUtils.translateAlternateColorCodes("&aCreate"));
		createMeta.setLore(ComponentBuilder.createLore("&2Constructs a new boss."));
		create.setItemMeta(createMeta);
		inv.setItem(this.inv.getSize()-1, create);
		
		
		
		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData());
		ItemMeta meta = empty.getItemMeta();
		meta.setDisplayName(MessageUtils.translateAlternateColorCodes("&c[&4X&c]"));
		meta.setLore(ComponentBuilder.createLore("&cUnused boss slot."));
		empty.setItemMeta(meta);
		
		this.fill(empty);
	
	}

}
