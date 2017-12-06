import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Assert;


import application.primaryScreenBounds;
import javafx.stage.Screen;

public class primaryScreenBoundsTest {
	
	@Test
	public void testWidth() 
	{
		double actualWidth = Screen.getPrimary().getVisualBounds().getWidth();
		Assert.assertEquals(primaryScreenBounds.Bounds.getWidth(),actualWidth,0);
	}
	
	@Test
	public void testHeight()
	{
		double actualHeight = Screen.getPrimary().getVisualBounds().getHeight();
		Assert.assertEquals(primaryScreenBounds.Bounds.getHeight(), actualHeight,0);
	}

}
