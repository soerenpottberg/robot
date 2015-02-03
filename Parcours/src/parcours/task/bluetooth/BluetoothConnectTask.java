package parcours.task.bluetooth;

import parcours.bluetooth.BluetoothConnection;
import parcours.task.ControllerTask;

public class BluetoothConnectTask extends ControllerTask {

	private BluetoothConnection connection;

	public BluetoothConnectTask(BluetoothConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void init() {
	}

	@Override
	protected void control() {
		connection.establish();
	}

	@Override
	protected boolean abort() {
		return connection.isEstablished();
	}

	@Override
	protected void tearDown() {
	}

	

}
