package com.boss.utils;

import java.util.ArrayList;
import java.util.List;

public class ComponentBuilder {

	public static List<String> createLore(String... i) {
		List<String> lore = new ArrayList<String>();
		for(String o : i.clone()) {
			lore.add(MessageUtils.translateAlternateColorCodes(o));
		}
		return lore;
	}
	
	public static String build(String[] array) {
		StringBuilder builder = new StringBuilder();
		for(String i : array) {
			builder.append(i + " ");
		}
		return builder.toString().trim();
	}
	
}
