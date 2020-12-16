package tower;

import java.util.ArrayList;

import base.*;

public class ArcaneTower extends Tower implements Castable{
	private final String buffStat;
	private double buffRatio;
	
	public ArcaneTower() {
		super(0,3,10,150,50,340,2);
		buffStat = "attackSpeed";
		buffRatio = 1.2;
	}

	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setBuffRatio(buffRatio + 0.15);
		setRange((int) (range * upgradeBonus));
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
	
	public double getBuffRatio() {
		return buffRatio;
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}


}
