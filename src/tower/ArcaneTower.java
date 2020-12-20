package tower;

import java.util.ArrayList;

import base.*;
import logic.GameLogic;
import logic.Sprites;

public class ArcaneTower extends Tower implements Castable{
	private final String BUFF_STAT;
	private double buffRatio;
	private final double RANGE_BONUS;
	private final double RATIO_BONUS;
	
	public ArcaneTower(int x, int y) {
		super(0,2500,300,150,50,340);
		setCoord(x, y);
		BUFF_STAT = "damage";
		buffRatio = 1.2;
		RANGE_BONUS = 2;
		RATIO_BONUS = 0.15;
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

	@Override
	public int getSymbol() {
		// TODO Auto-generated method stub
		return Sprites.ARCANE_TOWER;
	}
	
	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setBuffRatio(buffRatio + RATIO_BONUS);
		setRange((int) (range * RANGE_BONUS));
	}
	
	@Override
	public ArrayList<Effectable> findTarget(){
		ArrayList<Effectable> targetList = new ArrayList<>();
		ArrayList<Tower> towerInRange = GameLogic.towersInRange(this);
		if(!towerInRange.isEmpty()) {
			for(Tower tower : towerInRange) {
				if(!(tower instanceof ArcaneTower)) {
					targetList.add(tower);
				}
			}
		}
		return targetList;
	}
	
	@Override
	public boolean shoot() {
		if(findTarget().isEmpty()) {
			return false;
		}
		for(Effectable towerTarget : findTarget()) {
			Tower target = (Tower) towerTarget;
			GameLogic.addProjectile(target, this);			
		}
		return true;
	}

	//SETTER//
	public void setBuffRatio(double buffRatio) {
		this.buffRatio = buffRatio;
	}
	
	//GETTER//
	public double getBuffRatio() {
		return buffRatio;
	}

	public String getBUFF_STAT() {
		return BUFF_STAT;
	}
}
