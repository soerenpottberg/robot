package parcours.task.bluetooth;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.comm.Bluetooth;
import parcours.bluetooth.BluetoothConnection;
import parcours.task.Task;

public class BluetoothConnectTask extends Task {

	private BluetoothConnection connection;
	private RemoteDevice device;

	public BluetoothConnectTask(BluetoothConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void init() {
		String deviceName = "TurnTable";
		device = lookupDevice(deviceName);
	}

	@Override
	protected void control() {
		connection.establish(device);
	}

	@Override
	protected boolean abort() {
		return connection.isEstablished();
	}

	@Override
	protected void tearDown() {
	}

	private RemoteDevice lookupDevice(String deviceName) {
		RemoteDevice device = Bluetooth.getKnownDevice(deviceName);
		if (device == null) {
			System.out.println("unknown device" + deviceName);
			System.out.println("cannot connect to TurnTable");
		}
		return device;
	}

}
