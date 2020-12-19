package logic;

import application.Main;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tower.AcidTower;
import tower.ArcaneTower;
import tower.ArcticTower;
import tower.BasicTower;
import tower.BombardTower;
import tower.SniperTower;

public class GameController {

	@FXML
	private Button acid;
	@FXML
	private Button arcane;
	@FXML
	private Button arctic;
	@FXML
	private Button bombard;
	@FXML
	private Button sniper;
	@FXML
	private Button basic;
	@FXML
	private Button mute;
	@FXML
	private Button exit;
	@FXML
	private Label currentResources;
	@FXML
	private Label currentLevel;
	@FXML
	private Label currentLives;
	@FXML
	private Label timeLabel;
	
	public void muteSound() {
		if (Main.sound.isPlaying()) {
			Main.sound.stop();
		} else {
			Main.sound.play();
		}
	}

	public void exitTheGame() {
		System.exit(1);
	}

	public void updateLabels(String currentLevel1, String currentLives1, String currentResources1, String timeLabel1) {
		this.currentLevel.setText(currentLevel1);
		this.currentLives.setText(currentLives1);
		this.currentResources.setText(currentResources1);
		this.timeLabel.setText(timeLabel1);
	}
	
    public void buyBasicTower(){
    	GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent t) {
    		 
    		double x = t.getX();
    		double y = t.getY();
    		 
    		GameLogic.buyTower(x, y);
    		}
    	});
    }
/*	
    public void buyAcidTower(){
        gameLogic.getGameScene().setOnMouseClicked(new acidTower());
    }
	class acidTower implements EventHandler<MouseEvent> {
        public void handle(MouseEvent me) {
        	int xTile = (int)(me.getX() / 64);
            int yTile = (int)(me.getY() / 64);
            gameLogic.buyTower(xTile,yTile,new AcidTower(xTile,yTile));
            }
    }
	
    public void buyArcaneTower(){
        gameLogic.getGameScene().setOnMouseClicked(new basicTower());
    }
	class arcaneTower implements EventHandler<MouseEvent> {
        public void handle(MouseEvent me) {
        	int xTile = (int)(me.getX() / 64);
            int yTile = (int)(me.getY() / 64);
            gameLogic.buyTower(xTile,yTile,new ArcaneTower(xTile,yTile));
            }
    }
	
    public void buyArcticTower(){
        gameLogic.getGameScene().setOnMouseClicked(new basicTower());
    }
	class arcticTower implements EventHandler<MouseEvent> {
        public void handle(MouseEvent me) {
        	int xTile = (int)(me.getX() / 64);
            int yTile = (int)(me.getY() / 64);
            gameLogic.buyTower(xTile,yTile,new ArcticTower(xTile,yTile));
            }
    }
	
    public void buyBombardTower(){
        gameLogic.getGameScene().setOnMouseClicked(new basicTower());
    }
	class bombardTower implements EventHandler<MouseEvent> {
        public void handle(MouseEvent me) {
        	int xTile = (int)(me.getX() / 64);
            int yTile = (int)(me.getY() / 64);
            gameLogic.buyTower(xTile,yTile,new BombardTower(xTile,yTile));
            }
    }
	
    public void buySniperTower(){
        gameLogic.getGameScene().setOnMouseClicked(new basicTower());
    }
	class sniperTower implements EventHandler<MouseEvent> {
        public void handle(MouseEvent me) {
        	int xTile = (int)(me.getX() / 64);
            int yTile = (int)(me.getY() / 64);
            gameLogic.buyTower(xTile,yTile,new SniperTower(xTile,yTile));
            }
    }
	
	
*/
/*	public void updateLabels(int timer){
        GameController.updateLabels(
            Integer.toString(GameLogic.getLevel()) ,
            Integer.toString(GameLogic.getLives()) ,
            Integer.toString(GameLogic.getMoney()) ,
            Integer.toString(timer)
        	);
	}
*/

}
