package userInterface;

import java.util.ArrayList;

import application.primaryScreenBounds;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

public class SCHRect
{
	public GridPane theRect = new GridPane();
	public ArrayList<SCHPoint> thePoints = new ArrayList<SCHPoint>();
	
	public SCHRect()
	{
		//for(int i = 0; i < 4; i++ )
		
		// DEBUG
		thePoints.add(new SCHPoint(Color.BLACK));
		thePoints.add(new SCHPoint(Color.BLUE));
		thePoints.add(new SCHPoint(Color.AQUA));
		thePoints.add(new SCHPoint(Color.BEIGE));	
		
		theRect = new GridPane();
		theRect.setMaxSize((primaryScreenBounds.Bounds.getWidth()/1920) * 40, (primaryScreenBounds.Bounds.getHeight()/1040) * 40);
		theRect.getColumnConstraints().add(new ColumnConstraints((primaryScreenBounds.Bounds.getWidth()/1920) *10));
		theRect.getColumnConstraints().add(new ColumnConstraints((primaryScreenBounds.Bounds.getWidth()/1920) *10));
		theRect.getRowConstraints().add(new RowConstraints((primaryScreenBounds.Bounds.getHeight()/1040) *10));
		theRect.getRowConstraints().add(new RowConstraints((primaryScreenBounds.Bounds.getHeight()/1040) *10));
		theRect.setConstraints(thePoints.get(0),0,0);
		theRect.getChildren().add(thePoints.get(0));
		theRect.setConstraints(thePoints.get(1),1,0);
		theRect.getChildren().add(thePoints.get(1));
		theRect.setConstraints(thePoints.get(2),0,1);
		theRect.getChildren().add(thePoints.get(2));
		theRect.setConstraints(thePoints.get(3),1,1);
		theRect.getChildren().add(thePoints.get(3));
	}
}
