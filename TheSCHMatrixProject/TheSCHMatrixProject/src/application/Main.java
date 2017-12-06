package application;
	
import java.io.File;

import org.junit.Test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;

import toolbar.SCHBrush;
import tools.SCHBrushTool;
import tools.SCHTool;
import userInterface.SCHBuilding;
import userInterface.SCHPalette;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;

public class Main extends Application {
	
	// this variable holds the File in which you are working in
	private static File fileOutput;
	// Helps to pass the current stage to sub-classes
	private static Stage stageToPass;
	
	private static Scene scene;
	// Contains every element of the GUI
	private static GridPane GUI;
	// GUI + MenuBar
	private static GridPane root;
	// GUI elements
	private static SCHPalette palette;
	private static SCHBrush brush;
	private static SCHBuilding building;
	private static SCHTool currentTool;
	private static MenuBar menuBar;
	public static Slider timeSlider;
	public static Label timeLabel;
	public static Label frameCounter;
	private static HBox frameController;
	
	private static GridPane frameControllerPane;
	
	public static int programTime;
	
	private static Boolean bIsPlayed;
	private static Button play_stop;
	
	@Override
	public void start(Stage primaryStage) {
		
		stageToPass = primaryStage;
		
		try {
			currentTool = new SCHBrushTool();
			programTime = 0;
			bIsPlayed = false;
			
			initGUI();
			initRoot();
			initScene(primaryStage);
			
			timeSlider.setMajorTickUnit(100);
			timeSlider.setShowTickMarks(true);
			timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
				public void changed(ObservableValue<? extends Number> ov, Number old_value, Number new_value)
				{
					Main.programTime = new_value.intValue();
					Platform.runLater(new Runnable() {
						@Override
						public void run()
						{
							Main.timeLabel.setText(new String(Main.programTime +"/"+ Main.building.getSumOfTime()));
						}
					});
					if((int)(programTime/1000) != building.currentFrame && programTime != building.getSumOfTime())
					{
						building.currentFrame = (int)programTime/1000;
						building.refresh();
					}
				}
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{	
		launch(args);
	}
	
	/// This function initializes the main Scene, on which components are displayed
	private void initScene(Stage primaryStage) throws Exception
	{
		// TODO remove this one, debug purposes
		System.out.println("x:" + primaryScreenBounds.Bounds.getMaxX() + "        " + "y" + primaryScreenBounds.Bounds.getMaxY());
		
		scene = new Scene(root,primaryScreenBounds.Bounds.getMaxX()-5,primaryScreenBounds.Bounds.getMaxY()-35);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		primaryStage.setScene(scene);
		
		primaryStage.setX(-3);
		primaryStage.setY(0);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	/// This function initializes the GUI Pane and adds other Children to it.
	private void initGUI() throws Exception
	{
		GUI = new GridPane();
		GUI.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		GUI.getStyleClass().add("debuglines");
		
		setUpGUIConstraints();
		
		initFrameController();
		
		addChildrenToGUI();
	}
	
	/// This function initializes the root Pane and adds other Children to it.
	private void initRoot() throws Exception
	{
		
		root = new GridPane();
		root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		root.getStyleClass().add("debuglines");
		
		createMenu();
		
		setUpRootConstraints();
		
		addChildrenToRoot();
		
	}
	
	/// This function sets up the Constraints on the GUI GridPane
	private void setUpGUIConstraints()
	{
		GUI.setHgap(primaryScreenBounds.Bounds.getMaxX()/1920*10);
		GUI.setVgap(primaryScreenBounds.Bounds.getMaxY()/1040*10);
		
		//toolbar
		GUI.getColumnConstraints().add(new ColumnConstraints(primaryScreenBounds.Bounds.getWidth()/1920*30));
		GUI.getRowConstraints().add(new RowConstraints(primaryScreenBounds.Bounds.getWidth()/1040*15));
		//SCHbuilding
		GUI.getColumnConstraints().add(new ColumnConstraints(primaryScreenBounds.Bounds.getWidth()/1920*955));
		GUI.getRowConstraints().add(new RowConstraints(primaryScreenBounds.Bounds.getHeight()/1040*845));
		//palette
		GUI.getColumnConstraints().add(new ColumnConstraints(primaryScreenBounds.Bounds.getWidth()/1920*300));
		//TimeSlider
		GUI.getRowConstraints().add(new RowConstraints(primaryScreenBounds.Bounds.getHeight()/1040*40));
		//FrameControllerButtons
		GUI.getRowConstraints().add(new RowConstraints(primaryScreenBounds.Bounds.getHeight()/1040*40));
	
	}
	
	///This function sets up the Constraints on the Root GridPane
	private void setUpRootConstraints()
	{
		//menuBar
		root.getRowConstraints().add(new RowConstraints(primaryScreenBounds.Bounds.getMaxY()/1040*30));
	}
	
	/// This function adds all the elements to the root GridPane
	private void addChildrenToRoot()
	{
		root.add(menuBar, 0, 0);
		root.add(GUI, 0, 1);
	}
	
	/// This function adds all the elements to the GUI GridPane
	private void addChildrenToGUI()
	{
		palette = new SCHPalette();
		brush = new SCHBrush();
		building = new SCHBuilding();
		timeSlider = new Slider(0,1000,programTime);
		timeLabel = new Label(new String(programTime + "/" + building.getSumOfTime()));
		
		GUI.add(brush, 0, 0);
		GUI.add(building.theBuilding, 1, 1);
		GUI.add(palette.palette, 2, 1);
		GUI.add(timeSlider, 1, 2);
		GUI.add(frameControllerPane, 1, 3);
		
		GUI.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		frameControllerPane.getStyleClass().add("framecontrollersheet");
		
		Button addFrame = new Button();
		addFrame.setBackground(new Background(new BackgroundImage(new Image("file:add.jpg"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(40,40,false,false,true,false))));
		addFrame.setPrefSize(40, 40);
		addFrame.setOnMouseClicked(event -> {
			Main.building.addFrame();
			Main.frameCounter.setText(new String("  " + (building.currentFrame+1) + "/" + building.frameContainer.size()));
			Main.timeLabel.setText(new String(Main.programTime +"/"+ Main.building.getSumOfTime()));
			Main.timeSlider.setMax(Main.building.getSumOfTime());
		});

		Button deleteFrame = new Button();
		deleteFrame.setBackground(new Background(new BackgroundImage(new Image("file:delete.jpg"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(40,40,false,false,true,false))));
		deleteFrame.setPrefSize(40, 40);
		deleteFrame.setOnMouseClicked(event -> {
			if(Main.programTime >= 1000)
			{
			Main.programTime -= 1000;
			Main.building.deleteFrame();
			Main.frameCounter.setText(new String("  " + (building.currentFrame+1) + "/" + building.frameContainer.size()));
			Main.timeLabel.setText(new String(Main.programTime +"/"+ Main.building.getSumOfTime()));
			Main.timeSlider.setMax(Main.building.getSumOfTime());
			}
		});
		
		HBox frameButtonBox = new HBox();
		frameButtonBox.getChildren().addAll(addFrame,deleteFrame);
		frameButtonBox.setSpacing(5);
		
		HBox timeLabelWithframeButtonBox = new HBox();
		timeLabelWithframeButtonBox.getChildren().addAll(timeLabel,frameButtonBox);
		timeLabelWithframeButtonBox.setSpacing(50);
		timeLabelWithframeButtonBox.setAlignment(Pos.BOTTOM_LEFT);
		GUI.add(timeLabelWithframeButtonBox, 2,2);
		
		frameCounter = new Label(new String("  " + (building.currentFrame+1) + "/" + building.frameContainer.size()));
		GUI.add(frameCounter, 0, 2);
	}
	
	/// This function creates the menuBar
	@SuppressWarnings("unchecked")
	private void createMenu()
	{
		menuBar = new MenuBar();
		
		Menu mFile = new Menu("File");
		
		MenuItem openFile = new MenuItem("Open");
		openFile.setGraphic(new ImageView(new Image("file:openfile.png",15,15,false,false)));
		openFile.setOnAction(new openFileEventHandler());
		
		MenuItem saveFile = new MenuItem("Save");
		saveFile.setGraphic(new ImageView(new Image("file:savefile.png",15,15,false,false)));
		saveFile.setOnAction(new saveFileEventHandler());
		
		mFile.getItems().addAll(openFile,saveFile);
		
		Menu mEdit = new Menu("Edit");
		
		// Adding created menus to menuBar
		menuBar.getMenus().addAll( mFile , mEdit );
		menuBar.autosize();
		menuBar.setUseSystemMenuBar(true);
	}
	
	/// This function creates the button bar that controls the frames
	@SuppressWarnings("unchecked")
	public void initFrameController()
	{
		frameController = new HBox();
		frameController.setSpacing(30);
		
		frameControllerPane = new GridPane();
		
		Button first = new Button();
		
		first.setVisible(true);first.setBackground(new Background(new BackgroundImage(new Image("file:first.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
		first.setMinSize(20, 20);
		first.setOnMouseClicked(new firstEventHandler());
		
		Button stepLeft = new Button();
		stepLeft.setBackground(new Background(new BackgroundImage(new Image("file:arrow_left.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
		stepLeft.setVisible(true);
		stepLeft.setMinSize(20, 20);
		stepLeft.setOnMouseClicked(new stepLeftEventHandler());
		
		play_stop = new Button();
		play_stop.setBackground(new Background(new BackgroundImage(new Image("file:play.jpg"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
		play_stop.setVisible(true);
		play_stop.setMinSize(25, 25);
		play_stop.setOnMouseClicked(new play_stopEventHandler());
		
		Button stepRight = new Button();
		stepRight.setBackground(new Background(new BackgroundImage(new Image("file:arrow_right.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
		stepRight.setVisible(true);
		stepRight.setMinSize(20, 20);
		stepRight.setOnMouseClicked(new stepRightEventHandler());
		
		Button last = new Button();
		last.setBackground(new Background(new BackgroundImage(new Image("file:last.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
		last.setVisible(true);
		last.setMinSize(20, 20);
		last.setOnMouseClicked(new lastEventHandler());
		
		frameController.getChildren().addAll(first,stepLeft,play_stop,stepRight,last);
		frameControllerPane.add(frameController,0,0);
		
	}

	/// This EventHandler implementer class is called when somebody clicks on menu: File->save
	@SuppressWarnings("rawtypes")
	class saveFileEventHandler implements EventHandler
	{
		final FileChooser fileChooser = new FileChooser();
		
		@Override
		public void handle(Event event)
		{
			fileChooser.setTitle("Saving animation...");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Qp4 source files", "*.qp4"));
			
			fileOutput = null;
			fileOutput = fileChooser.showSaveDialog(stageToPass);
			
			/// TODO remove this one, debug purposes
			System.out.println(fileOutput.getPath());
			
			if(fileOutput != null)
			{
				try {
				building.toQP4(fileOutput);
				// TODO remove this one, debug purposes
				System.out.println("saving completed");
				}catch(Exception e)
				{
					System.err.println("Exception" + e.toString() + " was caught in class " + this.getClass().getName());
				}
			}
		}
	}
	
	/// This EventHandler implementer class is called when somebody clicks on menu: File->open
	@SuppressWarnings("rawtypes")
	class openFileEventHandler implements EventHandler
	{
		final FileChooser fileChooser = new FileChooser();
		
		@Override
		public void handle(Event event)
		{
			fileChooser.setTitle("Opening animation...");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Qp4 source files", "*.qp4"));
			
			fileOutput = null;
			fileOutput = fileChooser.showOpenDialog(stageToPass);
			if(fileOutput != null);
			{
				try {
				// TODO remove this one, debug purposes
				System.out.println(fileOutput.toString());
				building.toAnim(fileOutput);
				}catch(Exception e)
				{
					System.err.println("Exception" + e.toString() + " was caught in class " + this.getClass().getName());
				}
			}
		}
	}
	
	/// This EventHandler implementer class is called when somebody clicks on: Button:stepLeft
	@SuppressWarnings("rawtypes")
	class stepLeftEventHandler implements EventHandler
	{
		@Override
		public void handle(Event event)
		{
			if(building.currentFrame > 0)
			{
				Main.programTime -= 1000;
				Main.timeSlider.setValue(Main.programTime);
				Main.timeLabel.setText(new String(programTime + "/" + building.getSumOfTime()));
				if((int)(programTime/1000) != building.currentFrame)
				{
					building.currentFrame = (int)programTime/1000;
					building.refresh();
				}
				System.out.println(new String(this.getClass().getName() + "clicked"));
				frameCounter.setText(new String("  " + (building.currentFrame+1) + "/" + building.frameContainer.size()));
				
			}
		}
	}
	
	/// This EventHandler implementer class is called when somebody clicks on: Button:stepRight
	@SuppressWarnings("rawtypes")
	class stepRightEventHandler implements EventHandler
	{
		@Override
		public void handle(Event event)
		{
			if(building.currentFrame < building.frameContainer.size()-1)
			{
				Main.programTime += 1000;
				Main.timeSlider.setValue(Main.programTime);
				Main.timeLabel.setText(new String(programTime + "/" + building.getSumOfTime()));
				if((int)(programTime/1000) != building.currentFrame)
				{
					building.currentFrame = (int)programTime/1000;
					building.refresh();
				}
				System.out.println(new String(this.getClass().getName() + "clicked"));
				frameCounter.setText(new String("  " + (building.currentFrame+1) + "/" + building.frameContainer.size()));
			}
		}
	}
	
	/// This EventHandler implementer class is called when somebody clicks on: Button:first
	@SuppressWarnings("rawtypes")
	class firstEventHandler implements EventHandler
	{
		@Override
		public void handle(Event event)
		{
				Main.programTime = 0;
				if((int)(programTime/1000) != building.currentFrame)
				{
					building.currentFrame = (int)programTime/1000;
					building.refresh();
				}
				System.out.println(new String(this.getClass().getName() + "clicked"));
				frameCounter.setText(new String("  " + (building.currentFrame+1) + "/" + building.frameContainer.size()));
				Main.timeSlider.setValue(Main.programTime);
				Main.timeLabel.setText(new String(programTime + "/" + building.getSumOfTime()));
		}
	}
	
	/// This EventHandler implementer class is called when somebody clicks on: Button:last
	@SuppressWarnings("rawtypes")
	class lastEventHandler implements EventHandler
	{
		@Override
		public void handle(Event event)
		{
				Main.programTime = building.getSumOfTime()-1000;
				if((int)(programTime/1000) != building.currentFrame)
				{
					building.currentFrame = (int)programTime/1000;
					building.refresh();
				}
				System.out.println(new String(this.getClass().getName() + "clicked"));
				frameCounter.setText(new String("  " + (building.currentFrame+1) + "/" + building.frameContainer.size()));
				Main.timeSlider.setValue(Main.programTime);
				Main.timeLabel.setText(new String(programTime + "/" + building.getSumOfTime()));
		}
	}
	
	/// This EventHandler implementer class is called when somebody clicks on: Button:play_stop
	// TODO implement this one
	@SuppressWarnings("rawtypes")
	class play_stopEventHandler implements EventHandler
	{
		@SuppressWarnings("unchecked")
		@Override
		public void handle(Event event)
		{
			if(bIsPlayed == false)
			{
				bIsPlayed = true;

				Task playin = new Task<Integer>() 
				{
					@Override
					protected Integer call() throws Exception
					{
						do
						{	
							Main.programTime += 1;
							updateValue(Main.programTime);
							updateMessage(new String(Main.programTime + "/" + Main.building.getSumOfTime()));
							if(Main.programTime == Main.building.getSumOfTime())
							{
								Main.bIsPlayed = false;
							}
							if((int)(programTime/1000) != building.currentFrame && ((int)programTime != building.getSumOfTime()))
							{
								Platform.runLater(new Runnable() {
									@Override
									public void run()
									{
										building.currentFrame = (int)(programTime/1000);
										
										building.refresh();
									}
								});
							}
							try {
							Thread.sleep(1);
							}catch(Exception e)
							{
								throw e;
							}
						}while(Main.bIsPlayed == true);
						Main.play_stop.setBackground(new Background(new BackgroundImage(new Image("file:play.jpg"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
						return null;
					}
				};
				Main.timeSlider.valueProperty().bind(playin.valueProperty());
				Main.timeLabel.textProperty().bind(playin.messageProperty());
				
				playin.setOnSucceeded(e -> {
					Main.timeSlider.valueProperty().unbind();
					Main.timeLabel.textProperty().unbind();
					Main.timeLabel.setText(new String(Main.timeSlider.getValue() + "/" + Main.building.getSumOfTime()));
					Main.building.currentFrame = 0;
				});
				
				Thread myThread = new Thread(playin);
				myThread.setDaemon(true);
							
				myThread.start();
				
				play_stop.setBackground(new Background(new BackgroundImage(new Image("file:stop.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
				
				myThread.destroy();
			}
			System.out.println(new String(this.getClass().getName() + "clicked"));
		}
	}
}
