package parcours.level.test;

import java.io.IOException;

import parcours.bluetooth.ElevatorConnection;
import parcours.task.base.NonCrucialBluetoothTask;
import parcours.task.base.Task;

public class BluetoothFinishElevatorTask extends NonCrucialBluetoothTask
		implements Task {

	private ElevatorConnection connection;

	public BluetoothFinishElevatorTask(ElevatorConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void communicate() throws IOException {
		connection.sendCyaCommand();
	}

}
