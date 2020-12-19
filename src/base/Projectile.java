package base;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Projectile extends ImageView{
	private Effectable target; //can be both tower & monster
	private Tower shootingTower;
	private ImageView view;
	
	public Projectile(Effectable target, Tower shootingTower) {
		this.target = target;
		this.shootingTower = shootingTower;
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
		return shootingTower.getX();
	}

	public int getStartY() {
		return shootingTower.getY();
	}
}
