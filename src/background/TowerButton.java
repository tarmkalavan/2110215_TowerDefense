package background;

import base.Tower;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class TowerButton extends Button {
	
	private Tower tower;
	
	public TowerButton(String towerName) {
		setPadding(new Insets(5));
		tower = new Tower (towerName);
		String iconPath = ClassLoader.getSystemResource(tower.getUrl()).toString();
		ImageView icon = new ImageView(new Image(iconPath));
		icon.setFitWidth(64);
		icon.setFitHeight(64);
		setGraphic(icon);
		setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	public void highlight() {
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.FULL)));
	}

	public void unhighlight() {
		setBorder(null);
	}

}
