package parcours.level.test;

import java.util.ArrayList;

import parcours.bluetooth.TurnTableConnection;
import parcours.level.base.Level;
import parcours.task.Task;
import parcours.task.bluetooth.BluetoothCloseConnectionTask;
import parcours.task.bluetooth.BluetoothConnectTask;
import parcours.task.bluetooth.BluetoothFinishTurnTableTask;
import parcours.task.bluetooth.BluetoothTurnTurnTableTask;
import parcours.task.bluetooth.BluetoothWaitForTurnTableTask;


public class TestTurnTableBluetooth extends Level {
	
	@Override
	public String getLabel() {
		return "Bluetooth Turn";
	}

	@Override
	public Task[] getTasks() {
		final TurnTableConnection connection = new TurnTableConnection();
		final ArrayList<Task> taskList = new ArrayList<Task>();
		taskList.add(new BluetoothConnectTask(connection));
		taskList.add(new BluetoothWaitForTurnTableTask(connection));
		taskList.add(new BluetoothTurnTurnTableTask(connection));
		taskList.add(new BluetoothFinishTurnTableTask(connection));
		taskList.add(new BluetoothCloseConnectionTask(connection));
		Task[] tasks = new Task[taskList.size()];
		return taskList.toArray(tasks);
	}

}
