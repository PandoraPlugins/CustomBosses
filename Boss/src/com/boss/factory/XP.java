package com.boss.factory;

import java.util.concurrent.ThreadLocalRandom;

public class XP {

	private int minXP;
	private int maxXP;
	
	public XP(int minXP, int maxXP) {
		this.minXP = minXP;
		this.maxXP = maxXP;
	}
	
	public int get() {
		return ThreadLocalRandom.current().nextInt(minXP, maxXP);
	}

	public int getMinXP() {
		return minXP;
	}

	public void setMinXP(int minXP) {
		this.minXP = minXP;
	}

	public int getMaxXP() {
		return maxXP;
	}

	public void setMaxXP(int maxXP) {
		this.maxXP = maxXP;
	}
	
}
