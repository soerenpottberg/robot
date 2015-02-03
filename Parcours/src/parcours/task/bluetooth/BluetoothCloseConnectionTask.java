package parcours.task.bluetooth;

import parcours.bluetooth.BluetoothConnection;
import parcours.task.ControllerTask;

public class BluetoothCloseConnectionTask extends ControllerTask {

	private BluetoothConnection connection;

	public BluetoothCloseConnectionTask(BluetoothConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void init() {
		connection.close();
	}

	@Override
	protected void control() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean abort() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void tearDown() {
		// TODO Auto-generated method stub

	}

}
