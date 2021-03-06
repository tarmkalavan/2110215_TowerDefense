package tower;

import java.util.ArrayList;

import base.*;
import logic.GameLogic;
import logic.Sprites;

public class AcidTower extends Tower implements Castable {
	private final String BUFF_STAT;
	private double buffRatio;
	private final double UPGRADE_BONUS;

	public AcidTower(int x, int y) {
		super(10, 1500, 200, 120, 100, 300);
		setCoord(x, y);
		BUFF_STAT = "armor";
		buffRatio = 0.8;
		UPGRADE_BONUS = 1.5;
		towerAttack = new Thread(() -> {
			while (true) {
				try {
					boolean isShot = shoot();
					if (isShot) {
						Thread.sleep(getAttackCooldown());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					break;
				}
			}
		});
		towerAttack.start();
	}

	@Override
	public int getSymbol() {
		// TODO Auto-generated method stub
		return Sprites.ACID_TOWER;
	}

	@Override
	public void upgradeTower() {
		// TODO Auto-generated method stub
		double newBuffRatio = buffRatio * UPGRADE_BONUS;
		newBuffRatio = Math.round(newBuffRatio * 100.0) / 100.0; // round to 2dp.
		setBuffRatio(newBuffRatio);
	}

	@Override
	public ArrayList<Effectable> findTarget() {
		ArrayList<Effectable> targetList = new ArrayList<>();
		ArrayList<Monster> monsterInRange = GameLogic.monstersInRange(this);
		if (!monsterInRange.isEmpty()) {
			for (Monster monster : monsterInRange) {
				targetList.add(monster);
			}
		}
		return targetList;
	}

	// SETTER//
	public void setBuffRatio(double buffRatio) {
		this.buffRatio = Math.max(buffRatio, 0.0);
	}

	// GETTER//
	public String getBUFF_STAT() {
		return BUFF_STAT;
	}

	public double getUPGRADE_BONUS() {
		return UPGRADE_BONUS;
	}

	public double getBuffRatio() {
		return buffRatio;
	}

}
