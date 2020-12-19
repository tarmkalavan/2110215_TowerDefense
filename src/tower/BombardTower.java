package tower;

import java.util.ArrayList;

import base.*;
import logic.GameLogic;

public class BombardTower extends Tower{
	private int splashDamage;
	private int splashRadius;
	private final double UPGRADE_BONUS;
	
	public BombardTower(int x, int y) {
		super(40,3,10,120,50,250);
		setCoord(x, y);
		setSplashDamage((int) (damage/3));
		setSplashRadius(1);
		UPGRADE_BONUS = 1.5;
	}
	
	public ArrayList<Monster> monsterInBlast(Monster aimmedMonster){
		ArrayList<Monster> monsterInBlast = new ArrayList<>();
		int blastMinRangeX = aimmedMonster.getX() - splashRadius;
		int blastMinRangeY = aimmedMonster.getY() - splashRadius;
		int blastMaxRangeX = aimmedMonster.getX() + splashRadius;
		int blastMaxRangeY = aimmedMonster.getY() + splashRadius;
		for(Monster targetMonster : GameLogic.getMonsterList()) {
			if(targetMonster.getX() > blastMinRangeX && targetMonster.getX() < blastMaxRangeX
					&& targetMonster.getY() > blastMinRangeY && targetMonster.getY() < blastMaxRangeY){
						monsterInBlast.add(targetMonster);
					}
			}
		return monsterInBlast;
	}
	
	public void explode(Monster aimmedMonster) {
		//deal damage to monsters in splashRadius
		//for monster within range, takedamage(splash damage)
		if(monsterInBlast(aimmedMonster).size() == 0) return;
		for(Monster affectedMonster : monsterInBlast(aimmedMonster)) {
			affectedMonster.takeDamage(splashDamage);
		}
	}

	public void upgradeTower() {
		setDamage((int) (damage * UPGRADE_BONUS));
		setSplashDamage((int) damage / 3);
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
