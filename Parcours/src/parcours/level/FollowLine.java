package parcours.level;

import parcours.task.FindLineTask;
//import parcours.task.FollowLineTask;
//import parcours.task.FollowLineTaskAdaptive;
import parcours.task.FollowLineTaskConstTime;
import parcours.task.FollowLineTaskRaw;
import parcours.task.RightTurnTask;
import parcours.task.Task;
import parcours.task.TravelHandleCollisionTask;


public class FollowLine extends Level {
	
	private static final int BASE_SPEED = 20;
	private static final int INITIAL_TRAVEL_DISTANCE = 15; // used to clear the 3 lines at the beginning

	private static int TASK_COUNT = 5;
	private static Task[] tasks = new Task[TASK_COUNT];
	
	static {
		int i = 0;
		tasks[i++] = new TravelHandleCollisionTask(INITIAL_TRAVEL_DISTANCE, BASE_SPEED);
		tasks[i++] = new FindLineTask(); // Find line to follow
		tasks[i++] = new FollowLineTaskRaw();
		tasks[i++] = new RightTurnTask();
		tasks[i++] = new FindLineTask(); // Find line of next level
		//tasks[i++] = new FollowLineTaskConstTime();
		//tasks[i++] = new FollowLineTaskAdaptive();
	}
	
	@Override
	public String getLabel() {
		return "Follow Line";
	}

	@Override
	public Task[] getTasks() {
		return tasks;
	}

}
