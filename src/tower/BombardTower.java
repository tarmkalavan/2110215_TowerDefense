package tower;

import base.*;

public class BombardTower extends Tower{
	private int splashDamage;
	private int splashRadius;
	
	public BombardTower() {
		super(40,3,10,120,50,250,1.5);
		setSplashDamage((int) (damage/5));
		setSplashRadius(1);
	}
	
	public void explode() {
		//deal damage to monsters in splashRadius
	}

	public void upgradeTower() {
		setDamage((int) (damage * upgradeBonus));
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
