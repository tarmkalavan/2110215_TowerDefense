package base;

public abstract class Tower implements Effectable{

	protected int attackSpeed;
	protected int range;
	protected int upgradeCost;
	protected int sellCost;
	protected int buyCost;
	protected int level;
	protected double upgradeBonus;
	protected Ammo ammo;

	public abstract void upgradeTower();

	public Tower(int attackSpeed, int range, int upgradeCost, int sellCost, int level, double upgradeBonus,
			Ammo ammo) {
		setAttackSpeed(attackSpeed);
		setRange(range);
		setUpgradeCost(upgradeCost);
		setSellCost(sellCost);
		setLevel(level);
		setUpgradeBonus(upgradeBonus);
		setAmmo(ammo);
	}
	
	//Effectable//
	public int effect(Effectable e) {
		int finalStat = 0;
		if(e instanceof Tower) { //tower buffed by tower
			Ammo receivedAmmo = ((Tower) e).getAmmo();
			String statAffected = receivedAmmo.getBuffStat();
			switch(statAffected) {
			case "damage":
				finalStat = (int) (receivedAmmo.getDamage() * receivedAmmo.getBuffRatio());
				this.getAmmo().setDamage(finalStat);
				break;
			case "range":
				finalStat = (int) (this.getRange() * receivedAmmo.getBuffRatio());
				this.setRange(finalStat);
				break;
			case "attackSpeed":
				finalStat = (int) (this.getAttackSpeed() * receivedAmmo.getBuffRatio());
				this.setAttackSpeed(finalStat);
				break;
			}
		}
		return finalStat;
	}
	
	public int revertChange(Effectable e) {
		int finalStat = 0;
		boolean ratioIsInt = false;
		if(e instanceof Tower) { //revert (tower buffed by tower)
			Ammo receivedAmmo = ((Tower) e).getAmmo();
			String statAffected = receivedAmmo.getBuffStat();
			ratioIsInt = (receivedAmmo.getBuffRatio() == (int) receivedAmmo.getBuffRatio());
			switch(statAffected) {
			case "damage":
				finalStat = (int) (this.getAmmo().getDamage() / receivedAmmo.getBuffRatio());
				if(!ratioIsInt) finalStat++;
				this.getAmmo().setDamage(finalStat);
				break;
			case "range":
				finalStat = (int) (this.getRange() / receivedAmmo.getBuffRatio());
				if(!ratioIsInt) finalStat++;
				this.setRange(finalStat);
				break;
			case "attackSpeed":
				finalStat = (int) (this.getAttackSpeed() / receivedAmmo.getBuffRatio());
				if(!ratioIsInt) finalStat++;
				this.setAttackSpeed(finalStat);
				break;
			}
		}
		return finalStat;
	}
	
	//SETTER//
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
	
	public void setAmmo(Ammo ammo) {
		this.ammo.setDamage(ammo.getDamage());
		this.ammo.setBuffRatio(ammo.getBuffRatio());
		this.ammo.setBuffStat(ammo.getBuffStat());
		this.ammo.setSplashRadius(ammo.getSplashRadius());
	}
	
	//GETTER//
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

	public Ammo getAmmo() {
		return ammo;
	}

}
