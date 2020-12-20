package tower;

import base.Tower;
import logic.Sprites;

public class SniperTower extends Tower{
	private final double UPGRADE_BONUS;
	
	public SniperTower(int x, int y) {
		super(100,8,700,200,100,300);
		setCoord(x, y);
		UPGRADE_BONUS = 2.0;
		towerAttack = new Thread(() -> {
			while(true) {
				try {
					shoot();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		towerAttack.start();
	}
	
	@Override
	public int getSymbol() {
		// TODO Auto-generated method stub
		return Sprites.SNIPER_TOWER;
	}
	
	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setDamage((int) (damage * UPGRADE_BONUS));
		setAttackCooldown(attackCooldown - 1);
	}
}
