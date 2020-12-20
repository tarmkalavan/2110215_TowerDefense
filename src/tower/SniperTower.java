package tower;

import base.Tower;
import logic.Sprites;

public class SniperTower extends Tower{
	private final double UPGRADE_BONUS;
	
	public SniperTower(int xTile, int yTile) {
		super(100,3000,700,200,100,300);
		setCoord(xTile, yTile);
		UPGRADE_BONUS = 2.0;
		towerAttack = new Thread(() -> {
			while(true) {
				try {
					boolean isShot = shoot();
					if(isShot) {
						Thread.sleep(getAttackCooldown());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					break;
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
