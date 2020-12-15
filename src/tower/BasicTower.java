package tower;

import base.*;

public class BasicTower extends Tower {

	public BasicTower() {
		super(20, 10, 5, 50, 30, 100, 1.5);
		// damage, attack speed, range, buyCost, sellCost, 
		//upgradeCost, upgradeBonus
	}

	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setDamage((int) (damage * upgradeBonus));
		setRange((int) (range * upgradeBonus));
	}

}
