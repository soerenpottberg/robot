package parcours.level;

import parcours.task.FollowRightLineTask;
import parcours.task.Task;


public class StartLevel extends Level {
	
	private static Task[] tasks;
	
	static {
		tasks = new Task[1];
		tasks[0] = new FollowRightLineTask();
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
