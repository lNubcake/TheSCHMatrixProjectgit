package userInterface;

import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;

import application.primaryScreenBounds;

public class SCHBuilding
{
	public BorderPane theBuilding;
	public int currentFrame;
	public ArrayList<SCHFrame> frameContainer;
	
	public SCHBuilding()
	{
		theBuilding = new BorderPane();
		frameContainer = new ArrayList<SCHFrame>(1);
		frameContainer.add(new SCHFrame());
		
		theBuilding.setBackground(new Background(new BackgroundImage(new Image("file:Building.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,new BackgroundPosition(Side.LEFT,primaryScreenBounds.Bounds.getMaxX()/1920*125,false,Side.TOP, 10, false),new BackgroundSize(700,845,false,false,false,false))));
		theBuilding.getStylesheets().add(getClass().getResource("schpalette.css").toExternalForm());
		
		theBuilding.setCenter(frameContainer.get(0).theFrame);
		currentFrame = 0;
	}
	
	public void toQP4(File Output) throws Exception
	// TODO handle exception
	{
		PrintWriter writer = new PrintWriter(Output.getPath(),"UTF-8");
		writer.write("--MyOwnAnimEditor 2017 Programozás alapjai 3\n");
		writer.write("meta({\n"
				+ "audio=\"\",\n"
				+ "team=\"\",\n"
				+ "title=\"\",\n"
				+ "year=" + Year.now().getValue()+ "})\n"
				+ "\n");
		for( SCHFrame frame : frameContainer )
		{
			frame.toQP4(Output,writer);
			writer.write("\n");
		}
		
		writer.write("rootclip(\"" + frameContainer.get(0).name + "\")");
		writer.close();
	}
	
	public void toAnim(File Input) throws Exception
	{
		frameContainer.clear();
		theBuilding.setCenter(null);
		
		Scanner scanner = new Scanner(Input);
		String rst = "";
		
		String currentLine;
		while(scanner.hasNext())
		{
			SCHFrame frameToAdd = new SCHFrame();
			currentLine = scanner.next();
			if(currentLine.equals("frame({"))
			{
				while(!currentLine.endsWith("endclip()"))
				{
				rst += currentLine;
				rst += "\n";
				currentLine = scanner.next();
				}
				
				String[] splitString = rst.split("\n");
				
				SCHFrame.toFrame(splitString,frameToAdd);
			
			frameContainer.add(frameToAdd);	
			}
		}
		scanner.close();
		
		theBuilding.setCenter(frameContainer.get(0).theFrame);
		currentFrame = 0;
			
	}
	
	public double getSumOfTime()
	{
		int res = 0;
		for(SCHFrame f : frameContainer)
		{
			res += f.timeIn_ms;
		}
		return res;
	}
	
	public void refresh()
	{
		theBuilding.setCenter(null);
		theBuilding.setCenter(frameContainer.get(currentFrame).theFrame);
	}
	
	public void addFrame()
	{
		frameContainer.add(new SCHFrame());
	}
}
