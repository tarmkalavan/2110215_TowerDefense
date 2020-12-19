package base;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Projectile extends ImageView{
	private Effectable target; //can be both tower & monster
	private final int startX;
	private final int startY;
	private ImageView view;
	
	public Projectile(Effectable target, int towerX, int towerY) {
		this.target = target;
		this.startX = towerX;
		this.startY = towerY;
		view = new ImageView("MainMenu/Shield.png");
	}
	
	public Effectable getTarget() {
		return target;
	}

	public int getTargetX() {
		if (target instanceof Monster) {
			return ((Monster) target).getX();
		}
		if (target instanceof Tower) {
			return ((Tower) target).getX();
		}
		return 0;
	}

	public int getTargetY() {
		if (target instanceof Monster) {
			return ((Monster) target).getY();
		}
		if (target instanceof Tower) {
			return ((Tower) target).getY();
		}
		return 0;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}
}
