package monster;

import base.*;

public class BasicMonster extends Monster{

	public BasicMonster(int health, int armor, int speed, int reward) {
		super(health,armor,speed,reward);
		penalty = 1;
	}
	
	public int takeDamage(int incomingDamage) {
		int damageTaken = incomingDamage - armor;
		setHealth(getHealth() - damageTaken);
		if(getHealth() == 0) { //monster slained
			setDead(true);
			//dropCoin(); //*****
		}
		return damageTaken;
	}
}
