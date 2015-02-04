package parcours.level;

import java.util.ArrayList;
import java.util.List;

import parcours.bluetooth.ElevatorConnection;
import parcours.level.base.Level;
import parcours.task.GoIntoElevatorTask;
import parcours.task.TravelHandleCollisionTask;
import parcours.task.base.Task;
import parcours.task.bluetooth.BluetoothCloseConnectionTask;
import parcours.task.bluetooth.BluetoothConnectTask;
import parcours.task.bluetooth.BluetoothFinishElevatorTask;
import parcours.task.bluetooth.BluetoothUseElevatorTask;
import parcours.task.bluetooth.BluetoothWaitForElevatorTask;


public class Elevator extends Level {

	private static final int DISTANCE = 50;
	private static final int SPEED = 20;

	@Override
	public String getLabel() {
		return "Elevator";
	}

	@Override
	public List<Task> createTaskList() {
		final ElevatorConnection connection = new ElevatorConnection();
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(new BluetoothConnectTask(connection));
		taskList.add(new BluetoothWaitForElevatorTask(connection));
		taskList.add(new GoIntoElevatorTask());
		taskList.add(new BluetoothUseElevatorTask(connection));
		taskList.add(new TravelHandleCollisionTask(DISTANCE, SPEED));
		taskList.add(new BluetoothFinishElevatorTask(connection));
		taskList.add(new BluetoothCloseConnectionTask(connection));
		return taskList;
	}

}
