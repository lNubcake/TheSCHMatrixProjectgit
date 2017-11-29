package userInterface;

import application.primaryScreenBounds;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import tools.SCHTool;

public class SCHColor extends Rectangle
{
	
	public SCHColor(Color ColorToSet)
	{
		super();
		
		setUp(ColorToSet);
	}
	
	/// This function converts a hexadecimal string representing a color to an actual Color
	public static Color hexToColor(String hex)
	{
		if(!hex.contains("x"))
		{
			return Color.BLACK;
		}
		
		String[] cutDown0x = hex.split("x",2);
		char[] cutDownSplit = cutDown0x[1].toCharArray();
		String ColorChannelRed = "";ColorChannelRed += cutDownSplit[0]; ColorChannelRed += cutDownSplit[1];
		String ColorChannelGreen = "";ColorChannelGreen += cutDownSplit[2]; ColorChannelGreen += cutDownSplit[3];
		String ColorChannelBlue = "";ColorChannelBlue += cutDownSplit[4]; ColorChannelBlue += cutDownSplit[5];
		String ColorChannelOpacity = "";ColorChannelOpacity += cutDownSplit[6]; ColorChannelOpacity += cutDownSplit[7];
		
		return new Color(((double)(Integer.parseInt(ColorChannelRed,16)))/255,
						((double)(Integer.parseInt(ColorChannelGreen,16)))/255,
						((double)(Integer.parseInt(ColorChannelBlue,16)))/255,
						((double)(Integer.parseInt(ColorChannelOpacity,16)))/255);
		
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
		String result = "0x"
				+ colorChannelToHex(color.getRed())
				+ colorChannelToHex(color.getGreen())
				+ colorChannelToHex(color.getBlue())
				+ colorChannelToHex(color.getOpacity());
		
		return  result.equals(new String("0x000000ff")) ? "0" : result;
	}
	
	/// This function is called in the constructor
	public void setUp(Color ColorToSet)
	{
		this.setHeight(primaryScreenBounds.Bounds.getWidth()/1920*20);
		this.setWidth(primaryScreenBounds.Bounds.getHeight()/1040*20);
		
		this.setFill(ColorToSet);
		this.setVisible(true);
		this.setStroke(Color.BLACK);
		this.setStrokeWidth(1);
		
		this.setOnMouseClicked(new onClickEventHandler());
	}
	
	/// This function returns the background color value of this button
	public Color SCHgetColor()
	{
		return (Color) this.getFill();
	}
	
	/// This function is called when somebody clicks on the button
	class onClickEventHandler implements EventHandler
	{
		@Override
		public void handle(Event event)
		{
			SCHTool.theColor = SCHgetColor();
		}
	}
}
