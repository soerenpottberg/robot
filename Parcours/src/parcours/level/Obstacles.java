package parcours.level;

import java.util.ArrayList;
import java.util.List;

import parcours.level.base.Level;
import parcours.task.FindLineTask;
import parcours.task.FollowBridgeTask;
import parcours.task.FollowLineStraightTask;
import parcours.task.FollowLineTaskRaw;
import parcours.task.TraversOpenedDoorTask;
import parcours.task.base.Task;
import parcours.task.bluetooth.BluetoothCloseDoorTask;
import parcours.task.bluetooth.BluetoothOpenDoorTask;


public class Obstacles extends Level {
	
	@Override
	public String getLabel() {
		return "Obstacles";
	}

	@Override
	public List<Task> createTaskList() {
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(new BluetoothOpenDoorTask());
		taskList.add(new TraversOpenedDoorTask());
		taskList.add(new BluetoothCloseDoorTask());
		taskList.add(new FindLineTask(false));
		taskList.add(new FollowLineStraightTask());
		taskList.add(new FollowBridgeTask());
		taskList.add(new FindLineTask());
		taskList.add(new FollowLineTaskRaw());
		return taskList;
	}

}
