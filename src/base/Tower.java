package base;

public abstract class Tower implements Effectable {

	protected int damage;
	protected int attackSpeed;
	protected int range;
	protected int upgradeCost;
	protected int sellCost;
	protected int buyCost;
	protected int level;
	protected double upgradeBonus;
	// protected Ammo ammo;



	public Tower(int damage, int attackSpeed, int range, int buyCost, int sellCost, int upgradeCost,
			double upgradeBonus) {
		setDamage(damage);
		setAttackSpeed(attackSpeed);
		setRange(range);
		setUpgradeCost(upgradeCost);
		setBuyCost(buyCost);
		setSellCost(sellCost);
		setLevel(1);
		setUpgradeBonus(upgradeBonus);
		// setAmmo(ammo);
	}

	public abstract void upgradeTower();
	
	// Effectable//
	public int effect(Castable e) {
		int finalStat = 0;
		if (e instanceof Tower) { // this tower buffed by tower
			String statAffected = ((Tower) e).getBuffStat();
			switch (statAffected) {
			case "damage":
				finalStat = (int) (this.getDamage() * ((Tower) e).getBuffRatio());
				this.setDamage(finalStat);
				break;
			case "range":
				finalStat = (int) (this.getRange() * ((Tower) e).getBuffRatio());
				this.setRange(finalStat);
				break;
			case "attackSpeed":
				finalStat = (int) (this.getAttackSpeed() * ((Tower) e).getBuffRatio());
				this.setAttackSpeed(finalStat);
				break;
			}
		}
		return finalStat;
	}

	public int revertChange(Castable e) {
		int finalStat = 0;
		boolean ratioIsInt = false;
		if (e instanceof Tower) { // revert (this tower buffed by tower)
			String statAffected = ((Tower) e).getBuffStat();
			ratioIsInt = (((Tower) e).getBuffRatio() == (int) ((Tower) e).getBuffRatio());
			switch (statAffected) {
			case "damage":
				finalStat = (int) (this.getDamage() / ((Tower) e).getBuffRatio());
				this.setDamage(finalStat);
				break;
			case "range":
				finalStat = (int) (this.getRange() / ((Tower) e).getBuffRatio());
				this.setRange(finalStat);
				break;
			case "attackSpeed":
				finalStat = (int) (this.getAttackSpeed() / ((Tower) e).getBuffRatio());
				this.setAttackSpeed(finalStat);
				break;
			}
			if(!ratioIsInt) {
				finalStat++;
			}
		}
		return finalStat;
	}

	// SETTER//
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = Math.max(attackSpeed, 0);
	}

	public void setRange(int range) {
		this.range = Math.max(range, 0);
	}

	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = Math.max(upgradeCost, 0);
	}

	public void setSellCost(int sellCost) {
		this.sellCost = Math.max(sellCost, 0);
	}

	public void setBuyCost(int buyCost) {
		this.buyCost = Math.max(sellCost, 0);
	}

	public void setLevel(int level) {
		this.level = Math.max(level, 0);
	}

	public void setUpgradeBonus(double upgradeBonus) {
		this.upgradeBonus = Math.max(upgradeBonus, 0.0);
	}

//	public void setAmmo(Ammo ammo) {
//		this.ammo.setDamage(ammo.getDamage());
//		this.ammo.setBuffRatio(ammo.getBuffRatio());
//		this.ammo.setBuffStat(ammo.getBuffStat());
	// this.ammo.setSplashRadius(ammo.getSplashRadius());
//	}

	// GETTER//
	public int getDamage() {
		return damage;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public int getRange() {
		return range;
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}

	public int getSellCost() {
		return sellCost;
	}

	public int getBuyCost() {
		return buyCost;
	}

	public int getLevel() {
		return level;
	}

	public double getUpgradeBonus() {
		return upgradeBonus;
	}

	public String getBuffStat() {
		return "";
	}
	
	public double getBuffRatio() {
		return 1.0;
	}
//	public Ammo getAmmo() {
//		return ammo;
//	}

}
