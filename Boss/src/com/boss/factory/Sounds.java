package com.boss.factory;

public class Sounds {
	
	com.boss.factory.Sound spawnSound;
	com.boss.factory.Sound attackSound;
	com.boss.factory.Sound deathSound;
	com.boss.factory.Sound hurtSound;
	
	public Sounds(Sound spawnSound, Sound attackSound, Sound deathSound, Sound hurtSound) {
		this.spawnSound = spawnSound;
		this.attackSound = attackSound;
		this.deathSound = deathSound;
		this.hurtSound = hurtSound;
	}

	public com.boss.factory.Sound getSpawnSound() {
		return spawnSound;
	}

	public void setSpawnSound(com.boss.factory.Sound spawnSound) {
		this.spawnSound = spawnSound;
	}

	public com.boss.factory.Sound getAttackSound() {
		return attackSound;
	}

	public void setAttackSound(com.boss.factory.Sound attackSound) {
		this.attackSound = attackSound;
	}

	public com.boss.factory.Sound getDeathSound() {
		return deathSound;
	}

	public void setDeathSound(com.boss.factory.Sound deathSound) {
		this.deathSound = deathSound;
	}

	public com.boss.factory.Sound getHurtSound() {
		return hurtSound;
	}

	public void setHurtSound(com.boss.factory.Sound hurtSound) {
		this.hurtSound = hurtSound;
	}
	
}
