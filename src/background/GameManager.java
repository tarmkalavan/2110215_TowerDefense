package background;

import java.io.IOException;
import java.util.Iterator;

import application.*;
import base.Tower;
import base.Monster;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import logic.GameLogic;
import monster.BasicMonster;
import tower.AcidTower;

public class GameManager {
	
	private  TileMap tileMap;
    private  Group monsterLayer;
    private  Scene gameScene;

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
            
            //Monster.setPath(gameMap.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    public void spawnMonster(int health) {
    	if(!GameLogic.getMonsterList().isEmpty()) {
    		Iterator<base.Monster> monsters = GameLogic.getMonsterList().iterator();
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
    
   /* public void towerToMap(Tower tower) {
    	if (tower instanceof AcidTower) {
    		if (tileMap.isNodeOpen(xTile, yTile)) {
    			if
    		}
    	}
    } */
}
