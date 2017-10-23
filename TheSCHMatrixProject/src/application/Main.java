package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import toolbar.SCHBrush;
import userInterface.SCHBuilding;
import userInterface.SCHPalette;
import userInterface.SCHRect;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Main extends Application {
	
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
			SCHPalette palette = new SCHPalette();
			SCHBrush brush = new SCHBrush();
			SCHBuilding building = new SCHBuilding();
			//SCHRect rect = new SCHRect();
			BorderPane root = new BorderPane();
			// TODO make GridPane instead
			//root.getChildren().add(palette.palette);
			root.getChildren().add(building.theBuilding);
			//root.getChildren().add(building.theBuilding);
			//root.setCenter(building.theBuilding);
			//root.getChildren().add(rect.theRect);
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
