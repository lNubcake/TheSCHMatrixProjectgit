import org.junit.Assert;

import org.junit.Test;

import toolbar.SCHBrush;

public class SCHBrushtTest {

	@Test
	public void testMinWidth()
	{
		SCHBrush tested = new SCHBrush();
		Assert.assertEquals(30.0, tested.getMinWidth(),0);
	}
	
	@Test
	public void testMinHeight()
	{
		SCHBrush tested = new SCHBrush();
		Assert.assertEquals(30.0, tested.getMinHeight(),0);
	}
}
