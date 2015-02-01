package parcours.level;

import parcours.task.FindLineTask;
//import parcours.task.FollowLineTask;
//import parcours.task.FollowLineTaskAdaptive;
import parcours.task.FollowLineTaskConstTime;
import parcours.task.Task;


public class FollowLine extends Level {

	private static int TASK_COUNT = 2;
	private static Task[] tasks = new Task[TASK_COUNT];
	
	static {
		int i = 0;
		tasks[i++] = new FindLineTask();
		tasks[i++] = new FollowLineTaskConstTime();
		//tasks[i++] = new FollowLineTaskAdaptive();
	}
	
	@Override
	public String getLabel() {
		return "Follow Line";
	}

	@Override
	public Task[] getTasks() {
		// TODO Auto-generated method stub
		return tasks;
	}

}
