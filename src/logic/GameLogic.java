package logic;

import base.*;

public class GameLogic {
	private static int money = 200; //200 = starting money
	private static int lives = 20; //20 = starting lives
	private static boolean isGameOver = false;
	private static final int TOWER_LEVEL_CAP = 3; 
	
	//method 1
	//if monster reach the end, decrease live -->(note) no need for penalty, check if boss/basic monster
	
	//method 2
	//check game state; playing, win, lose 
	
	public void buyTower(Tower tower) {
		if(tower.getBuyCost() > money) return; //not enough money
		money -= tower.getBuyCost();
		//add tower to arraylist or something
	}
	
	
	public void upgradeTower(Tower tower) {
		if(tower.getUpgradeCost() > money) return; //not enough money
		money -= tower.getUpgradeCost();
		tower.upgradeTower();
		if(tower.getLevel() < TOWER_LEVEL_CAP ) tower.setLevel(tower.getLevel() + 1);
	}
	
	//GETTER
	public static int getMoney() {
		return money;
	}

	public static int getLives() {
		return lives;
	}

	public static boolean isGameOver() {
		return isGameOver;
	}

	public static int getTowerLevelCap() {
		return TOWER_LEVEL_CAP;
	}

	public void sellTower(Tower tower) {
		money += tower.getSellCost();
	}
} 
