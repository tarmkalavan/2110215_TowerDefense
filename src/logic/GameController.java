package logic;

import application.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tower.AcidTower;

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

	private GameLogic gameLogic;
	
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
	
    public void setGameManager(GameLogic gameLogic){
        this.gameLogic = gameLogic;
    }
	
	class buyTower implements EventHandler<MouseEvent> {
        public void handle(MouseEvent me) {
        	int xTile = (int)(me.getX() / 64);
            int yTile = (int)(me.getY() / 64);
            gameLogic.buyTower(xTile,yTile,new AcidTower(xTile,yTile));
            }
    }
	
	public void buyTower(){
        gameLogic.getGameScene().setOnMouseClicked(new buyTower());
    }

}
