package base;

public abstract class Tower {
	
	protected int attackDamage;
	protected double attackSpeed;
	protected int range;
	protected int upgradeCost;
	protected int sellCost;
	protected int buyCost;
	protected int level;
	protected double upgradeBonus;
	protected Ammo ammo;
	
	public abstract void upgradeTower();

	public Tower(int attackDamage, double attackSpeed, int range,
			   int upgradeCost, int sellCost, int level, double upgradeBonus, 
			   Ammo ammo, int splashRadius) {
		setAttackSpeed(attackSpeed);
		setRange(range);
		setUpgradeCost(upgradeCost);
		setSellCost(sellCost);
		setLevel(level);
		setUpgradeBonus(upgradeBonus);
		ammo = new Ammo(attackDamage, splashRadius);
	}
	
	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = Math.max(attackDamage, 0);
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(double attackSpeed) {
		this.attackSpeed = Math.max(attackSpeed,0.0);
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = Math.max(range, 0);
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}

	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = Math.max(upgradeCost, 0);
	}

	public int getSellCost() {
		return sellCost;
	}

	public void setSellCost(int sellCost) {
		this.sellCost = Math.max(sellCost, 0);
	}

	public int getBuyCost() {
		return buyCost;
	}

	public void setBuyCost(int buyCost) {
		this.buyCost = Math.max(sellCost, 0);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = Math.max(level, 0);
	}

	public double getUpgradeBonus() {
		return upgradeBonus;
	}

	public void setUpgradeBonus(double upgradeBonus) {
		this.upgradeBonus = Math.max(upgradeBonus, 0.0);
	}

	public Ammo getAmmo() {
		return ammo;
	}

}
