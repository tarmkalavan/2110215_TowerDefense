package tower;

import base.*;
import logic.Sprites;

public class BasicTower extends Tower {
	private final double UPGRADE_BONUS;

	public BasicTower(int xTile, int yTile) {
		super(20, 2, 200, 50, 30, 100);
		setCoord(xTile, yTile);
		//System.out.println("x:" + this.getX() + " y:" + this.getY());
		// damage, attack speed, range, buyCost, sellCost, 
		//upgradeCost, upgradeBonus
		UPGRADE_BONUS = 1.5;
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
		return Sprites.BASIC_TOWER;
	}

	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		setDamage((int) (damage * UPGRADE_BONUS));
		setRange((int) (range * UPGRADE_BONUS));
	}

}
