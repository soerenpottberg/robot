package parcours.level;

import java.util.ArrayList;
import java.util.List;

import parcours.level.base.Level;
import parcours.task.FindBridgeEdgeTask;
import parcours.task.FollowBridgeTask;
import parcours.task.TravelHandleCollisionTask;
import parcours.task.base.Task;


public class Bridge extends Level {
	
	private static final int INITIAL_TRAVEL_DISTANCE = 30; // used to clear the 3 lines at the beginning
	
	@Override
	public String getLabel() {
		return "Bridge";
	}

	@Override
	public List<Task> createTaskList() {
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(new TravelHandleCollisionTask(INITIAL_TRAVEL_DISTANCE, 40));
		taskList.add(new FindBridgeEdgeTask());
		taskList.add(new FollowBridgeTask());
		return taskList;
	}

}
