package userInterface;

import userInterface.SCHColor;
import application.primaryScreenBounds;
import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SCHPalette
{
	public GridPane palette;
	
	public SCHPalette()
	{
		Init();
		
		addColors();		
		
	}

	/// This function initializes all members 
	public void Init()
	{
		palette = new GridPane();
		
		setUpConstraints();

		palette.setHgap(1);
		palette.setVgap(1);
		palette.setPrefSize(primaryScreenBounds.Bounds.getWidth()/1920*200, primaryScreenBounds.Bounds.getHeight()/1040*100);
		palette.getStylesheets().add(getClass().getResource("schpalette.css").toExternalForm());
		// DEBUG purposes
		palette.getStyleClass().add("palettestyle");
	}
	
	/// This function sets up the ColumnConstraints in palette
	private void setUpConstraints()
	{
		palette.getColumnConstraints().add((new ColumnConstraints(primaryScreenBounds.Bounds.getHeight()/1040*21)));
		palette.getColumnConstraints().add((new ColumnConstraints(primaryScreenBounds.Bounds.getHeight()/1040*21)));
		palette.getColumnConstraints().add((new ColumnConstraints(primaryScreenBounds.Bounds.getHeight()/1040*21)));
		palette.getColumnConstraints().add((new ColumnConstraints(primaryScreenBounds.Bounds.getHeight()/1040*21)));
		palette.getColumnConstraints().add((new ColumnConstraints(primaryScreenBounds.Bounds.getHeight()/1040*21)));
		palette.getColumnConstraints().add((new ColumnConstraints(primaryScreenBounds.Bounds.getHeight()/1040*21)));
		palette.getColumnConstraints().add((new ColumnConstraints(primaryScreenBounds.Bounds.getHeight()/1040*21)));
	}
	
	/// This is the function in which you shall add more colors
	private void addColors()
	{
		// TODO add all colors
		
		HBox BlackToWhite = new HBox();
		BlackToWhite.getChildren().add(new SCHColor(new Color(0,0,0,1))); // black
		BlackToWhite.getChildren().add(new SCHColor(new Color((36.0/255),(36.0/255),(36.0/255),1))); // grey
		BlackToWhite.getChildren().add(new SCHColor(new Color(72.0/255,72.0/255,72.0/255,1)));
		BlackToWhite.getChildren().add(new SCHColor(new Color(109.0/255,109.0/255,109.0/255,1)));
		BlackToWhite.getChildren().add(new SCHColor(new Color(145.0/255,145.0/255,145.0/255,1)));
		BlackToWhite.getChildren().add(new SCHColor(new Color(218.0/255,218.0/255,218.0/255,1)));
		BlackToWhite.getChildren().add(new SCHColor(new Color(1,1,1,1))); // white
		BlackToWhite.getStyleClass().add("hbox");
		
		HBox BlueToGreen = new HBox();
		BlueToGreen.getChildren().add(new SCHColor(new Color(0,0,72.0/255,1))); // dark blue
		BlueToGreen.getChildren().add(new SCHColor(new Color(0,0,145.0/255,1)));
		BlueToGreen.getChildren().add(new SCHColor(new Color(0,0,182.0/255,1)));
		BlueToGreen.getChildren().add(new SCHColor(new Color(0,72.0/255,0,1)));
		BlueToGreen.getChildren().add(new SCHColor(new Color(0,145.0/255,0,1)));
		BlueToGreen.getChildren().add(new SCHColor(new Color(0,182.0/255,0,1)));
		BlueToGreen.getChildren().add(new SCHColor(new Color(0,1,0,1))); // green
		BlueToGreen.getStyleClass().add("hbox");
		
		HBox Cyanlikes = new HBox();
		Cyanlikes.getChildren().add(new SCHColor(new Color(0,0,1,127.0/255))); // dark cyan
		Cyanlikes.getChildren().add(new SCHColor(new Color(0,109.0/255,109.0/255,127.0/255)));
		Cyanlikes.getChildren().add(new SCHColor(new Color(0,1,1,127.0/255)));
		Cyanlikes.getChildren().add(new SCHColor(new Color(0,72.0/255,72.0/255,1)));
		Cyanlikes.getChildren().add(new SCHColor(new Color(0,145.0/255,145.0/255,1)));
		Cyanlikes.getChildren().add(new SCHColor(new Color(0,182.0/255,182.0/255,1)));
		Cyanlikes.getChildren().add(new SCHColor(new Color(0,1,1,1)));
		Cyanlikes.getStyleClass().add("hbox");
		
		HBox GreenieToYellow = new HBox();
		GreenieToYellow.getChildren().add(new SCHColor(new Color(1,1,1,127.0/255)));
		GreenieToYellow.getChildren().add(new SCHColor(new Color(0,109.0/255,0,127.0/255)));
		GreenieToYellow.getChildren().add(new SCHColor(new Color(0,1,0,127.0/255)));
		GreenieToYellow.getChildren().add(new SCHColor(new Color(1,1,0,127.0/255)));
		GreenieToYellow.getChildren().add(new SCHColor(new Color(72.0/255,72.0/255,0,1)));
		GreenieToYellow.getChildren().add(new SCHColor(new Color(145.0/255,145.0/255,0,1)));
		GreenieToYellow.getChildren().add(new SCHColor(new Color(1,1,0,1)));
		GreenieToYellow.getStyleClass().add("hbox");
		
		HBox OrangeToRed = new HBox();
		OrangeToRed.getChildren().add(new SCHColor(new Color(109.0/255,0,0,127.0/255)));
		OrangeToRed.getChildren().add(new SCHColor(new Color(72.0/255,0,0,1)));
		OrangeToRed.getChildren().add(new SCHColor(new Color(1,0,0,127.0/255)));
		OrangeToRed.getChildren().add(new SCHColor(new Color(182.0/255,72.0/255,0,1)));
		OrangeToRed.getChildren().add(new SCHColor(new Color(145.0/255,0,0,1)));
		OrangeToRed.getChildren().add(new SCHColor(new Color(182.0/255,0,0,1)));
		OrangeToRed.getChildren().add(new SCHColor(new Color(1,0,0,1)));
		OrangeToRed.getStyleClass().add("hbox");
		
		HBox PinkOnes = new HBox();
		PinkOnes.getChildren().add(new SCHColor(new Color(0,0,109.0/255,127.0/255)));
		PinkOnes.getChildren().add(new SCHColor(new Color(109.0/255,36.0/255,0,127.0/255)));
		PinkOnes.getChildren().add(new SCHColor(new Color(109.0/255,0,109.0/255,127.0/255)));
		PinkOnes.getChildren().add(new SCHColor(new Color(1,0,1,127.0/255)));
		PinkOnes.getChildren().add(new SCHColor(new Color(72.0/255,0,72.0/255,1)));
		PinkOnes.getChildren().add(new SCHColor(new Color(145.0/255,0,145.0/255,1)));
		PinkOnes.getChildren().add(new SCHColor(new Color(1,0,1,1)));
		PinkOnes.getStyleClass().add("hbox");
		
		palette.add(BlackToWhite, 0, 0);
		palette.add(BlueToGreen, 0, 1);
		palette.add(Cyanlikes, 0, 2);
		palette.add(GreenieToYellow, 0, 3);
		palette.add(OrangeToRed, 0, 4);
		palette.add(PinkOnes, 0, 5);
		
		palette.setVisible(true);
	}
}
