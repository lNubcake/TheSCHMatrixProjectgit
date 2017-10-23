package userInterface;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class SCHPoint extends Button
{
	Color myColor = Color.BLACK;

	public SCHPoint(Color ColorToSet)
	{
		super();
		// to debug
		myColor = ColorToSet;
		this.setMinSize(5,5);
		this.setBackground(new Background(new BackgroundFill(myColor,CornerRadii.EMPTY,Insets.EMPTY)));
	}
}
