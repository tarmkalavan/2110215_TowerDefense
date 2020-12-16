package tower;

import base.*;

public class BombardTower extends Tower{
	private int splashDamage;
	private int splashRadius;
	private final double UPGRADE_BONUS;
	
	public BombardTower() {
		super(40,3,10,120,50,250);
		setSplashDamage((int) (damage/3));
		setSplashRadius(1);
		UPGRADE_BONUS = 1.5;
	}
	
	public void explode() {
		//deal damage to monsters in splashRadius
		//for monster within range, takedamage(splash damage)
	}

	public void upgradeTower() {
		setDamage((int) (damage * UPGRADE_BONUS));
		setSplashDamage((int) damage / 4);
	}

	//SETTER//
	public void setSplashDamage(int splashDamage) {
		this.splashDamage = Math.max(splashDamage, 1);
	}

	public void setSplashRadius(int splashRadius) {
		this.splashRadius = Math.max(splashRadius, 0);
	}
	
	//GETTER//
	public int getSplashRadius() {
		return splashRadius;
	}
	
	public int getSplashDamage() {
		return splashDamage;
	}
}
