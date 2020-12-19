package tower;

import base.Tower;

public class SniperTower extends Tower{
	private final double UPGRADE_BONUS;
	
	public SniperTower(int x, int y) {
		super(100,8,15,200,100,300);
		setCoord(x, y);
		UPGRADE_BONUS = 2.0;
	}

	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setDamage((int) (damage * UPGRADE_BONUS));
		setAttackCooldown(attackCooldown - 1);
	}
}
