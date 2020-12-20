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
		super(0,3,300,150,50,340);
		setCoord(x, y);
		BUFF_STAT = "damage";
		buffRatio = 1.2;
		RANGE_BONUS = 2;
		RATIO_BONUS = 0.15;
		towerAttack = new Thread(() -> {
			while(true) {
				try {
					shoot();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		for(Tower tower : towerInRange) {
			targetList.add(tower);
		}
		return targetList;
	}
	
	@Override
	public void shoot() {
		for(Effectable towerTarget : findTarget()) {
			Thread effectThread = new Thread(() -> {
				try {
					towerTarget.effect((Castable) this);
					Thread.sleep(3000);
					towerTarget.revertChange((Castable) this);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			});
			effectThread.start();
			//Tower target = (Tower) towerTarget;
			//target.effect((Castable) this);
			//target.revertChange((Castable) this);			
		}
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
