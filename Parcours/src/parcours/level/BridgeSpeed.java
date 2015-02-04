package parcours.level;

import java.util.ArrayList;
import java.util.List;

import parcours.level.base.Level;
import parcours.task.FindBridgeEdgeTask;
import parcours.task.FollowBridgeSpeedTask;
import parcours.task.GoInFrontOfElevatorTask;
import parcours.task.TravelHandleCollisionTask;
import parcours.task.base.Task;


public class BridgeSpeed extends Level {
	
	private static final int INITIAL_TRAVEL_DISTANCE = 55; // used to clear the 3 lines at the beginning
	
	@Override
	public String getLabel() {
		return "Bridge Speed";
	}

	@Override
	public List<Task> createTaskList() {
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(new TravelHandleCollisionTask(INITIAL_TRAVEL_DISTANCE, 40));
		taskList.add(new FindBridgeEdgeTask());
		taskList.add(new FollowBridgeSpeedTask());
		taskList.add(new GoInFrontOfElevatorTask());
		return taskList;
	}

}
