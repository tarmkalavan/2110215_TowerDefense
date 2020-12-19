package base;

import java.util.ArrayList;

import background.Coordinate;
import javafx.scene.image.ImageView;

public abstract class Monster implements Effectable{

	protected int currentHealth;
	protected int maxHealth;
	protected int speed;
	protected int reward;
	protected int armor;
	protected boolean isDead;
	protected Coordinate coords;
	protected boolean pathFinished;
	protected boolean moveX;
	protected int nodeDirection;
	protected static ArrayList<Coordinate> path;
	protected ImageView view;

	public Monster(int maxHealth, int armor, int speed, int reward) {
		setCoords(5,5);
		setCurrentHealth(maxHealth);
		setMaxHealth(maxHealth);
		setReward(reward);
		setSpeed(speed);
		setArmor(armor);
		setDead(false);
		pathFinished = false;
        moveX = true;
        nodeDirection = 1;
		this.view = new ImageView("MainMenu/Shield.png");
	}
	public abstract int takeDamage(int incomingDamage);
	
	public void updateLocation(int distance){

        // Move along the x axis
        if(moveX){
            view.setX(view.getX() + distance);
            // Reached a changing point in path , switch direction
            if(view.getX() == path.get(nodeDirection).getExactX()){
                moveX = false;
                nodeDirection++;
                // Traversed all changing points, path ended
                if(nodeDirection == path.size()){
                    pathFinished = true;
                    isDead = true;
                }
            }
        }
        // Move along the y axis
        else{
            if(view.getY() < path.get(nodeDirection).getExactY()) {
                view.setY(view.getY() + distance);
            }
            else{
                view.setY(view.getY() - distance);
            }
            // Reach changing point , switch direction
            if(view.getY() == path.get(nodeDirection).getExactY()){
                moveX = true;
                nodeDirection++;
                if(nodeDirection == path.size()){
                    pathFinished = true;
                    isDead = true;
                }
            }
        }
    }
	
	//Effectable//
	public int effect(Castable caster) {
		int finalStat = 0;
		if(caster instanceof Tower) { //monster debuffed by tower
			String statAffected = ((Tower) caster).getBUFF_STAT();
			switch(statAffected) {
			case "armor":
				finalStat = (int) (this.getArmor() * ((Tower) caster).getBUFF_RATIO());
				this.setArmor(finalStat);
				break;
			case "speed":
				finalStat = (int) (this.getSpeed() * ((Tower) caster).getBUFF_RATIO());
				this.setSpeed(finalStat);
				break;
			}
		}
		return finalStat;
	}
	public int revertChange(Castable caster) {
		int finalStat = 0;
		boolean ratioIsInt = false;
		if(caster instanceof Tower) { //revert (monster debuffed by tower)
			String statAffected = ((Tower) caster).getBUFF_STAT();
			ratioIsInt = (((Tower) caster).getBUFF_RATIO() == (int) ((Tower) caster).getBUFF_RATIO());
			switch(statAffected) {
			case "armor":
				finalStat = (int) (this.getArmor() / ((Tower) caster).getBUFF_RATIO());
				if(!ratioIsInt) finalStat++;
				this.setArmor(finalStat);
				break;
			case "speed":
				finalStat = (int) (this.getSpeed() / ((Tower) caster).getBUFF_RATIO());
				if(!ratioIsInt) finalStat++;
				this.setSpeed(finalStat);
				break;
			}
		}
		return finalStat;
	}
	
	//SETTER//
	public void setCurrentHealth(int health) {
		this.currentHealth = Math.max(health, 0);
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = Math.max(maxHealth, 0);
	}

	public void setSpeed(int speed) {
		this.speed = Math.max(speed, 0);
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void setReward(int reward) {
		this.reward = Math.max(0, reward);
	}
	
	public void setArmor(int armor) {
		this.armor = Math.max(0,armor);
	}
	
	public void setCoords(int x, int y) {
		this.coords = new Coordinate(x,y);
	}
	
	public void setCoords(Coordinate coords) {
		this.coords = coords;
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
	
	//GETTER//
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
		return coords.getExactX();
	}

	public int getY() {
		return coords.getExactY();
	}
	
	public Coordinate getCoords() {
		return coords;
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
