package parcours.level.test;

import parcours.level.Level;
import parcours.task.Task;
import parcours.task.TravelHandleCollisionTask;


public class TestTravelLevel extends Level {
	
	private static int TASK_COUNT = 1;
	private static Task[] tasks = new Task[TASK_COUNT];
	
	static {
		int i = 0;
		tasks[i++] = new TravelHandleCollisionTask(100, 20);
	}

	@Override
	public String getLabel() {
		return "Test: Travel";
	}

	@Override
	public Task[] getTasks() {
		return tasks;
	}

}
