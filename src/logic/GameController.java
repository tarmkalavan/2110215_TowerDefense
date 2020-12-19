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
    private static Label currentScore;
    @FXML
    private static Label currentResources;
    @FXML
    private static Label currentLevel;
    @FXML
    private static Label currentLives;
    @FXML
    private static Label timeLabel;
    
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
    
    public static void updateLabels(String currentLevel1 , String currentLives1 , String currentResources1 , String currentScore1 , String timeLabel1){
        currentLevel.setText(currentLevel1);
        currentLives.setText(currentLives1);
        currentResources.setText(currentResources1);
        currentScore.setText(currentScore1);
        timeLabel.setText(timeLabel1);
    }
    
}
