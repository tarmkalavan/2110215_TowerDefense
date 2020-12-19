package tower;

import base.*;
import logic.Sprites;

public class BasicTower extends Tower {
	private final double UPGRADE_BONUS;

	public BasicTower(int x, int y) {
		super(20, 2, 5, 50, 30, 100);
		setCoord(x, y);
		// damage, attack speed, range, buyCost, sellCost, 
		//upgradeCost, upgradeBonus
		UPGRADE_BONUS = 1.5;
	}

	@Override
	public int getSymbol() {
		// TODO Auto-generated method stub
		return Sprites.BASIC_TOWER;
	}

	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setDamage((int) (damage * UPGRADE_BONUS));
		setRange((int) (range * UPGRADE_BONUS));
	}

}
