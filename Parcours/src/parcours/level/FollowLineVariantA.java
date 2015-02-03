package parcours.level;

import parcours.level.base.Level;
import parcours.task.FindLineTask;
//import parcours.task.FollowLineTask;
//import parcours.task.FollowLineTaskAdaptive;
import parcours.task.FollowLineTaskConstTime;
import parcours.task.FollowLineTaskRaw;
import parcours.task.RightTurnTask;
import parcours.task.ControllerTask;
import parcours.task.TravelHandleCollisionTask;


public class FollowLineVariantA extends Level {
	
	private static final int BASE_SPEED = 20;
	private static final int INITIAL_TRAVEL_DISTANCE = 15; // used to clear the 3 lines at the beginning

	private static int TASK_COUNT = 5;
	private static ControllerTask[] tasks = new ControllerTask[TASK_COUNT];
	
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
		return "Follow Line (A)";
	}

	@Override
	public ControllerTask[] getTasks() {
		return tasks;
	}

}
