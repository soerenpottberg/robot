package parcours.task.bluetooth;

import java.io.IOException;

import parcours.bluetooth.ElevatorConnection;
import parcours.task.base.BluetoothTask;

public class BluetoothWaitForElevatorTask extends BluetoothTask {

	private ElevatorConnection connection;

	public BluetoothWaitForElevatorTask(ElevatorConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void communicate() throws IOException {
		boolean isDown = false;
		do {
			connection.sendIsReadyCommand();
			isDown = connection.receiveBooleanCommand();
		} while (!isDown);
	}

}
