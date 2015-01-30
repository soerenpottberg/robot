package parcours.level;

import lejos.util.Delay;
import parcours.task.Task;

public abstract class Level {

	public abstract String getLabel();

	public abstract Task[] getTasks();

	public void run() {
		if (getTasks() == null) {
			System.out.println("Level " + getLabel() + " contains no tasks.");
			Delay.msDelay(1000);
			return;
		}
		for (Task task : getTasks()) {
			task.run();
		}
	}

}
