package logic;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
	
	public void updateLabels(int timer){
        GameController.updateLabels(
        	Integer.toString(GameLogic.getLevel()) ,
            Integer.toString(GameLogic.getLevel()) ,
            Integer.toString(GameLogic.getLives()) ,
            Integer.toString(GameLogic.getMoney()) ,
            Integer.toString(timer)
        	);
	}

}
