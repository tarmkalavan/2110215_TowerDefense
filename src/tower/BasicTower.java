package tower;

import base.*;

public class BasicTower extends Tower {
	private final double UPGRADE_BONUS;

	public BasicTower(int x, int y) {
		super(20, 10, 5, 50, 30, 100);
		setCoord(x, y);
		// damage, attack speed, range, buyCost, sellCost, 
		//upgradeCost, upgradeBonus
		UPGRADE_BONUS = 1.5;
	}

	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setDamage((int) (damage * UPGRADE_BONUS));
		setRange((int) (range * UPGRADE_BONUS));
	}

}
