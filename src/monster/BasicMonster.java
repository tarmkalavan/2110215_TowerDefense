package monster;

import base.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BasicMonster extends Monster {

	public BasicMonster(int health, int armor, int speed, int reward) {
		super(health, armor, speed, reward);
		this.view = new ImageView(new Image(ClassLoader.getSystemResource("GameMap/BasicMonster.png").toString()));
		view.setX(path.get(0).getExactX() - 32);
		view.setY(path.get(0).getExactY() - 32);
	}

	public int takeDamage(int incomingDamage) {
		int damageTaken = Math.max(incomingDamage - armor, 0);
		setCurrentHealth(getCurrentHealth() - damageTaken);
		if (getCurrentHealth() == 0) { 
			setDead(true);
		}
		return damageTaken;
	}
}
