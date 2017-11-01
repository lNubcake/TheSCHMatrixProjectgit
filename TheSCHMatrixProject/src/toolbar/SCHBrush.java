package toolbar;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;

public class SCHBrush extends Button
{
	public SCHBrush()
	{
		super();
		this.setVisible(true);
		this.setPrefSize(30, 30);
		// TODO photoshop this image
		this.setBackground(new Background(new BackgroundImage(new Image("file:Brush.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,new BackgroundSize(30,30,false,false,true,false))));
	}
}
