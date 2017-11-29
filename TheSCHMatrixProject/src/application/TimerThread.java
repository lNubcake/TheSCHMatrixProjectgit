package application;
import application.Main;

public class TimerThread extends Thread
{
	@Override
	public void run()
	{
		while(true)
		{
		if(Main.timeSlider.valueChangingProperty().get())
		{
			Main.programTime = Main.timeSlider.getValue();
			Main.timeLabel.setText(new String(Main.programTime + "/" + Main.building.getSumOfTime()));
		}
		}
	}
}
