package parcours.level;

import parcours.task.FollowBridgeTask;
import parcours.task.Task;


public class Bridge extends Level {
	
	private static int TASK_COUNT = 1;
	private static Task[] tasks = new Task[TASK_COUNT];
	
	static {
		int i = 0;
		tasks[i++] = new FollowBridgeTask();
	}

	@Override
	public String getLabel() {
		return "Bridge";
	}

	@Override
	public Task[] getTasks() {
		return tasks;
	}

}
