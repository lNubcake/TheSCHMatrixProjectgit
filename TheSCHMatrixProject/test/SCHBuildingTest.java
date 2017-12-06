import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;
import userInterface.SCHBuilding;

public class SCHBuildingTest {

	SCHBuilding tested;
	
	@Before
	public void setUp()
	{
		tested = new SCHBuilding();
	}
	
	@Test
	public void testcurrentFrame()
	{
		Assert.assertEquals(0, tested.currentFrame,0);
	}
	
	@Test
	public void testgetSumOfTime()
	{
		Assert.assertEquals(1000, tested.getSumOfTime(),0);
	}
	
	@Test
	public void addFrameTest()
	{
		tested.addFrame();
		Assert.assertEquals(1,tested.frameContainer.size()-1,0);
	}
	
	@Test
	public void deleteFrameTest()
	{
		tested.addFrame();
		tested.deleteFrame();
		Assert.assertEquals(1, tested.frameContainer.size()-1,0);
	}
}
