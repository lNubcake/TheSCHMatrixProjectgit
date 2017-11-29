package tools;

public class SCHBrushTool extends SCHTool
{
	boolean bWholeRect;
	
	public SCHBrushTool()
	{
		super();
		bWholeRect = false;
	}
	
	public void negateWholeRect()
	{
		bWholeRect = !bWholeRect;
	}
}
