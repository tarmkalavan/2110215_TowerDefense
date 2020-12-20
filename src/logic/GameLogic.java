package logic;

import java.io.IOException;
import java.util.ArrayList;

import application.MenuNavigator;
import background.TileMap;
import base.*;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import monster.BasicMonster;
import monster.BossMonster;;

public class GameLogic {
	private static int money = 200; //200 = starting money
	private static int lives = 20; //20 = starting lives
	private static int level = 0;
	private static int time = 50;
	private static boolean isGameOver = false;
	private static final int TOWER_LEVEL_CAP = 3; 
	private static ArrayList<Monster> monsterList = new ArrayList<>();
	private static ArrayList<Tower> towerList = new ArrayList<>();
	private static ArrayList<Projectile> projectileList = new ArrayList<>();
	private static  TileMap tileMap;
    private static  Group monsterLayer;
    private  static Scene gameScene;
    private  AnimationTimer loop;
    private  GameController gameController;
    
	
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
    		setMoney(500);
    		setLives(20);
    		setLevel(1);
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
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/GameMap/TowerShopAndData.fxml")));
            gameController = loader.<GameController>getController();
            
            MenuNavigator.stage.setScene(gameScene);
            Monster.setPath(tileMap.getPath());
            startLoop();
            System.out.println("aaa");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public int getMonsterAmount() {
		switch(level) {
		case 1: return 10;
		case 2: return 10;
		case 3: return 5;
		case 4: return 20;
		case 5: return 1;
		case 6: return 5;
		case 7: return 15;
		default: return 0;
		}
	}
	
	public Monster getMonsterPrototype() {
		switch(level) {
		case 1: return new BasicMonster(50,10,1,15); //(hp, armor, speed, reward)
		case 2: return new BasicMonster(30,10,4,25);
		case 3: return new BasicMonster(80,15,2,50);
		case 4: return new BasicMonster(20,0,8,20);
		case 5: return new BossMonster(100,10,1,400,20);
		case 6: return new BasicMonster(50,20,4,100);
		case 7: return new BasicMonster(20,10,8,15);
		default: return new BasicMonster(50,10,2,15);
		}
	}
	
	public void spawnMonster() {
		Monster prototypeMonster = getMonsterPrototype();
		getMonsterList().add(prototypeMonster);
        monsterLayer.getChildren().add(getMonsterList().get(getMonsterList().size() - 1).getView());
    }
	
	private void startLoop() {
		final LongProperty secondUpdate = new SimpleLongProperty(0);
        final LongProperty fpstimer = new SimpleLongProperty(0);
        int IDLE_TIME = 1;
        int ROUND_TIME = 10;
        
        final AnimationTimer timer = new AnimationTimer() {
            int timer = IDLE_TIME;
            
            @Override
            public void handle(long timestamp) {
            	int monsterCount = getMonsterAmount();
                // Times each second
                if (timestamp/ 1000000000 != secondUpdate.get()) {
                    timer--;
                    if(timer >= (ROUND_TIME - monsterCount)) {
                        spawnMonster();
                        //System.out.println("HP = " + getMonsterPrototype().getMaxHealth() + ", Speed = " + getMonsterPrototype().getSpeed());
                    }
                    else if(timer <= 0){
                        setLevel(level + 1);
                        timer = ROUND_TIME;
                        //System.out.println(level);
                    }
                }
                createProjectile();
                if(timestamp / 10000000 != fpstimer.get()){
                    updateLocations();
                }
                fpstimer.set(timestamp / 10000000);
                secondUpdate.set(timestamp / 1000000000);
                if(level == 8) {
                	System.out.println("game end");
                	if(monsterList.isEmpty()) {
                		this.stop();
                		System.out.println("aaaa");
                	}
                	
                }
                setTime(timer);
				//gameController.initialize();
            }
        };
        loop = timer;
        timer.start();
	}
	
	public static void addProjectile(Effectable target, Tower shootingTower) {
		projectileList.add(new Projectile(target, shootingTower));
	}
	
	public static void createProjectile() {
		Path projectilePath;
		PathTransition animation;
		//System.out.println("here");
		//System.out.println("a"+projectileList.size());
		for(Projectile projectile : projectileList) {
		//for(Tower tower : towerList) {
			//for(Projectile projectile : tower.getShotProjectile()) {
			int startX = projectile.getStartX();
			int startY = projectile.getStartY();
			int endX = projectile.getTargetX();
			int endY = projectile.getTargetY();
			System.out.println("start x " + startX+ ", start y " + startY);
			System.out.println("target x " + endX+ ", target y " + endY);
			projectilePath = new Path(new MoveTo(startX, startY));
			LineTo line = new LineTo(endX, endY);
			projectilePath.getElements().add(line);
			animation = new PathTransition(Duration.millis(100) , projectilePath , projectile);
			
			animation.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                	//System.out.println("proj hit");
                    PathTransition finishedAnimation = (PathTransition) actionEvent.getSource();
                    Projectile finishedProjectile = (Projectile) finishedAnimation.getNode();

                    // Hide and remove from gui
                    finishedProjectile.setVisible(false);
                    monsterLayer.getChildren().remove(finishedProjectile);

                    //apply damage and effects
                    projectile.getShootingTower().projectileHit(projectile.getTarget(),projectile.getShootingTower());
                    // Remove monster if they are dead
                    if(finishedProjectile.getTarget() instanceof Monster) {
	                    if(((Monster) finishedProjectile.getTarget()).isDead()){
	                        removeMonster((Monster) finishedProjectile.getTarget());
	                    }
                    }
                }
            });
            monsterLayer.getChildren().add(projectile);
            animation.play();
            //System.out.println("proj shot");
		}
		projectileList.clear();
	}
	
	private void updateLocations(){
		ArrayList<Monster> copyMonsterList = monsterList;
		for(int i = 0; i < copyMonsterList.size(); i++) {
			Monster currentMonster = monsterList.get(i);
			currentMonster.updateLocation(currentMonster.getSpeed());
			if(currentMonster.isPathFinished()) {
				removeMonster(currentMonster);
			}
		}
    }
	

	

	
    public synchronized static void removeMonster(Monster monster){
        // Punish player
        if (monster.isPathFinished()){
        	if(monster instanceof BasicMonster) {
        		setLives((getLives()) - 1);
        	} else if (monster instanceof BossMonster) {
        		setLives((getLives()) - 5);
        	}
            
        }
        // Reward player
        else{
            setMoney((getMoney()) + monster.getReward());
        }

        // Remove monsters graphic and reference
        monster.getView().setVisible(false);
        getMonsterList().remove(monster);

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
	
	public void buyTower(int xTile , int yTile,Tower t){

        if(tileMap.isNodeOpen(xTile,yTile)){
        	if(t.getBuyCost() > money) return;
    		money -= t.getBuyCost();
    		addTower(t);
            tileMap.setNewNode(xTile, yTile, t.getSymbol());
            
        }
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

	public static Scene getGameScene() {
		return gameScene;
	}

	public AnimationTimer getLoop() {
		return loop;
	}
	

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		GameLogic.time = time;
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
		GameLogic.tileMap = tileMap;
	}

	public void setMonsterLayer(Group monsterLayer) {
		GameLogic.monsterLayer = monsterLayer;
	}

	public static void setGameScene(Scene gameScene) {
		GameLogic.gameScene = gameScene;
	}

	public void setLoop(AnimationTimer loop) {
		this.loop = loop;
	}

	public GameController getGameController() {
		return gameController;
	}
	


	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public static void buyTower(double x, double y,Tower t) {
        int xTile = (int)(x / 64);
        int yTile = (int)(y / 64);

        if(tileMap.isNodeOpen(xTile,yTile)){
            if(getMoney() > 0) {
                addTower(t);
                setMoney(getMoney() - 0);
                tileMap.setNewNode(xTile, yTile, t.getSymbol());
            }
        }
    }
		
}
	
	
