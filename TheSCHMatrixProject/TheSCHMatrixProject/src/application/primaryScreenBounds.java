package application;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class primaryScreenBounds 
{
	// Holds the visual bounds of the current PC-s screen
	public static Rectangle2D Bounds  = Screen.getPrimary().getVisualBounds();
}
