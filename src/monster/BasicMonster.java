package monster;

import base.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.GameLogic;

public class BasicMonster extends Monster{

	public BasicMonster(int health, int armor, int speed, int reward) {
		super(health,armor,speed,reward);
		this.view = new ImageView(new Image(ClassLoader.getSystemResource("GameMap/BasicMonster.png").toString()));
		view.setX(path.get(0).getExactX()-32);
		view.setY(path.get(0).getExactY()-32);
	}
	
/*	
	public BasicMonster(int x, int y, BasicMonster prototype) {
		super(prototype.maxHealth, prototype.armor, prototype.speed, prototype.reward);
		this.view = new ImageView(new Image(ClassLoader.getSystemResource("GameMap/BasicMonster.png").toString()));
		view.setX(path.get(0).getExactX()-32);
		view.setY(path.get(0).getExactY()-32);
		setCoords(x, y);
	}
*/	
	public int takeDamage(int incomingDamage) {
		int damageTaken = Math.max(incomingDamage - armor, 0);
		setCurrentHealth(getCurrentHealth() - damageTaken);
		if(getCurrentHealth() == 0) { //monster was slained
			setDead(true);
			GameLogic.dropCoin(this);
			GameLogic.removeMonster(this);
		}
		return damageTaken;
	}
}
