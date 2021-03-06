package base;

import java.util.ArrayList;
import java.util.Arrays;

import background.Coordinate;
import javafx.scene.image.ImageView;

public abstract class Monster implements Effectable {

	protected int currentHealth;
	protected int maxHealth;
	protected int speed;
	protected int reward;
	protected int armor;
	protected boolean isDead;
	protected boolean pathFinished;
	protected boolean moveX;
	protected int nodeDirection;
	protected static ArrayList<Coordinate> path;
	protected ImageView view;

	public Monster(int maxHealth, int armor, int speed, int reward) {
		setCurrentHealth(maxHealth);
		setMaxHealth(maxHealth);
		setReward(reward);
		setSpeed(speed);
		setArmor(armor);
		setDead(false);
		pathFinished = false;
		moveX = true;
		nodeDirection = 1;
	}

	public abstract int takeDamage(int incomingDamage);

	public void updateLocation(int distance) {
		if (moveX) { // Move along the x axis
			view.setX(view.getX() + distance);
			// Reached a changing point in path , switch direction
			if (view.getX() == path.get(nodeDirection).getExactX() - 32) {
				moveX = false;
				nodeDirection++;
				// Traversed all changing points, path ended
				if (nodeDirection == path.size()) {
					pathFinished = true;
					isDead = true;
				}
			}
		}
		else { // Move along the y axis
			if (view.getY() < path.get(nodeDirection).getExactY() - 32) {
				view.setY(view.getY() + distance);
			} else {
				view.setY(view.getY() - distance);
			}
			// Reach changing point , switch direction
			if (view.getY() == path.get(nodeDirection).getExactY() - 32) {
				moveX = true;
				nodeDirection++;
				if (nodeDirection == path.size()) {
					pathFinished = true;
					isDead = true;
				}
			}
		}
	}

	// Effectable//
	public int effect(Castable caster) {
		int originalStat = 0;
		if (caster instanceof Tower) { 
			String statAffected = ((Tower) caster).getBUFF_STAT();
			switch (statAffected) {
			case "armor":
				originalStat = this.getArmor();
				this.setArmor((int) (this.getArmor() * ((Tower) caster).getBuffRatio()));
				break;
			case "speed":
				originalStat = this.getSpeed();
				this.setSpeed((int) (this.getSpeed() * ((Tower) caster).getBuffRatio()));
				break;
			}
		}
		return originalStat;
	}

	public void revertChange(Castable caster, int originalStat) {
		if (caster instanceof Tower) { 
			String statAffected = ((Tower) caster).getBUFF_STAT();
			switch (statAffected) {
			case "armor":
				this.setArmor(originalStat);
				break;
			case "speed":
				// do nothing
				break;
			}
		}
	}

	// SETTER//
	public void setCurrentHealth(int health) {
		this.currentHealth = Math.max(health, 0);
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = Math.max(maxHealth, 0);
	}

	public void setSpeed(int speed) {
		ArrayList<Integer> acceptedSpeed = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 8, 16, 32));
		if (acceptedSpeed.contains(speed)) {
			this.speed = Math.max(speed, 1);
		} else {
			this.speed = 1;
		}

	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void setReward(int reward) {
		this.reward = Math.max(0, reward);
	}

	public void setArmor(int armor) {
		this.armor = Math.max(0, armor);
	}

	public void setPathFinished(boolean pathFinished) {
		this.pathFinished = pathFinished;
	}

	public void setMoveX(boolean moveX) {
		this.moveX = moveX;
	}

	public void setNodeDirection(int nodeDirection) {
		this.nodeDirection = nodeDirection;
	}

	public static void setPath(ArrayList<Coordinate> path) {
		Monster.path = path;
	}

	// GETTER//
	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getSpeed() {
		return speed;
	}

	public boolean isDead() {
		return isDead;
	}

	public int getReward() {
		return reward;
	}

	public int getArmor() {
		return armor;
	}

	public int getX() {
		return (int) ((view.getX()) + 32);
	}

	public int getY() {
		return (int) ((view.getY()) + 32);
	}

	public boolean isPathFinished() {
		return pathFinished;
	}

	public boolean isMoveX() {
		return moveX;
	}

	public int getNodeDirection() {
		return nodeDirection;
	}

	public static ArrayList<Coordinate> getPath() {
		return path;
	}

	public ImageView getView() {
		return view;
	}

	public void setView(ImageView view) {
		this.view = view;
	}

}
