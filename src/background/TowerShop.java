package background;

import base.Tower;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;

public class TowerShop extends GridPane{
	
	private ObservableList<TowerButton> towerButtonList = FXCollections.observableArrayList();

	public TowerShop() {
		setVgap(5);
		Tower t = new Tower();
		towerButtonList.addAll(t);
		
	}
}
