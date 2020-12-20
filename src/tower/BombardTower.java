package tower;

import java.util.ArrayList;

import base.*;
import logic.GameLogic;
import logic.Sprites;

public class BombardTower extends Tower{
	private int splashDamage;
	private int splashRadius;
	private final double UPGRADE_BONUS;
	
	public BombardTower(int x, int y) {
		super(60,1500,400,120,50,250);
		setCoord(x, y);
		setSplashDamage((int) (splashDamage / 3));
		setSplashRadius(150);
		UPGRADE_BONUS = 1.5;
		towerAttack = new Thread(() -> {
			while(true) {
				try {
					boolean isShot = shoot();
					if(isShot) {
						Thread.sleep(getAttackCooldown());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					break;
				}
			}
		});
		towerAttack.start();
	}
	
	public ArrayList<Monster> monsterInBlast(Monster aimmedMonster){
		ArrayList<Monster> monsterInBlast = new ArrayList<>();
		int blastMinRangeX = aimmedMonster.getX() - splashRadius;
		int blastMinRangeY = aimmedMonster.getY() - splashRadius;
		int blastMaxRangeX = aimmedMonster.getX() + splashRadius;
		int blastMaxRangeY = aimmedMonster.getY() + splashRadius;
		if(!GameLogic.getMonsterList().isEmpty()) {
			for(Monster targetMonster : GameLogic.getMonsterList()) {
				if(targetMonster.getX() > blastMinRangeX && targetMonster.getX() < blastMaxRangeX
						&& targetMonster.getY() > blastMinRangeY && targetMonster.getY() < blastMaxRangeY){
					if(targetMonster != aimmedMonster) {		
						monsterInBlast.add(targetMonster);
					}
				}
			}
		}
		return monsterInBlast;
	}
	
	public void explode(Monster aimmedMonster) {
		//deal damage to monsters in splashRadius
		//for monster within range, takedamage(splash damage)
		if(monsterInBlast(aimmedMonster).size() == 0) return;
		System.out.println(monsterInBlast(aimmedMonster).size());
		for(Monster affectedMonster : monsterInBlast(aimmedMonster)) {
			System.out.println("hp1 " + affectedMonster.getCurrentHealth());
			affectedMonster.takeDamage(splashDamage);
			System.out.println("hp2 " + affectedMonster.getCurrentHealth());
		}
	}
	
	@Override
	public int getSymbol() {
		// TODO Auto-generated method stub
		return Sprites.BOMBARD_TOWER;
	}
	
	@Override
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
