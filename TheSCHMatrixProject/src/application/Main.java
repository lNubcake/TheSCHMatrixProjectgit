package application;
	
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import toolbar.SCHBrush;
import userInterface.SCHBuilding;
import userInterface.SCHPalette;
import userInterface.SCHRect;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Main extends Application {
	
	static Scene scene;
	static GridPane root;
	static SCHPalette palette;
	static SCHBrush brush;
	static SCHBuilding building;
	
	@Override
	public void start(Stage primaryStage) {
		
		// This is the place to try out things in FXML
		/*
		Pane canvas = new Pane();
		canvas.setStyle("-fx-background-color: black;");
		canvas.setPrefSize(200, 200);
		Circle circle = new Circle(50, Color.RED);
		circle.relocate(20, 20);
		canvas.getChildren().add(circle);
		*/
		// And this is the place where the playground ends
		
		try {
			
			initRoot();
			initScene(primaryStage);
			
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
		
		System.out.println("x:" + primaryScreenBounds.Bounds.getMaxX() + "        " + "y" + primaryScreenBounds.Bounds.getMaxY());
		
		scene = new Scene(root,primaryScreenBounds.Bounds.getMaxX()-5,primaryScreenBounds.Bounds.getMaxY()-35);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		
		primaryStage.setX(-3);
		primaryStage.setY(0);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	/// This function initializes the root Pane and adds other Children to it.
	private void initRoot() throws Exception
	{
		root = new GridPane();
		root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		root.getStyleClass().add("debuglines");
		
		setUpConstraints();
		
		addChildrenToRoot();
		
		
	}
	
	private void setUpConstraints()
	{
		root.setHgap(primaryScreenBounds.Bounds.getMaxX()/192);
		//toolbar
		root.getColumnConstraints().add(new ColumnConstraints(primaryScreenBounds.Bounds.getMaxX()/64));
		root.getRowConstraints().add(new RowConstraints(primaryScreenBounds.Bounds.getMaxY()/(1040/30)));
		//SCHbuilding
		root.getColumnConstraints().add(new ColumnConstraints(primaryScreenBounds.Bounds.getMaxX()/(1920/730)));
		root.getRowConstraints().add(new RowConstraints(primaryScreenBounds.Bounds.getMaxY()/(1040/910)));
		//palette
		root.getColumnConstraints().add(new ColumnConstraints(primaryScreenBounds.Bounds.getMaxX()/(1920/890)));
	}
	
	/// This function sets up and adds all the elements to the UI
	private void addChildrenToRoot()
	{
		palette = new SCHPalette();
		brush = new SCHBrush();
		building = new SCHBuilding();
		
		root.add(brush, 0, 0);
		root.add(building.theBuilding, 1, 1);
		root.add(palette.palette, 2, 1);
	}
}
