package com.boss.abilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public class AbilityHandler {
	
	public List<Abilities> types;
	
	public AbilityHandler() {
		
		types = new ArrayList<Abilities>();
		
		types.add(new Swiftness());
		types.add(new Beserk());
		types.add(new IronSkin());
		types.add(new LifeSteal());
		
	}
	

	public List<Abilities> parse(List<String> parse) {
		
		List<Abilities> abbilities = new ArrayList<Abilities>();
		
		for(String i : parse) {
			abbilities.add(getAbbility(i));
		}
			
		return abbilities;
	}
	
	
	public Abilities getAbbility(String i) {
		for(Abilities x : types) {
			for(String o : x.names()) {
				if(i.toLowerCase().equalsIgnoreCase(o.toLowerCase())) {
					return x;
				}
			}
		}
		return new None();
	}
	
	public Abilities getAbbility(Material i) {
		for(Abilities x : types) {
			if(x.material() == i) {
				return x;
			}
		}
		return new None();
	}
	
}
