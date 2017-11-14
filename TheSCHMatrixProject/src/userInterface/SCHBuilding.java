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
import application.primaryScreenBounds;

public class SCHBuilding
{
	public GridPane theBuilding;
	
	public SCHBuilding()
	{
		theBuilding = new GridPane();
		
		theBuilding.setBackground(new Background(new BackgroundImage(new Image("file:Building.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(700,880,false,false,true,false))));
		theBuilding.getStylesheets().add(getClass().getResource("schpalette.css").toExternalForm());
		
		theBuilding.add((new SCHFrame()).theFrame, 0, 0);
	}
	
}
