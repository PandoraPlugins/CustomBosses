package com.boss.tags;

import java.util.ArrayList;
import java.util.List;

import com.boss.abilities.Abilities;
import com.boss.messages.GenericMessages;
import com.boss.utils.MessageUtils;

public class TagFactory {

	private String item = "";
	private List<String> itemList;
	
	
	public int health = 0;
	public String preset = "";
	public List<Abilities> abilities = new ArrayList<Abilities>();
	
	public TagFactory(String item) {
		this.item = item;
	}
	
	public TagFactory(List<String> itemList) {
		this.itemList = itemList;
	}
	
	public String parse() {
		return this.parse(this.item);
	}
	
	public List<String> listParse(){
		
		List<String> builder = new ArrayList<String>();
		
		this.itemList.forEach(i -> {
			
			builder.add(parse(i));
			
		});
		
		return builder;
	}
	
	private String parse(String item) {
		
		String parser = item;
		
		parser = parser.replace("%prefix%", GenericMessages.PREFIX);
		parser = parser.replace("%health%", this.health + "%");
		parser = parser.replace("%preset%", this.preset);
		
		
		
		if(parser.contains("%ability%")) {
			StringBuilder ability = new StringBuilder();
			
			for(Abilities i : this.abilities) {
				ability.append(MessageUtils.translateAlternateColorCodes(i.names()[0]) + ", ");
			}
			
			return parser.replace("%ability%", ability.toString().substring(0, ability.toString().length()-2) + ".");	
		}
		
		return parser;
		
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getPreset() {
		return preset;
	}

	public void setPreset(String preset) {
		this.preset = preset;
	}

	public List<String> getItemList() {
		return itemList;
	}

	public void setItemList(List<String> itemList) {
		this.itemList = itemList;
	}

	public List<Abilities> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<Abilities> abilities) {
		this.abilities = abilities;
	}
	
}
