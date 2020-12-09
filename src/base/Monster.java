package base;

public abstract class Monster {

	protected int health;
	protected int speed;
	protected int reward;
	protected int armor;
	protected boolean isDead;

	public Monster(int health, int armor, int speed, int reward) {
		setHealth(health);
		setReward(reward);
		setSpeed(speed);
		setArmor(armor);
		this.isDead = false;
	}

	public abstract int takeDamage(int damage);

	public int getHealth() {
		return health;
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
		if (health < 0) {
			health = 0;
		}
		this.health = health;
	}

	public void setSpeed(int speed) {
		if (speed < 0) {
			speed = 0;
		}
		this.speed = speed;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void setReward(int reward) {
		if (reward < 0) {
			reward = 0;
		}
		this.reward = reward;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		if (armor < 0) {
			armor = 0;
		}
		this.armor = armor;
	}

}
