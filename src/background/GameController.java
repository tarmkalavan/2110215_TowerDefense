package background;

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
    private Label currentScore;
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
    
    public void updateLabels(String currentLevel , String currentLives , String currentResources , String currentScore , String timeLabel){
        this.currentLevel.setText(currentLevel);
        this.currentLives.setText(currentLives);
        this.currentResources.setText(currentResources);
        this.currentScore.setText(currentScore);
        this.timeLabel.setText(timeLabel);
    }
    
}
