package logic;

import application.Main;
import background.TileMap;
import base.Tower;
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
	private Button upgrade;
	@FXML
	private Button mute;
	@FXML
	private Button exit;
	@FXML
	private Label currentResources = new Label("");
	@FXML
	private Label currentLevel = new Label("");
	@FXML
	private Label currentLives = new Label("");
	@FXML
	private Label timeLabel = new Label("");

	public void muteSound() {
		if (Main.sound.isPlaying()) {
			Main.sound.stop();
		} else {
			Main.sound.play();
		}
	}

	public void exitTheGame() {
		System.exit(0);
	}

	public void upgradeTower() {
		GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {

				int x = (int) t.getX() / 64;
				int y = (int) t.getY() / 64;

				for (Tower tower : GameLogic.getTowerList()) {
					if (tower.getXTile() == x && tower.getYTile() == y && GameLogic.getMoney() >= tower.getUpgradeCost()
							&& tower.getLevel() < GameLogic.TOWER_LEVEL_CAP) {
						tower.upgradeTower();
						tower.setLevel(tower.getLevel() + 1);
						GameLogic.setMoney(GameLogic.getMoney() - tower.getUpgradeCost());
						System.out.println("Remaining Money: " + GameLogic.getMoney());
					}
				}
			}
		});

	}

	public void buyBasicTower() {
		GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {

				double x = t.getX();
				double y = t.getY();
				if (TileMap.isNodeOpen((int) x / 64, (int) y / 64) && GameLogic.getMoney() >= 50) {
					GameLogic.setMoney(GameLogic.getMoney() - 50);
					System.out.println("Remaining Money: " + GameLogic.getMoney());
					GameLogic.buyTower(x, y, new BasicTower((int) x / 64, (int) y / 64));
				}
			}
		});
	}

	public void buyAcidTower() {
		GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {

				double x = t.getX();
				double y = t.getY();
				if (TileMap.isNodeOpen((int) x / 64, (int) y / 64) && GameLogic.getMoney() >= 120) {
					GameLogic.setMoney(GameLogic.getMoney() - 120);
					System.out.println("Remaining Money: " + GameLogic.getMoney());
					GameLogic.buyTower(x, y, new AcidTower((int) x / 64, (int) y / 64));
				}
			}
		});
	}

	public void buyArcaneTower() {
		GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {

				double x = t.getX();
				double y = t.getY();

				if (TileMap.isNodeOpen((int) x / 64, (int) y / 64) && GameLogic.getMoney() >= 150) {
					GameLogic.setMoney(GameLogic.getMoney() - 150);
					System.out.println("Remaining Money: " + GameLogic.getMoney());
					GameLogic.buyTower(x, y, new ArcaneTower((int) x / 64, (int) y / 64));
				}
			}
		});
	}

	public void buyArcticTower() {
		GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {

				double x = t.getX();
				double y = t.getY();

				if (TileMap.isNodeOpen((int) x / 64, (int) y / 64) && GameLogic.getMoney() >= 70) {
					GameLogic.setMoney(GameLogic.getMoney() - 70);
					System.out.println("Remaining Money: " + GameLogic.getMoney());
					GameLogic.buyTower(x, y, new ArcticTower((int) x / 64, (int) y / 64));
				}
			}
		});
	}

	public void buyBombardTower() {
		GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {

				double x = t.getX();
				double y = t.getY();

				if (TileMap.isNodeOpen((int) x / 64, (int) y / 64) && GameLogic.getMoney() >= 120) {
					GameLogic.setMoney(GameLogic.getMoney() - 150);
					System.out.println("Remaining Money: " + GameLogic.getMoney());
					GameLogic.buyTower(x, y, new BombardTower((int) x / 64, (int) y / 64));
				}
			}
		});
	}

	public void buySniperTower() {
		GameLogic.getGameScene().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {

				double x = t.getX();
				double y = t.getY();

				if (TileMap.isNodeOpen((int) x / 64, (int) y / 64) && GameLogic.getMoney() >= 200) {
					GameLogic.setMoney(GameLogic.getMoney() - 300);
					System.out.println("Remaining Money: " + GameLogic.getMoney());
					GameLogic.buyTower(x, y, new SniperTower((int) x / 64, (int) y / 64));
				}
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
