package com.boss.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Luck {

	public static boolean chance(double percentage) {
		return ThreadLocalRandom.current().nextDouble() <= percentage;
	}
	
}
