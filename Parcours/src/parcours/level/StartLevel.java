package parcours.level;

import parcours.task.FollowRightLineTask;
import parcours.task.Task;


public class StartLevel extends Level {
	
	private static int TASK_COUNT = 1;
	private static Task[] tasks = new Task[TASK_COUNT];
	
	static {
		int i = 0;
		tasks[i++] = new FollowRightLineTask();
	}
	
	@Override
	public String getLabel() {
		return "Start Level";
	}

	@Override
	public Task[] getTasks() {
		return tasks;
	}


}
