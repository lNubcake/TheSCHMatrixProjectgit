import org.junit.Assert;

import org.junit.Test;

import userInterface.SCHRect;

public class SCHRectTest {

	@Test
	public void numberTest()
	{
		SCHRect myRect = new SCHRect();
		Assert.assertEquals(4, myRect.thePoints.size(),0);
	}
}
