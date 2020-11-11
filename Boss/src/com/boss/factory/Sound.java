package com.boss.factory;

public class Sound {
	
	private org.bukkit.Sound sound;
	private float level;
	
	public Sound(String sound) {
		if(sound.equalsIgnoreCase("None")) {
			this.setSound(org.bukkit.Sound.ANVIL_USE);
			this.setLevel(0f);
		}else {
			this.setSound(org.bukkit.Sound.valueOf(sound));
			this.setLevel(15f);
		}
	}

	public org.bukkit.Sound getSound() {
		return sound;
	}

	public void setSound(org.bukkit.Sound sound) {
		this.sound = sound;
	}

	public float getLevel() {
		return level;
	}

	public void setLevel(float level) {
		this.level = level;
	}

}
