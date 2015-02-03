package parcours.level;

import parcours.level.base.Level;
import parcours.task.BluetoothCloseDoorTask;
import parcours.task.BluetoothOpenDoorTask;
import parcours.task.FindLineTask;
import parcours.task.FollowBridgeTask;
import parcours.task.FollowLineTaskRaw;
import parcours.task.FollowLineStraightTask;
import parcours.task.Task;
import parcours.task.TraversOpenedDoorTask;


public class Obstacles extends Level {
	
	private static int TASK_COUNT = 8;
	private static Task[] tasks = new Task[TASK_COUNT];
	
	static {
		int i = 0;
		tasks[i++] = new BluetoothOpenDoorTask();
		tasks[i++] = new TraversOpenedDoorTask();
		tasks[i++] = new BluetoothCloseDoorTask();
		tasks[i++] = new FindLineTask( false );
		tasks[i++] = new FollowLineStraightTask();
		tasks[i++] = new FollowBridgeTask();
		tasks[i++] = new FindLineTask( false );
		tasks[i++] = new FollowLineTaskRaw();
	}

	@Override
	public String getLabel() {
		return "Obstacles";
	}

	@Override
	public Task[] getTasks() {
		return tasks;
	}

}
