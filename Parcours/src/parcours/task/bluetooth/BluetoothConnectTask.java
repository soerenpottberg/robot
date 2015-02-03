package parcours.task.bluetooth;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.comm.Bluetooth;
import parcours.bluetooth.BluetoothConnection;
import parcours.task.Task;

public class BluetoothConnectTask extends Task {

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
