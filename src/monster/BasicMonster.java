package monster;

import base.*;
import logic.GameLogic;

public class BasicMonster extends Monster{

	public BasicMonster(int health, int armor, int speed, int reward) {
		super(health,armor,speed,reward);
	}
	
	public BasicMonster(int x, int y, BasicMonster prototype) {
		super(prototype.maxHealth, prototype.armor, prototype.speed, prototype.reward);
		setCoords(x, y);
	}
	
	public int takeDamage(int incomingDamage) {
		int damageTaken = incomingDamage - armor;
		setCurrentHealth(getCurrentHealth() - damageTaken);
		if(getCurrentHealth() == 0) { //monster was slained
			setDead(true);
			GameLogic.dropCoin(this);
			GameLogic.removeMonster(this);
		}
		return damageTaken;
	}
}
