package parcours.level.test;

import java.util.ArrayList;
import java.util.List;

import parcours.bluetooth.ElevatorConnection;
import parcours.level.base.Level;
import parcours.task.base.Task;
import parcours.task.bluetooth.BluetoothCloseConnectionTask;
import parcours.task.bluetooth.BluetoothConnectTask;
import parcours.task.bluetooth.BluetoothUseElevatorTask;
import parcours.task.bluetooth.BluetoothWaitForElevatorTask;
import parcours.task.debug.DebugTask;


public class TestElevatorBluetooth extends Level {
	
	@Override
	public String getLabel() {
		return "Bluetooth Lift";
	}

	@Override
	public List<Task> createTaskList() {
		final ElevatorConnection connection = new ElevatorConnection();
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(new BluetoothConnectTask(connection));
		taskList.add(new DebugTask("Connected"));
		taskList.add(new BluetoothWaitForElevatorTask(connection));
		taskList.add(new DebugTask("Waited"));
		taskList.add(new BluetoothUseElevatorTask(connection));
		taskList.add(new DebugTask("Used"));
		taskList.add(new BluetoothFinishElevatorTask(connection));
		taskList.add(new DebugTask("Finished"));
		taskList.add(new BluetoothCloseConnectionTask(connection));
		taskList.add(new DebugTask("Closed"));
		return taskList;
	}

}
