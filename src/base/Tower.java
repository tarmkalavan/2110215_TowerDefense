package base;

import java.util.ArrayList;

import background.Coordinate;
import logic.GameLogic;
import monster.BasicMonster;
import tower.BombardTower;

public abstract class Tower implements Effectable {


	protected int damage;
	protected int attackCooldown;
	protected int range;
	protected int upgradeCost;
	protected int sellCost;
	protected int buyCost;
	protected int level;
	
	protected Coordinate coords;
	protected Thread towerAttack;
	protected ArrayList<Projectile> shotProjectile = new ArrayList<>();
	
	public Tower(int damage, int attackCooldown, int range, int buyCost, int sellCost, int upgradeCost) {
		setDamage(damage);
		setAttackCooldown(attackCooldown);
		setRange(range);
		setUpgradeCost(upgradeCost);
		setBuyCost(buyCost);
		setSellCost(sellCost);
		setLevel(1);
		setCoord(0, 0);
	}

	public abstract int getSymbol();
	
	public abstract void upgradeTower();
	
	public ArrayList<Effectable> findTarget(){
		//add only the first monster to targetList
		ArrayList<Effectable> targetList = new ArrayList<>();
		if(!GameLogic.monstersInRange(this).isEmpty())targetList.add(GameLogic.monstersInRange(this).get(0)); //only the first monster
		return targetList;
	}
	
	public boolean shoot() {
		try {
			if (findTarget().isEmpty()) {
				return false;
			}
			for(Effectable monsterTarget : findTarget()) {
				Monster target = (Monster) monsterTarget;
				GameLogic.addProjectile(target, this);			
			}
		} catch(Exception e) {
			//do nothing
			/*
			ConcurrentModificationException or NoSuchElementException may be thrown 
			when the monster goes out of range or reach the end while the projectile is travelling
			*/
			System.out.println("e");
		}
		return true;
	}
	
	public void projectileHit(Effectable target, Tower shootingTower) {
		if(target instanceof Monster) {
			((Monster) target).takeDamage(this.getDamage());
			if(!((Monster) target).isDead()) { //if survive
				if(this instanceof BombardTower) {
					((BombardTower) this).explode((Monster) target);
				}
				if(this instanceof Castable) { //if tower is a castable tower
					Thread effectThread = new Thread(() -> {
						try {
							int originalStat = target.effect((Castable) this);
							Thread.sleep(2000);
							target.revertChange((Castable) this, originalStat);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
					effectThread.start();
				}
			}
		} else if(target instanceof Tower) {
			Thread effectThread = new Thread(() -> {
				try {
					int originalStat = ((Tower) target).effect((Castable) this);
					Thread.sleep(1000);
					target.revertChange((Castable) this, originalStat);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
			effectThread.start();
		}
	}
	
	public void stopTowerAttack() {
		towerAttack.interrupt();
	}
	
	// Effectable//
	public int effect(Castable caster) {
		int originalStat = 0;
		if (caster instanceof Tower) { // this tower buffed by tower
			String statAffected = ((Tower) caster).getBUFF_STAT();
			switch (statAffected) {
			case "damage":
				originalStat = this.getDamage();
				this.setDamage((int) (this.getDamage() * ((Tower) caster).getBuffRatio()));
				break;
			}
		}
		return originalStat;
	}

	public void revertChange(Castable caster, int originalStat) {
		if (caster instanceof Tower) { // revert changes from (this tower buffed by tower)
			String statAffected = ((Tower) caster).getBUFF_STAT();
			switch (statAffected) {
			case "damage":
				this.setDamage(originalStat);
				break;
			}
		}
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
	
	public void setCoord(int x, int y) {
		this.coords = new Coordinate(x,y);
	}

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
	
	public double getBuffRatio() {
		return 1.0;
	}
	
	public int getX() {
		return coords.getExactX();
	}
	
	public int getY() {
		return coords.getExactY();
	}
	
	public int getXTile() {
		return coords.getX();
	}
	
	public int getYTile() {
		return coords.getY();
	}

}
