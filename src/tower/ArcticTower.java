package tower;

import base.*;

public class ArcticTower extends Tower implements Castable{
	private final String BUFF_STAT;
	private double buffRatio;
	private final double RANGE_BONUS;
	private final double RATIO_BONUS;
	
	public ArcticTower() {
		super(30, 7, 5, 70, 40, 150);
		BUFF_STAT = "speed";
		buffRatio = 0.5;
		RATIO_BONUS = 0.1;
		RANGE_BONUS = 1.5;
	}

	
	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setBuffRatio(buffRatio + RATIO_BONUS);
		setRange((int) (range * RANGE_BONUS));
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
