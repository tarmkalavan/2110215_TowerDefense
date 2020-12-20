package monster;

import base.Monster;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.GameLogic;

public class BossMonster extends Monster{
	private int barrier;
	
	public BossMonster(int maxHealth, int armor, int speed, int reward, int barrier) {
		super(maxHealth,armor,speed,reward);
		setBarrier(barrier);
		this.view = new ImageView(new Image(ClassLoader.getSystemResource("GameMap/BossMonster.png").toString()));
		view.setX(path.get(0).getExactX()-32);
		view.setY(path.get(0).getExactY()-32);
	}
	
	public int takeDamage(int incomingDamage) {
		if(barrier > 0) { //barrier must be broken before damaging the boss
			barrier--;
			return 0;
		}
		int damageTaken = incomingDamage - armor;
		setCurrentHealth(getCurrentHealth() - damageTaken);
		if(getCurrentHealth() == 0) { //monster slained
			setDead(true);
			GameLogic.dropCoin(this);
			GameLogic.removeMonster(this);
		}
		return damageTaken;
	}
	
	public int getBarrier() {
		return barrier;
	}

	public void setBarrier(int barrier) {
		this.barrier = Math.max(barrier, 0);
	}


}
