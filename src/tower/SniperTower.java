package tower;

import base.Tower;

public class SniperTower extends Tower{
	private final double UPGRADE_BONUS;
	
	public SniperTower() {
		super(100,1,15,200,100,300);
		UPGRADE_BONUS = 2.0;
	}

	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setDamage((int) (damage * UPGRADE_BONUS));
		setAttackSpeed(attackSpeed + 1);
	}
}
