package userInterface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SCHColor extends Rectangle
{	
	public SCHColor(Color ColorToSet)
	{
		super();
		
		setUp(ColorToSet);
	}
	
	/// This function provides that every 0 stays in its place
	public static String colorChannelToHex(double channelValue) // from stackoverflow https://stackoverflow.com/questions/17925318/how-to-get-hex-web-string-from-javafx-colorpicker-color
	{
		String rtn = Integer.toHexString((int) Math.min(Math.round((channelValue * 255)), 255 ));
		if(rtn.length() == 1)
		{
			rtn = "0" + rtn;
		}
		return rtn;
	}
	
	/// This function converts the color to Hexadecimal in string
	public static String colorToHex(Color color)
	{
		return "0x"
				+ colorChannelToHex(color.getRed())
				+ colorChannelToHex(color.getGreen())
				+ colorChannelToHex(color.getBlue())
				+ colorChannelToHex(color.getOpacity());
	}
	
	/// This function is called in the constructor
	public void setUp(Color ColorToSet)
	{
		this.setHeight(15);
		this.setWidth(15);
		
		// TODO make a background image on which it can set opacity
		
		this.setFill(ColorToSet);
		this.setVisible(true);
		this.setStroke(Color.BLACK);
		this.setStrokeWidth(1);
	}
	
	// TODO override onMousePressed
}