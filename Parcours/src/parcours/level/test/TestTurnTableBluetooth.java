package parcours.level.test;

import parcours.bluetooth.TurnTableConnection;
import parcours.level.base.Level;
import parcours.task.Task;
import parcours.task.bluetooth.BluetoothCloseConnectionTask;
import parcours.task.bluetooth.BluetoothConnectTask;
import parcours.task.bluetooth.BluetoothFinishTurnTableTask;
import parcours.task.bluetooth.BluetoothTurnTurnTableTask;
import parcours.task.bluetooth.BluetoothWaitForTurnTableTask;


public class TestTurnTableBluetooth extends Level {
	
	private static final int TASK_COUNT = 5;
	private static final Task[] TASKS = new Task[TASK_COUNT];
	static final TurnTableConnection TURN_TABLE_CONNECTION = new TurnTableConnection();
	
	static {
		int i = 0;
		TASKS[i++] = new BluetoothConnectTask(TURN_TABLE_CONNECTION);
		TASKS[i++] = new BluetoothWaitForTurnTableTask(TURN_TABLE_CONNECTION);
		TASKS[i++] = new BluetoothTurnTurnTableTask(TURN_TABLE_CONNECTION);
		TASKS[i++] = new BluetoothFinishTurnTableTask(TURN_TABLE_CONNECTION);
		TASKS[i++] = new BluetoothCloseConnectionTask(TURN_TABLE_CONNECTION);
	}

	@Override
	public String getLabel() {
		return "Bluetooth Turn";
	}

	@Override
	public Task[] getTasks() {
		return TASKS;
	}

}
