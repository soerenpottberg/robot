package parcours.level;

import java.util.ArrayList;
import java.util.List;

import parcours.level.base.Level;
import parcours.task.FindBridgeEdgeTask;
import parcours.task.FindLineTask;
import parcours.task.FollowBridgeSuspensionTask;
import parcours.task.FollowLineStraightAbortLongDistanceTask;
import parcours.task.base.Task;


public class SuspensionBridge extends Level {
	
	private static final int BASE_SPEED = 8;
	private static final int ACCELERATION = 10;
	
	@Override
	public String getLabel() {
		return "SuspensionBridge";
	}

	@Override
	public List<Task> createTaskList() {
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(new FindLineTask(false));
		taskList.add(new FollowLineStraightAbortLongDistanceTask());
		taskList.add(new FindBridgeEdgeTask(BASE_SPEED, ACCELERATION));
		taskList.add(new FollowBridgeSuspensionTask());
		taskList.add(new FindLineTask());
		return taskList;
	}

}
