package tower;

import base.*;
import logic.Sprites;

public class ArcticTower extends Tower implements Castable{
	private final String BUFF_STAT;
	private double buffRatio;
	private final double UPGRADE_BONUS;
	
	public ArcticTower(int x, int y) {
		super(30, 7, 5, 70, 40, 150);
		setCoord(x, y);
		BUFF_STAT = "speed";
		buffRatio = 0.5;
		UPGRADE_BONUS = 1.5;
	}

	@Override
	public int getSymbol() {
		// TODO Auto-generated method stub
		return Sprites.ARCTIC_TOWER;
	}
	
	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setAttackCooldown((int) (getAttackCooldown() * UPGRADE_BONUS)); 
		setRange((int) (range * UPGRADE_BONUS));
	}
	
	//SETTER//
	public void setBuffRatio(double buffRatio) {
		this.buffRatio = buffRatio;
	}
	
	//GETTER//
	public String getBUFF_STAT() {
		return BUFF_STAT;
	}


	public double getBuffRatio() {
		return buffRatio;
	}


}
