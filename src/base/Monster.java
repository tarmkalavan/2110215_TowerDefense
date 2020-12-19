package base;

import background.Coordinate;

public abstract class Monster implements Effectable{

	protected int currentHealth;
	protected int maxHealth;
	protected int speed;
	protected int reward;
	protected int armor;
	protected boolean isDead;
	protected Coordinate coords;

	public Monster(int maxHealth, int armor, int speed, int reward) {
		setCoords(0, 0);
		setCurrentHealth(maxHealth);
		setMaxHealth(maxHealth);
		setReward(reward);
		setSpeed(speed);
		setArmor(armor);
		setDead(false);
	}
	public abstract int takeDamage(int incomingDamage);
	
	//Effectable//
	public int effect(Castable caster) {
		int finalStat = 0;
		if(caster instanceof Tower) { //monster debuffed by tower
			String statAffected = ((Tower) caster).getBUFF_STAT();
			switch(statAffected) {
			case "armor":
				finalStat = (int) (this.getArmor() * ((Tower) caster).getBUFF_RATIO());
				this.setArmor(finalStat);
				break;
			case "speed":
				finalStat = (int) (this.getSpeed() * ((Tower) caster).getBUFF_RATIO());
				this.setSpeed(finalStat);
				break;
			}
		}
		return finalStat;
	}
	public int revertChange(Castable caster) {
		int finalStat = 0;
		boolean ratioIsInt = false;
		if(caster instanceof Tower) { //revert (monster debuffed by tower)
			String statAffected = ((Tower) caster).getBUFF_STAT();
			ratioIsInt = (((Tower) caster).getBUFF_RATIO() == (int) ((Tower) caster).getBUFF_RATIO());
			switch(statAffected) {
			case "armor":
				finalStat = (int) (this.getArmor() / ((Tower) caster).getBUFF_RATIO());
				if(!ratioIsInt) finalStat++;
				this.setArmor(finalStat);
				break;
			case "speed":
				finalStat = (int) (this.getSpeed() / ((Tower) caster).getBUFF_RATIO());
				if(!ratioIsInt) finalStat++;
				this.setSpeed(finalStat);
				break;
			}
		}
		return finalStat;
	}
	
	//SETTER//
	public void setCurrentHealth(int health) {
		this.currentHealth = Math.max(health, 0);
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = Math.max(maxHealth, 0);
	}

	public void setSpeed(int speed) {
		this.speed = Math.max(speed, 0);
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void setReward(int reward) {
		this.reward = Math.max(0, reward);
	}
	
	public void setArmor(int armor) {
		this.armor = Math.max(0,armor);
	}
	
	public void setCoords(int x, int y) {
		this.coords = new Coordinate(x,y);
	}
	
	//GETTER//
	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getSpeed() {
		return speed;
	}

	public boolean isDead() {
		return isDead;
	}

	public int getReward() {
		return reward;
	}

	public int getArmor() {
		return armor;
	}
	
	public int getX() {
		return coords.getExactX();
	}

	public int getY() {
		return coords.getExactY();
	}

}
