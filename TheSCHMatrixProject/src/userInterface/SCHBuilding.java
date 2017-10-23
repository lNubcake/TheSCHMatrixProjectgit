package userInterface;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class SCHBuilding
{
	public GridPane theBuilding;
	
	public SCHBuilding()
	{
		theBuilding = new GridPane();
		theBuilding.getColumnConstraints().add((new ColumnConstraints(16)));
		//theBuilding.setBackground(new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY,Insets.EMPTY)));
		theBuilding.setPrefSize(49, 60);
		theBuilding.setMinSize(40, 60);
		theBuilding.setMaxSize(60, 60);
		//theBuilding.setVisible(true);
		theBuilding.relocate(250, 50);
		theBuilding.setBackground(new Background(new BackgroundImage(new Image("file:Building.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(400,400,false,false,true,true))));
		
		//theBuilding.setVisible(true);
		theBuilding.getStylesheets().add(getClass().getResource("schpalette.css").toExternalForm());
		theBuilding.getStyleClass().add("palettestyle");
	}
}
