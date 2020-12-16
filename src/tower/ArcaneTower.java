package tower;

import java.util.ArrayList;

import base.*;

public class ArcaneTower extends Tower implements Castable{
	private final String BUFF_STAT;
	private double buffRatio;
	private final double RANGE_BONUS;
	private final double RATIO_BONUS;
	
	public ArcaneTower() {
		super(0,3,10,150,50,340);
		BUFF_STAT = "attackSpeed";
		buffRatio = 1.2;
		RANGE_BONUS = 2;
		RATIO_BONUS = 0.15;
	}

	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setBuffRatio(buffRatio + RATIO_BONUS);
		setRange((int) (range * RANGE_BONUS));
	}
	
	@Override
	public ArrayList<Effectable> findTarget(){
		//for all coords of "tower" within range
		//and them all
		ArrayList<Effectable> targetList = new ArrayList<>();
		return targetList;
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
