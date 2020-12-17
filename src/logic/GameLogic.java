package logic;

import java.util.ArrayList;

import base.*;

public class GameLogic {
	private static int money = 200; //200 = starting money
	private static int lives = 20; //20 = starting lives
	private static boolean isGameOver = false;
	private static final int TOWER_LEVEL_CAP = 3; 
	private static ArrayList<Monster> monsterList = new ArrayList<>();
	private static ArrayList<Tower> towerList = new ArrayList<>();
	
	//method 1
	//if monster reach the end, decrease live -->(note) no need for penalty, check if boss/basic monster
	
	
	public static ArrayList<Tower> towersInRange(Tower attackingTower){
		ArrayList<Tower> targetList = new ArrayList<>();
		int towerMinRangeX = attackingTower.getX() - attackingTower.getRange();
		int towerMinRangeY = attackingTower.getY() - attackingTower.getRange();
		int towerMaxRangeX = attackingTower.getX() + attackingTower.getRange();
		int towerMaxRangeY = attackingTower.getY() + attackingTower.getRange();
		for(Tower targetMonster : towerList) {
			if(targetMonster.getX() > towerMinRangeX && targetMonster.getX() < towerMaxRangeX
				&& targetMonster.getY() > towerMinRangeY && targetMonster.getY() < towerMaxRangeY){
					targetList.add(targetMonster);
				}
		}
		return targetList;
	}
	
	public static ArrayList<Monster> monstersInRange(Tower attackingTower){
		ArrayList<Monster> targetList = new ArrayList<>();
		int towerMinRangeX = attackingTower.getX() - attackingTower.getRange();
		int towerMinRangeY = attackingTower.getY() - attackingTower.getRange();
		int towerMaxRangeX = attackingTower.getX() + attackingTower.getRange();
		int towerMaxRangeY = attackingTower.getY() + attackingTower.getRange();
		for(Monster targetMonster : monsterList) {
			if(targetMonster.getX() > towerMinRangeX && targetMonster.getX() < towerMaxRangeX
					&& targetMonster.getY() > towerMinRangeY && targetMonster.getY() < towerMaxRangeY){
						targetList.add(targetMonster);
					}
			}
		return targetList;
	}
	
	//method 2
	//check game state; playing, win, lose 
	public boolean hasWon() {
		return (lives > 0) && (isGameOver);
	}
	
	public void buyTower(Tower tower) {
		if(tower.getBuyCost() > money) return; //not enough money
		money -= tower.getBuyCost();
		addTower(tower);
	}
	
	public void sellTower(Tower tower) {
		money += tower.getSellCost();
		removeTower(tower);
	}
	
	public void upgradeTower(Tower tower) {
		if(tower.getUpgradeCost() > money) return; //not enough money
		money -= tower.getUpgradeCost();
		tower.upgradeTower();
		if(tower.getLevel() < TOWER_LEVEL_CAP ) tower.setLevel(tower.getLevel() + 1);
	}
	
	public static void dropCoin(Monster monster) {
		money += monster.getReward();
	}

	public static boolean isGameOver() {
		return isGameOver;
	}
		
	public static void addMonster(Monster monster) {
		monsterList.add(monster);
	}
	
	public static void addTower(Tower tower) {
		towerList.add(tower);
	}
	
	public static void removeMonster(Monster monster) {
		monsterList.remove(money);
	}
	
	public static void removeTower(Tower tower) {
		towerList.remove(tower);
	}
	
	//GETTER
	public static int getMoney() {
		return money;
	}

	public static int getLives() {
		return lives;
	}

	public static int getTowerLevelCap() {
		return TOWER_LEVEL_CAP;
	}
	
	public static ArrayList<Monster> getMonsterList(){
		return monsterList;
	}
} 
