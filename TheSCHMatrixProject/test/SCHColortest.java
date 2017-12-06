import org.junit.Assert;

import org.junit.Test;

import javafx.scene.paint.Color;

import userInterface.SCHColor;

public class SCHColortest {

	@Test
	public void colorToHex()
	{
		Assert.assertEquals(SCHColor.colorToHex(Color.BLACK), "0");
	}

}
