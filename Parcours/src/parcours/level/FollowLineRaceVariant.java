package parcours.level;

import java.util.ArrayList;
import java.util.List;

import parcours.level.base.Level;
import parcours.task.FindLineTask;
import parcours.task.FollowLineSpeedTask;
import parcours.task.RightTurnTask;
import parcours.task.TravelHandleCollisionTask;
import parcours.task.base.Task;

public class FollowLineRaceVariant extends Level {

	private static final int BASE_SPEED = 20;
	private static final int INITIAL_TRAVEL_DISTANCE = 15; // used to clear the
															// 3 lines at the
															// beginning

	@Override
	public String getLabel() {
		return "Follow Line (R)";
	}

	@Override
	public List<Task> createTaskList() {
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(new TravelHandleCollisionTask(INITIAL_TRAVEL_DISTANCE,
				BASE_SPEED));
		taskList.add(new FindLineTask()); // Find line to follow
		taskList.add(new FollowLineSpeedTask());
		taskList.add(new RightTurnTask());
		taskList.add(new FindLineTask()); // Find line of next level
		return taskList;
	}

}
