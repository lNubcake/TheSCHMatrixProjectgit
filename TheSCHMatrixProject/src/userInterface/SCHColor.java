package userInterface;

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
		this.setHeight(20);
		this.setWidth(20);
		
		this.setFill(ColorToSet);
		this.setVisible(true);
		this.setStroke(Color.BLACK);
		this.setStrokeWidth(1);
		
		this.setOnMouseClicked(new onClickEventHandler());
	}
	
	public Color SCHgetColor()
	{
		return (Color) this.getFill();
	}
	
	class onClickEventHandler implements EventHandler
	{
		@Override
		public void handle(Event event)
		{
			SCHTool.theColor = SCHgetColor();
		}
	}
}
