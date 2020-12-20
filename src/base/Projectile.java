package base;

import javafx.scene.paint.Color;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Projectile extends Circle{
	private Effectable target; //can be both tower & monster
	private Tower shootingTower;
	//private ImageView view;
	
	public Projectile(Effectable target, Tower shootingTower) {
		super(shootingTower.getX(), shootingTower.getY(), 5,Color.BLACK);
		//System.out.println("x:" + shootingTower.getX() + " y: " + shootingTower.getY());
		this.target = target;
		this.shootingTower = shootingTower;
		//view = new ImageView("MainMenu/Shield.png");
	}
	
	public Effectable getTarget() {
		return target;
	}

	public Tower getShootingTower() {
		return shootingTower;
	}
	public int getTargetX() {
		if (target instanceof Monster) {
			System.out.println("here");
			return ((Monster) target).getX();
		}
		if (target instanceof Tower) {
			return ((Tower) target).getX();
		}
		System.out.println("here2");
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
