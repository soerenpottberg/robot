package parcours.level;

import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.util.Delay;
import parcours.task.Task;

public abstract class Level {

	public abstract String getLabel();

	public abstract Task[] getTasks();

	public void run() {
		if (getTasks() == null) {
			showWarning("Level " + getLabel() + " contains no tasks.");
			return;
		}
		for (Task task : getTasks()) {
			task.run();
		}
		Sound.beepSequenceUp();
	}

	private void showWarning(String message) {
		LCD.clear();
		System.out.println(message);
		Delay.msDelay(1000);
		LCD.clear();
	}

}
