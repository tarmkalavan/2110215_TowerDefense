package base;

import java.util.ArrayList;

import logic.GameLogic;
import monster.BasicMonster;
import tower.BombardTower;

public abstract class Tower implements Effectable {

	protected int damage;
	protected int attackSpeed;
	protected int range;
	protected int upgradeCost;
	protected int sellCost;
	protected int buyCost;
	protected int level;
	//protected double upgradeBonus;
	// protected Ammo ammo;



	public Tower(int damage, int attackSpeed, int range, int buyCost, int sellCost, int upgradeCost) {
		setDamage(damage);
		setAttackSpeed(attackSpeed);
		setRange(range);
		setUpgradeCost(upgradeCost);
		setBuyCost(buyCost);
		setSellCost(sellCost);
		setLevel(1);
		//setUpgradeBonus(upgradeBonus);
		// setAmmo(ammo);
	}

	public abstract void upgradeTower();
	
	public ArrayList<Effectable> findTarget(){
		//for all coords of monster, find the first monster within range
		//add the first monster to ArrayList
		ArrayList<Effectable> targetList = new ArrayList<>();
		return targetList;
	}
	
	public Effectable shoot() { //return the target
		//search for target, can be either tower or monster
		Monster target = new BasicMonster(100, 10, 10, 15); //health, armor, speed, reward
		//create projectile
		//deal damage (when projectile reached target)
		if(target instanceof Monster) {
			target.takeDamage(this.getDamage());
			if(!target.isDead()) { //if survive
				if(this instanceof Castable) { //if tower is a castable tower
					target.effect((Castable) this);
					//after delay
					target.revertChange((Castable) this);
				}
				if(this instanceof BombardTower) {
					((BombardTower) this).explode();
				}
			}
		}
		//remove if monster is slained
		//apply buff/debuff/explode
		return target;
	}
	
	// Effectable//
	public int effect(Castable e) {
		int finalStat = 0;
		if (e instanceof Tower) { // this tower buffed by tower
			String statAffected = ((Tower) e).getBUFF_STAT();
			switch (statAffected) {
			case "damage":
				finalStat = (int) (this.getDamage() * ((Tower) e).getBUFF_RATIO());
				this.setDamage(finalStat);
				break;
			case "range":
				finalStat = (int) (this.getRange() * ((Tower) e).getBUFF_RATIO());
				this.setRange(finalStat);
				break;
			case "attackSpeed":
				finalStat = (int) (this.getAttackSpeed() * ((Tower) e).getBUFF_RATIO());
				this.setAttackSpeed(finalStat);
				break;
			}
		}
		return finalStat;
	}

	public int revertChange(Castable e) {
		int finalStat = 0;
		boolean ratioIsInt = false;
		if (e instanceof Tower) { // revert changes from (this tower buffed by tower)
			String statAffected = ((Tower) e).getBUFF_STAT();
			ratioIsInt = (((Tower) e).getBUFF_RATIO() == (int) ((Tower) e).getBUFF_RATIO());
			switch (statAffected) {
			case "damage":
				finalStat = (int) (this.getDamage() / ((Tower) e).getBUFF_RATIO());
				this.setDamage(finalStat);
				break;
			case "range":
				finalStat = (int) (this.getRange() / ((Tower) e).getBUFF_RATIO());
				this.setRange(finalStat);
				break;
			case "attackSpeed":
				finalStat = (int) (this.getAttackSpeed() / ((Tower) e).getBUFF_RATIO());
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

//	public void setUpgradeBonus(double upgradeBonus) {
//		this.upgradeBonus = Math.max(upgradeBonus, 0.0);
//	}

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

//	public double getUpgradeBonus() {
//		return upgradeBonus;
//	}

	public String getBUFF_STAT() {
		return "";
	}
	
	public double getBUFF_RATIO() {
		return 1.0;
	}
//	public Ammo getAmmo() {
//		return ammo;
//	}

}
