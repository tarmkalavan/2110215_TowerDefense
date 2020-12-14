package monster;

import base.Monster;

public class BossMonster extends Monster{
	
	public BossMonster(int maxHealth, int armor, int speed, int reward) {
		super(maxHealth,armor,speed,reward);
		penalty = 5;
	}
	
	public int takeDamage(int incomingDamage) {
		int damageTaken = incomingDamage - armor;
		setCurrentHealth(getCurrentHealth() - damageTaken);
		if(getCurrentHealth() == 0) { //monster slained
			setDead(true);
			//dropCoin(); //*****
		}
		return damageTaken;
	}
}
