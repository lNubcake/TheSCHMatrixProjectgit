package userInterface;

import application.primaryScreenBounds;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class SCHFrame 
{
	public GridPane theFrame;
	public SCHRect[][] Container;
	
	public SCHFrame(SCHFrame FrameToSet) 
	{
		
	}
	
	public SCHFrame()
	{
		theFrame = new GridPane();
		setUpConstraints();
		
		Container = new SCHRect[16][12];
		
		for(int i = 0; i < 16; i++)
		{
			for(int j = 0; j < 12; j++)
			{
				Container[i][j] = new SCHRect();
				theFrame.add(Container[i][j].theRect, 1+i, 1+j);
			}
		}
		
	}
	
	private void setUpConstraints()
	{
		int i = 0;
		
		theFrame.getColumnConstraints().add(new ColumnConstraints((primaryScreenBounds.Bounds.getWidth()/1920) * 164));
		theFrame.getRowConstraints().add(new RowConstraints((primaryScreenBounds.Bounds.getHeight()/1040) * 230));
		while(i < 16)
		{
			theFrame.getColumnConstraints().add(new ColumnConstraints((primaryScreenBounds.Bounds.getWidth()/1920) * 39));
			if(i < 12)
			{
				theFrame.getRowConstraints().add(new RowConstraints((primaryScreenBounds.Bounds.getHeight()/1040) * 62));
			}
			i++;
		}
	}
}
