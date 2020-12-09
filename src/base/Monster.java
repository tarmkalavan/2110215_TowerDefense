package base;

public abstract class Monster {

	protected int currentHealth;
	protected int maxHealth;
	protected int speed;
	protected int reward;
	protected int armor;
	protected int penalty;
	protected boolean isDead;

	public Monster(int maxHealth, int armor, int speed, int reward) {
		setHealth(maxHealth);
		setMaxHealth(maxHealth);
		setDead(false);
		setReward(reward);
		setSpeed(speed);
		setArmor(armor);
	}

	public abstract int takeDamage(int incomingDamage);

	public int getHealth() {
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

	public void setHealth(int health) {
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

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = Math.max(0,armor);
	}

}
