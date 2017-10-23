package userInterface;

import java.util.ArrayList;

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
		theRect.setMaxSize(20, 20);
		theRect.getColumnConstraints().add(new ColumnConstraints(5));
		theRect.getColumnConstraints().add(new ColumnConstraints(5));
		theRect.getRowConstraints().add(new RowConstraints(5));
		theRect.getRowConstraints().add(new RowConstraints(5));
		theRect.setConstraints(thePoints.get(0),0,0);
		theRect.getChildren().add(thePoints.get(0));
		theRect.setConstraints(thePoints.get(1),1,0);
		theRect.getChildren().add(thePoints.get(1));
		theRect.setConstraints(thePoints.get(2),0,1);
		theRect.getChildren().add(thePoints.get(2));
		theRect.setConstraints(thePoints.get(3),1,1);
		theRect.getChildren().add(thePoints.get(3));
		
		theRect.relocate(200,300);
	}
}
