package base;

import java.util.ArrayList;

import background.Coordinate;
import logic.GameLogic;
import monster.BasicMonster;
import tower.BombardTower;

public abstract class Tower implements Effectable {

	protected Coordinate coords;
	
	protected int damage;
	protected int attackCooldown;
	protected int range;
	protected int upgradeCost;
	protected int sellCost;
	protected int buyCost;
	protected int level;
	//protected double upgradeBonus;
	// protected Ammo ammo;



	public Tower(int damage, int attackCooldown, int range, int buyCost, int sellCost, int upgradeCost) {
		setDamage(damage);
		setAttackCooldown(attackCooldown);
		setRange(range);
		setUpgradeCost(upgradeCost);
		setBuyCost(buyCost);
		setSellCost(sellCost);
		setLevel(1);
		setCoord(0, 0);
		//setUpgradeBonus(upgradeBonus);
		// setAmmo(ammo);
	}

	public abstract void upgradeTower();
	
	public ArrayList<Effectable> findTarget(){
		//add only the first monster to targetList
		ArrayList<Effectable> targetList = new ArrayList<>();
		targetList.add(GameLogic.monstersInRange(this).get(0)); //only the first monster
		return targetList;
	}
	
	public void shoot() { 
		Monster target = (Monster) findTarget().get(0);
		//create projectile
		//deal damage (when projectile reached target)
		target.takeDamage(this.getDamage());
		if(!target.isDead()) { //if survive
			if(this instanceof Castable) { //if tower is a castable tower
				Thread effectThread = new Thread(() -> {
					try {
						target.effect((Castable) this);
						Thread.sleep(3000);
						target.revertChange((Castable) this);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				});
				effectThread.start();
				//target.effect((Castable) this);
				//after delay
				//target.revertChange((Castable) this);
			}
			if(this instanceof BombardTower) {
				((BombardTower) this).explode(target);
			}
		}
		//remove if monster is slained
		//apply buff/debuff/explode
		//return target;
	}
	
	// Effectable//
	public int effect(Castable caster) {
		int finalStat = 0;
		if (caster instanceof Tower) { // this tower buffed by tower
			String statAffected = ((Tower) caster).getBUFF_STAT();
			switch (statAffected) {
			case "damage":
				finalStat = (int) (this.getDamage() * ((Tower) caster).getBUFF_RATIO());
				this.setDamage(finalStat);
				break;
			case "range":
				finalStat = (int) (this.getRange() * ((Tower) caster).getBUFF_RATIO());
				this.setRange(finalStat);
				break;
			case "attackCooldown":
				finalStat = (int) (this.getAttackCooldown() * ((Tower) caster).getBUFF_RATIO());
				this.setAttackCooldown(finalStat);
				break;
			}
		}
		return finalStat;
	}

	public int revertChange(Castable caster) {
		int finalStat = 0;
		boolean ratioIsInt = false;
		if (caster instanceof Tower) { // revert changes from (this tower buffed by tower)
			String statAffected = ((Tower) caster).getBUFF_STAT();
			ratioIsInt = (((Tower) caster).getBUFF_RATIO() == (int) ((Tower) caster).getBUFF_RATIO());
			switch (statAffected) {
			case "damage":
				finalStat = (int) (this.getDamage() / ((Tower) caster).getBUFF_RATIO());
				this.setDamage(finalStat);
				break;
			case "range":
				finalStat = (int) (this.getRange() / ((Tower) caster).getBUFF_RATIO());
				this.setRange(finalStat);
				break;
			case "attackCooldown":
				finalStat = (int) (this.getAttackCooldown() / ((Tower) caster).getBUFF_RATIO());
				this.setAttackCooldown(finalStat);
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

	public void setAttackCooldown(int attackCooldown) {
		this.attackCooldown = Math.max(attackCooldown, 0);
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
	
	public void setCoord(double x, double y) {
		this.coords = new Coordinate(x,y);
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

	public int getAttackCooldown() {
		return attackCooldown;
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

	public String getBUFF_STAT() {
		return "";
	}
	
	public double getBUFF_RATIO() {
		return 1.0;
	}
	
	public int getX() {
		return coords.getExactX();
	}
	
	public int getY() {
		return coords.getExactY();
	}
//	public double getUpgradeBonus() {
//		return upgradeBonus;
//	}


//	public Ammo getAmmo() {
//		return ammo;
//	}

}
