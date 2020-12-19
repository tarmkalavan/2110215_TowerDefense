package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import application.MenuNavigator;
import background.TileMap;
import base.*;
import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import monster.BasicMonster;

public class GameLogic {
	private static int money = 200; //200 = starting money
	private static int lives = 20; //20 = starting lives
	private static int level = 0;
	private static boolean isGameOver = false;
	private static final int TOWER_LEVEL_CAP = 3; 
	private static ArrayList<Monster> monsterList = new ArrayList<>();
	private static ArrayList<Tower> towerList = new ArrayList<>();
	private static ArrayList<Projectile> projectileList = new ArrayList<>();
	private  TileMap tileMap;
    private  Group monsterLayer;
    private  Scene gameScene;
    private  AnimationTimer loop;
	
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
	
	public void createGameMap() {    	
    	try {
    		tileMap = new TileMap(1280, 800);
			Parent towerShopAndData = FXMLLoader.load(getClass().getResource("/GameMap/TowerShopAndData.fxml"));
			StackPane gamePane = new StackPane();
			gamePane.setAlignment(Pos.BOTTOM_CENTER);
            Group tilemapGroup = new Group();
            monsterLayer = new Group();
            monsterLayer.getChildren().add(tilemapGroup);
            tilemapGroup.getChildren().add(tileMap);
            gamePane.getChildren().add(monsterLayer);
            
            gamePane.getChildren().add(towerShopAndData);
            gameScene = new Scene(gamePane);
            
            MenuNavigator.stage.setScene(gameScene);
            Monster.setPath(tileMap.getPath());
            startLoop();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	private void startLoop() {
		final LongProperty secondUpdate = new SimpleLongProperty(0);
        final LongProperty fpstimer = new SimpleLongProperty(0);
        final AnimationTimer timer = new AnimationTimer() {
            int timer = 10;

            @Override
            public void handle(long timestamp) {

                // Times each second
                if (timestamp/ 1000000000 != secondUpdate.get()) {
                    timer--;
                    if(timer > 19) {
                        spawnMonster(3);
                    }
                    else if(timer <= 0){
                        setLevel(level + 1);
                        timer = 30;
                    }
                }
                createProjectile();
                if(timestamp / 10000000 != fpstimer.get()){
                    updateLocations();
                }
                fpstimer.set(timestamp / 10000000);
                secondUpdate.set(timestamp / 1000000000);
				//updateLabels(timer);
            }
        };
        loop = timer;
        timer.start();
	}
	
	private void updateLocations(){
        if(!getMonsterList().isEmpty()){
            Iterator<Monster> monsters = getMonsterList().iterator();
            Monster monster;
            while(monsters.hasNext()) {
                monster = monsters.next();
                monster.updateLocation(1);
                if(monster.isPathFinished()){
                    removeMonster(monster);
                }
            }
        }
    }
	
	public void spawnMonster(int health) {
		getMonsterList().add(new BasicMonster(health,1,1,1));
        monsterLayer.getChildren().add(getMonsterList().get(getMonsterList().size() - 1).getView());
    }
	
    public synchronized static void removeMonster(Monster monster){
        // Punish player
        if (monster.isPathFinished()){
            setLives((getLives()) - 1);
        }
        // Reward player
        else{
            setMoney((getMoney()) + monster.getReward());
        }

        // Remove monsters graphic and reference
        monster.getView().setVisible(false);
        getMonsterList().remove(monster);

    }

	
	
	
	public static void addProjectile(Effectable target, Tower shootingTower) {
		projectileList.add(new Projectile(target, shootingTower.getX(), shootingTower.getY()));
	}
	
	public static void createProjectile() {
		for(Projectile projectile : projectileList) {
			//animation thingy
		}
		projectileList.clear();
	}
	
	//method 2
	//check game state; playing, win, lose 
	public boolean hasWon() {
		return (lives > 0) && (isGameOver);
	}
	
	
	
	public static void buyTower(Tower tower) {
		if(tower.getBuyCost() > money) return; //not enough money
		money -= tower.getBuyCost();
		addTower(tower);
	}
	
	public static void sellTower(Tower tower) {
		money += tower.getSellCost();
		removeTower(tower);
	}
	
	public static void upgradeTower(Tower tower) {
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

	public static int getLevel() {
		return level;
	}

	public static ArrayList<Tower> getTowerList() {
		return towerList;
	}

	public static ArrayList<Projectile> getProjectileList() {
		return projectileList;
	}

	public TileMap getTileMap() {
		return tileMap;
	}

	public Group getMonsterLayer() {
		return monsterLayer;
	}

	public Scene getGameScene() {
		return gameScene;
	}

	public AnimationTimer getLoop() {
		return loop;
	}

	public static void setMoney(int money) {
		GameLogic.money = money;
	}

	public static void setLives(int lives) {
		GameLogic.lives = lives;
	}

	public static void setLevel(int level) {
		GameLogic.level = level;
	}

	public static void setGameOver(boolean isGameOver) {
		GameLogic.isGameOver = isGameOver;
	}

	public static void setMonsterList(ArrayList<Monster> monsterList) {
		GameLogic.monsterList = monsterList;
	}

	public static void setTowerList(ArrayList<Tower> towerList) {
		GameLogic.towerList = towerList;
	}

	public static void setProjectileList(ArrayList<Projectile> projectileList) {
		GameLogic.projectileList = projectileList;
	}

	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}

	public void setMonsterLayer(Group monsterLayer) {
		this.monsterLayer = monsterLayer;
	}

	public void setGameScene(Scene gameScene) {
		this.gameScene = gameScene;
	}

	public void setLoop(AnimationTimer loop) {
		this.loop = loop;
	}
	
	
} 
