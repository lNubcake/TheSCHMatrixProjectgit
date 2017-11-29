package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import toolbar.SCHBrush;
import tools.SCHBrushTool;
import tools.SCHTool;
import userInterface.SCHBuilding;
import userInterface.SCHColor;
import userInterface.SCHPalette;
import userInterface.SCHRect;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Main extends Application {
	
	// this variable holds the File in which you are working in
	static File fileOutput;
	// Helps to pass the current stage to sub-classes
	static Stage stageToPass;
	
	static Scene scene;
	// Contains every element of the GUI
	static GridPane GUI;
	// GUI + MenuBar
	static GridPane root;
	// GUI elements
	static SCHPalette palette;
	static SCHBrush brush;
	static SCHBuilding building;
	static SCHTool currentTool;
	static MenuBar menuBar;
	static Slider timeSlider;
	static Label timeLabel;
	static Thread sliderTick;
	static HBox frameController;
	
	static GridPane test;
	
	static double programTime;
	
	@Override
	public void start(Stage primaryStage) {
		
		stageToPass = primaryStage;
		
		try {
			
			currentTool = new SCHBrushTool();
			programTime = 0;
			
			initGUI();
			initRoot();
			initScene(primaryStage);
			
			//TODO find it out later when you slept
			/*Task task = new Task<Void>() {
			    @Override public Void call() {
			        while(!isCancelled()) 
			        {
			        	if(timeSlider.valueChangingProperty().get())
			        	{
			        		programTime = timeSlider.getValue();
			    			
			        		//timeLabel.setText(null);
			        		System.out.println(programTime);
			        	}
			        	timeLabel.setText(new String(programTime + "/" + building.getSumOfTime()));
			        }
			        return null;
			    }
			};
			sliderTick = new Thread(task);
			sliderTick.start();*/
			
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
		
		// TODO refactor
		GUI.add(test, 1, 3);
		
		GUI.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		test.getStyleClass().add("framecontrollersheet");
		
		//TODO refactor
		Button addFrame = new Button();
		addFrame.setBackground(new Background(new BackgroundImage(new Image("file:addframe.jpg"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
		addFrame.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event event)
			{
				Main.building.addFrame();
			}
		});
		
		Button deleteFrame = new Button();
		addFrame.setBackground(new Background(new BackgroundImage(new Image("file:addframe.jpg"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
		
		HBox frameButtonBox = new HBox();
		frameButtonBox.getChildren().addAll(addFrame,deleteFrame);
		frameButtonBox.setSpacing(5);
		
		HBox timeLabelWithframeButtonBox = new HBox();
		timeLabelWithframeButtonBox.getChildren().addAll(timeLabel,frameButtonBox);
		timeLabelWithframeButtonBox.setSpacing(50);
		timeLabelWithframeButtonBox.setAlignment(Pos.BOTTOM_LEFT);
		GUI.add(timeLabelWithframeButtonBox, 2,2);
		
		
		
	}
	
	/// This function creates the menuBar
	private void createMenu()
	{
		menuBar = new MenuBar();
		
		// TODO finish this one
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
	public void initFrameController()
	{
		frameController = new HBox();
		frameController.setSpacing(30);
		
		test = new GridPane();
		
		Button first = new Button();
		
		first.setVisible(true);first.setBackground(new Background(new BackgroundImage(new Image("file:first.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
		first.setMinSize(20, 20);
		first.setOnMouseClicked(new firstEventHandler());
		
		Button stepLeft = new Button();
		stepLeft.setBackground(new Background(new BackgroundImage(new Image("file:arrow_left.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
		stepLeft.setVisible(true);
		stepLeft.setMinSize(20, 20);
		stepLeft.setOnMouseClicked(new stepLeftEventHandler());
		
		Button play_stop = new Button();
		play_stop.setBackground(new Background(new BackgroundImage(new Image("file:play.jpg"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
		play_stop.setVisible(true);
		play_stop.setMinSize(25, 25);
		play_stop.setOnMouseClicked(new play_stopEventHandler());
		
		Button stepRight = new Button();
		stepRight.setBackground(new Background(new BackgroundImage(new Image("file:arrow_right.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
		stepRight.setVisible(true);
		stepRight.setMinSize(15, 15);
		stepRight.setOnMouseClicked(new stepRightEventHandler());
		
		Button last = new Button();
		last.setBackground(new Background(new BackgroundImage(new Image("file:last.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
		last.setVisible(true);
		last.setMinSize(15, 15);
		last.setOnMouseClicked(new lastEventHandler());
		
		frameController.getChildren().addAll(first,stepLeft,play_stop,stepRight,last);
		test.add(frameController,0,0);
		
	}
	
	/// This EventHandler implementer class is called when somebody clicks on menu: File->save
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
	
	class stepLeftEventHandler implements EventHandler
	{
		@Override
		public void handle(Event event)
		{
			if(building.currentFrame > 0)
			{
				building.currentFrame -= 1;
				building.refresh();
				System.out.println(new String(this.getClass().getName() + "clicked"));
			}
		}
	}
	
	class stepRightEventHandler implements EventHandler
	{
		@Override
		public void handle(Event event)
		{
			if(building.currentFrame < building.frameContainer.size()-1)
			{
				building.currentFrame += 1;
				building.refresh();
				System.out.println(new String(this.getClass().getName() + "clicked"));
			}
		}
	}
	
	class firstEventHandler implements EventHandler
	{
		@Override
		public void handle(Event event)
		{
				building.currentFrame = 0;
				building.refresh();
				System.out.println(new String(this.getClass().getName() + "clicked"));
		}
	}
	
	class lastEventHandler implements EventHandler
	{
		@Override
		public void handle(Event event)
		{
				building.currentFrame = building.frameContainer.size()-1;
				building.refresh();
				System.out.println(new String(this.getClass().getName() + "clicked"));
		}
	}
	
	class play_stopEventHandler implements EventHandler
	{
		@Override
		public void handle(Event event)
		{
				// TODO implement later
			System.out.println(new String(this.getClass().getName() + "clicked"));
		}
	}
}
