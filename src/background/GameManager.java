package background;

import java.io.IOException;

import background.*;
import application.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class GameManager {
	
	private  TileMap tileMap;
    private  Group monsterLayer;
    private  Scene gameScene;
    private  GameController gameController;
    private  AnimationTimer waveTime;

    public void createGameMap() {    	
    	try {
    		tileMap = new TileMap(1280, 800);
			Parent towerShopAndData = FXMLLoader.load(getClass().getResource("/GameMap/TowerShopAndData.fxml"));
    		
			StackPane gamePane = new StackPane();
            Group tilemapGroup = new Group();
            monsterLayer = new Group();
            monsterLayer.getChildren().add(tilemapGroup);
            tilemapGroup.getChildren().add(tileMap);
            gamePane.getChildren().add(monsterLayer);
            
            gameScene = new Scene(gamePane);
            
            MenuNavigator.stage.setScene(gameScene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
