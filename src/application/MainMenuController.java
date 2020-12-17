package application;

import java.io.IOException;

import background.GameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

	@FXML
	private Button exitButton;
	
	@FXML
	private Button muteButton;
	
	@FXML
	private Button creditsButton;
	
	@FXML
	private Button startButton;

	public void exitTheGame() {
		System.exit(1);
	}
	
	public void muteSound() {
		if (Main.sound.isPlaying()) {
			Main.sound.stop();
		} else {
			Main.sound.play();
		}
	}

	public void showCredits() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainMenu/Credits.fxml"));
			Parent root1 = (Parent) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Credits");
			stage.setScene(new Scene(root1));  
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startGame() {
		GameManager manager = new GameManager();
		manager.createGameMap();
	}
}
