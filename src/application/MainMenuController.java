package application;

import javafx.scene.control.Button;

public class MainMenuController {

	private Button exitButton;
	private Button muteButton;

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

}
