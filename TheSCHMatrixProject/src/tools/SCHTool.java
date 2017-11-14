package tools;

import javafx.scene.paint.Color;

public class SCHTool
{
	static public Color theColor;
	
	public SCHTool()
	{
		theColor = new Color(0,0,0,1);
	}
	
	public static void setColor(Color ColorToSet)
	{
		theColor = ColorToSet;
	}

}
