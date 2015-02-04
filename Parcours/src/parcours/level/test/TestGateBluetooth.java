package parcours.level.test;

import java.util.ArrayList;
import java.util.List;

import parcours.bluetooth.GateConnection;
import parcours.level.base.Level;
import parcours.task.base.Task;
import parcours.task.bluetooth.BluetoothCloseConnectionTask;
import parcours.task.bluetooth.BluetoothCloseGateTask;
import parcours.task.bluetooth.BluetoothConnectTask;


public class TestGateBluetooth extends Level {
	
	@Override
	public String getLabel() {
		return "Bluetooth Gate";
	}

	@Override
	public List<Task> createTaskList() {
		final GateConnection connection = new GateConnection();
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(new BluetoothConnectTask(connection));
		taskList.add(new BluetoothCloseGateTask(connection));
		taskList.add(new BluetoothCloseConnectionTask(connection));
		return taskList;
	}

}
