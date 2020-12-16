package tower;

import base.Tower;

public class SniperTower extends Tower{
	public SniperTower() {
		super(100,1,15,200,100,300,2);
	}

	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setDamage((int) (damage * upgradeBonus));
		setAttackSpeed(attackSpeed + 1);
	}
}
