package tower;

import base.*;

public class ArcticTower extends Tower implements Castable{
	private final String buffStat;
	private double buffRatio;
	
	public ArcticTower() {
		super(30, 7, 5, 70, 40, 150, 1.5);
		buffStat = "speed";
		buffRatio = 0.5;
	}

	
	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setBuffRatio(buffRatio + 0.1);
		setRange((int) (range * upgradeBonus));
	}
	
	//SETTER//

	public void setBuffRatio(double buffRatio) {
		this.buffRatio = buffRatio;
	}


	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}
}
