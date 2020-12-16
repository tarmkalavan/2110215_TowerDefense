package tower;

import java.util.ArrayList;

import base.*;

public class AcidTower extends Tower implements Castable{
	private final String BUFF_STAT;
	private double buffRatio;
	private final double UPGRADE_BONUS;
	
	public AcidTower() {
		super(10,3,8,120,100,300);
		BUFF_STAT = "armor";
		buffRatio = 0.8;
		UPGRADE_BONUS = 2;
	}

	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		double newBuffRatio = buffRatio * UPGRADE_BONUS;
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
	public String getBUFF_STAT() {
		return BUFF_STAT;
	}

	public double getUPGRADE_BONUS() {
		return UPGRADE_BONUS;
	}

	public double getBuffRatio() {
		return buffRatio;
	}
	
}
