package tower;

import java.util.ArrayList;

import base.*;

public class AcidTower extends Tower implements Castable{
	private final String buffStat;
	private double buffRatio;
	
	public AcidTower() {
		super(10,3,8,120,100,300,0.9);
		buffStat = "armor";
		buffRatio = 0.8;
	}

	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		double newBuffRatio = buffRatio * upgradeBonus;
		newBuffRatio = Math.round(newBuffRatio * 100.0) / 100.0; //round to 2dp.
		setBuffRatio(newBuffRatio);
	}
	
	@Override
	public ArrayList<Effectable> findTarget(){
		//for all monster in range
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

	public String getBuffStat() {
		return buffStat;
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}
}
