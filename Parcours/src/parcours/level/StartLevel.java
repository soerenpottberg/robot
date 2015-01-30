package parcours.level;

import parcours.task.FollowRightWallTask;
import parcours.task.FollowRightWallTaskStateFull;
import parcours.task.Task;


public class StartLevel extends Level {
	
	private static int TASK_COUNT = 1;
	private static Task[] tasks = new Task[TASK_COUNT];
	
	static {
		int i = 0;
		tasks[i++] = new FollowRightWallTaskStateFull();
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
