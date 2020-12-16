package base;

public abstract class Monster implements Effectable{

	protected int currentHealth;
	protected int maxHealth;
	protected int speed;
	protected int reward;
	protected int armor;
	protected boolean isDead;

	public Monster(int maxHealth, int armor, int speed, int reward) {
		setCurrentHealth(maxHealth);
		setMaxHealth(maxHealth);
		setReward(reward);
		setSpeed(speed);
		setArmor(armor);
		setDead(false);
	}
	public abstract int takeDamage(int incomingDamage);
	
	//Effectable//
	public int effect(Castable e) {
		int finalStat = 0;
		if(e instanceof Tower) { //monster debuffed by tower
			String statAffected = ((Tower) e).getBuffStat();
			switch(statAffected) {
			case "armor":
				finalStat = (int) (this.getArmor() * ((Tower) e).getBuffRatio());
				this.setArmor(finalStat);
				break;
			case "speed":
				finalStat = (int) (this.getSpeed() * ((Tower) e).getBuffRatio());
				this.setSpeed(finalStat);
				break;
			}
		}
		return finalStat;
	}
	public int revertChange(Castable e) {
		int finalStat = 0;
		boolean ratioIsInt = false;
		if(e instanceof Tower) { //revert (monster debuffed by tower)
			String statAffected = ((Tower) e).getBuffStat();
			ratioIsInt = (((Tower) e).getBuffRatio() == (int) ((Tower) e).getBuffRatio());
			switch(statAffected) {
			case "armor":
				finalStat = (int) (this.getArmor() / ((Tower) e).getBuffRatio());
				if(!ratioIsInt) finalStat++;
				this.setArmor(finalStat);
				break;
			case "speed":
				finalStat = (int) (this.getSpeed() / ((Tower) e).getBuffRatio());
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



}
