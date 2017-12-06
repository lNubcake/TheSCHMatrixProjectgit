package userInterface;

import application.primaryScreenBounds;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import tools.SCHTool;

public class SCHPoint extends Button
{
	public Color myColor = Color.BLACK;

	/// This constructor creates an SCHPoint witch a specific color
	public SCHPoint(Color ColorToSet)
	{
		super();
		// to debug
		myColor = ColorToSet;
		this.setMinSize((primaryScreenBounds.Bounds.getWidth()/1920) * 10,(primaryScreenBounds.Bounds.getHeight()/1040) * 10);
		this.setPrefSize((primaryScreenBounds.Bounds.getWidth()/1920) * 10, (primaryScreenBounds.Bounds.getHeight()/1040) * 10);
		this.setMaxSize((primaryScreenBounds.Bounds.getWidth()/1920) * 10, (primaryScreenBounds.Bounds.getHeight()/1040) * 10);
		setMyBackground(myColor);
		
		
		// TODO find a better way for this
		this.setOnMouseClicked(new onClickEventHandler());
	}
	
	/// This function sets the background value of the point
	public void setMyBackground(Color ColorToSet)
	{
		this.setBackground(new Background(new BackgroundFill(ColorToSet,CornerRadii.EMPTY,Insets.EMPTY)));
	}

	/// This function is called when you click on a point
	class onClickEventHandler implements EventHandler
	{

		@Override
		public void handle(Event event)
		{
			myColor = SCHTool.theColor;
			setMyBackground(myColor);
		}
	}
}
