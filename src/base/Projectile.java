package base;

import javafx.scene.shape.Circle;

public class Projectile {
	private Effectable target; //can be both tower & monster
	private final int startX;
	private final int startY;
	
	public Projectile(Effectable target, int towerX, int towerY) {
		this.target = target;
		this.startX = towerX;
		this.startY = towerY;
	}
}
