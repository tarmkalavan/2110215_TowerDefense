package monster;

import base.Monster;

public class BossMonster extends Monster{
	private int barrier;
	
	public BossMonster(int maxHealth, int armor, int speed, int reward, int barrier) {
		super(maxHealth,armor,speed,reward);
		setBarrier(barrier);
	}
	
	public BossMonster(int x, int y, BossMonster prototype) {
		super(prototype.maxHealth, prototype.armor, prototype.speed, prototype.reward);
		setCoords(x, y);
	}
	
	public int takeDamage(int incomingDamage) {
		if(barrier > 0) { //barrier must be broken before damaging the boss
			barrier--;
			return 0;
		}
		int damageTaken = incomingDamage - armor;
		setCurrentHealth(getCurrentHealth() - damageTaken);
		if(getCurrentHealth() == 0) { //monster slained
			setDead(true);
			//dropCoin(); //*****
		}
		return damageTaken;
	}
	
	public int getBarrier() {
		return barrier;
	}

	public void setBarrier(int barrier) {
		this.barrier = Math.max(barrier, 0);
	}


}
