package logic;

import application.Main;
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
	private Label currentResources = new Label("");
	@FXML
	private Label currentLevel= new Label("");
	@FXML
	private Label currentLives= new Label("");
	@FXML
	private Label timeLabel= new Label("");
	
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
	
    public void buyBasicTower(){
    	GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent t) {
    		 
    		double x = t.getX();
    		double y = t.getY();
    		 
    		GameLogic.buyTower(x, y,new BasicTower((int)x/64, (int)y/64));
    		}
    	});
    }
	
    public void buyAcidTower(){
        GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent t) {
    		 
    		double x = t.getX();
    		double y = t.getY();
    		 
    		GameLogic.buyTower(x, y,new AcidTower((int)x/64, (int)y/64));
    		}
    	});
    }
	
    public void buyArcaneTower(){
    	GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent t) {
    		 
    		double x = t.getX();
    		double y = t.getY();
    		 
    		GameLogic.buyTower(x, y,new ArcaneTower((int)x/64, (int)y/64));
    		}
    	});
    }
	
    public void buyArcticTower(){
    	GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent t) {
    		 
    		double x = t.getX();
    		double y = t.getY();
    		 
    		GameLogic.buyTower(x, y,new ArcticTower((int)x/64, (int)y/64));
    		}
    	});
    }
	
    public void buyBombardTower(){
    	GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent t) {
    		 
    		double x = t.getX();
    		double y = t.getY();
    		 
    		GameLogic.buyTower(x, y,new BombardTower((int)x/64, (int)y/64));
    		}
    	});
    }
	
    public void buySniperTower(){
    	GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent t) {
    		 
    		double x = t.getX();
    		double y = t.getY();
    		 
    		GameLogic.buyTower(x, y,new SniperTower((int)x/64, (int)y/64));
    		}
    	});
    }
    
	@FXML
    public void initialize() {
        this.currentLevel.setText(Integer.toString(GameLogic.getLevel()));
        this.currentLives.setText(Integer.toString(GameLogic.getLives()));
        this.currentResources.setText(Integer.toString(GameLogic.getMoney()));
        this.timeLabel.setText(Integer.toString(GameLogic.getTime()));
    }

}
