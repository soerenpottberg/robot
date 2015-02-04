package parcours.level.base;

import java.util.List;

import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.util.Delay;
import parcours.task.base.Task;

public abstract class Level {

	public abstract String getLabel();
	
	public final Task[] getTasks() {
		final List<Task> taskList = createTaskList();
		if(taskList == null) {
			return null;
		}
		Task[] tasks = new Task[taskList.size()];
		return taskList.toArray(tasks);
	}

	public abstract List<Task> createTaskList();

	public void run() {
		final Task[] tasks = getTasks();
		if (tasks == null) {
			showWarning("Level " + getLabel() + " contains no tasks.");
			return;
		}
		for (Task task : tasks) {
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
