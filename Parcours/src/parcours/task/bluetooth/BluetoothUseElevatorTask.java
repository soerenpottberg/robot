package parcours.task.bluetooth;

import java.io.IOException;

import parcours.bluetooth.ElevatorConnection;
import parcours.task.base.BluetoothTask;
import parcours.task.base.Task;

public class BluetoothUseElevatorTask extends BluetoothTask implements Task {

	private ElevatorConnection connection;

	public BluetoothUseElevatorTask(ElevatorConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void communicate() throws IOException {
		connection.sendDownCommand();
		connection.receiveOkay();
		awaitMovement();
	}

	private void awaitMovement() throws IOException {
		boolean isDown = false;
		do {
			connection.sendIsDownCommand();
		    isDown = connection.receiveBooleanCommand();
		} while(!isDown);
	}
}
