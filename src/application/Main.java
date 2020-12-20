package application;

import base.Tower;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.GameLogic;
import javafx.scene.media.AudioClip;

public class Main extends Application {

	public static AudioClip sound = new AudioClip(ClassLoader.getSystemResource("MainMenu/Wild Boar's Inn.mp3").toString());

	@Override
	public void start(Stage stage) {
		try {
			stage.setTitle("Tower Defense");
			Parent root = FXMLLoader.load(getClass().getResource("/MainMenu/Menu.fxml"));
			sound.play();
			Scene scene = new Scene(root, 1280, 800);
			stage.setScene(scene);
			stage.setResizable(false);
			MenuNavigator.setStage(stage);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
		System.exit(0);
	}
	
	@Override
	public void stop() throws Exception{
		for(Tower tower : GameLogic.getTowerList()) {
			tower.stopTowerAttack();
		}
		sound.stop();
	}

}
