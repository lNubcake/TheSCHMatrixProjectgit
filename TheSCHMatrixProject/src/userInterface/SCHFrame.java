package userInterface;

import java.io.File;
import java.io.PrintWriter;

import application.primaryScreenBounds;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class SCHFrame 
{
	public GridPane theFrame;
	public SCHRect[][] Container;
	public int timeIn_ms;
	public String name;
	
	/// Copying a frame;
	public SCHFrame(SCHFrame FrameToSet) 
	{
		theFrame = FrameToSet.theFrame;
		Container = FrameToSet.Container;
		timeIn_ms = FrameToSet.timeIn_ms;
		name = FrameToSet.name;
	}
	
	/// constructing a frame
	public SCHFrame()
	{
		theFrame = new GridPane();
		setUpConstraints();
		
		Container = new SCHRect[13][16];
		
		for(int i = 0; i < 13; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				Container[i][j] = new SCHRect();
				theFrame.add(Container[i][j].theRect, 1+j, 1+i);
			}
		}
		
		timeIn_ms = 1000;
		name = "test";
		
	}
	
	/// Sets up Constraints on theFrame , MAY DIFFER ON DIFFERENT COMPUTERS;
	private void setUpConstraints()
	{
		int i = 0;
		
		theFrame.getColumnConstraints().add(new ColumnConstraints((primaryScreenBounds.Bounds.getWidth()/1920) * 208));
		theFrame.getRowConstraints().add(new RowConstraints((primaryScreenBounds.Bounds.getHeight()/1040) * 195));
		while(i < 16)
		{
			theFrame.getColumnConstraints().add(new ColumnConstraints((primaryScreenBounds.Bounds.getWidth()/1920) * 33));
			if(i < 12)
			{
				theFrame.getRowConstraints().add(new RowConstraints((primaryScreenBounds.Bounds.getHeight()/1040) * 51));
			}
			i++;
		}
	}
	
	/// Writes the frame to the output
	public void toQP4(File Output, PrintWriter writer) throws Exception
	{
		try {
			
		writer.write(new String("beginclip(32,26,\"" + name + "\")\n"));
		writer.write(new String("frame({\n"));
		for(int row = 0; row < 13; row++)
		{
			for(int column = 0; column < 16; column++)
			{
				writer.write(new String(SCHColor.colorToHex(Container[row][column].thePoints.get(0).myColor) + ","));
				writer.write(new String(SCHColor.colorToHex(Container[row][column].thePoints.get(2).myColor) + ","));	
			}
			writer.write("\n");
			for(int column = 0; column < 16; column++)
			{
				writer.write(new String(SCHColor.colorToHex(Container[row][column].thePoints.get(1).myColor) + ","));
				writer.write(new String(SCHColor.colorToHex(Container[row][column].thePoints.get(3).myColor) + ","));	
			}
			writer.write("\n");
		}
		writer.write(new String("},"+ timeIn_ms + ")\n"));
		writer.write("endclip()");
		
		}catch(Exception e)
		{
			throw e;
		}
	}
	
	/// This function constructs the passed frame in the right way.
	static public SCHFrame toFrame(String[] splitString,SCHFrame frameToAdd) throws Exception
	{
		for(int index = 1; index < 27; index++)
		{
			String[] splitLineTOP = splitString[index].split(",");
			for(int lineIndex = 0; lineIndex < 32; lineIndex++)
			{
				try {
				frameToAdd.Container[(int)((index-1)/2)][(int)(lineIndex/2)].thePoints.get(0).myColor = SCHColor.hexToColor(splitLineTOP[lineIndex]);
				frameToAdd.Container[(int)((index-1)/2)][(int)(lineIndex/2)].thePoints.get(0).setMyBackground(SCHColor.hexToColor(splitLineTOP[lineIndex]));
				lineIndex++;
				frameToAdd.Container[(int)((index-1)/2)][(int)(lineIndex/2)].thePoints.get(2).myColor = SCHColor.hexToColor(splitLineTOP[lineIndex]);
				frameToAdd.Container[(int)((index-1)/2)][(int)(lineIndex/2)].thePoints.get(2).setMyBackground(SCHColor.hexToColor(splitLineTOP[lineIndex]));
				}catch(Exception e)
				{
					System.out.println("Exception caught at:" + (index-1)/2 + " " + lineIndex/2);
					throw e;
				}
			}
			index++;
			String[] splitLineBOT = splitString[index].split(",");
			for(int lineIndex = 0; lineIndex < 32; lineIndex++)
			{
				try {
				frameToAdd.Container[(int)((index-1)/2)][(int)(lineIndex/2)].thePoints.get(1).myColor = SCHColor.hexToColor(splitLineBOT[lineIndex]);
				frameToAdd.Container[(int)((index-1)/2)][(int)(lineIndex/2)].thePoints.get(1).setMyBackground(SCHColor.hexToColor(splitLineBOT[lineIndex]));
				lineIndex++;
				frameToAdd.Container[(int)((index-1)/2)][(int)(lineIndex/2)].thePoints.get(3).setMyBackground(SCHColor.hexToColor(splitLineBOT[lineIndex]));
				frameToAdd.Container[(int)((index-1)/2)][(int)(lineIndex/2)].thePoints.get(3).myColor = SCHColor.hexToColor(splitLineBOT[lineIndex]);
				}catch(Exception e)
				{
					System.out.println("Exception caught at:" + (index-1)/2 + " " + lineIndex/2);
					throw e;
				}
			}
			
		}
		return frameToAdd;
	}
}
