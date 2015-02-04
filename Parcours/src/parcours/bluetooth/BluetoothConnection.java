package parcours.bluetooth;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public abstract class BluetoothConnection {

	private BTConnection connection;
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;
	private RemoteDevice device;

	public BluetoothConnection(String name) {
		this.device = lookupDevice(name);
	}

	public void establish() {
		connection = Bluetooth.connect(device);
		if (connection == null) {
			return;
		}
		dataOutputStream = connection.openDataOutputStream();
		dataInputStream = connection.openDataInputStream();
	}

	public boolean isEstablished() {
		return connection != null;
	}

	public int receiveIntegerCommand() throws IOException {
		int command = dataInputStream.readInt();
		System.out.println("Receive:" + command);
		return command;
	}

	public void sendIntegerCommand(int command) throws IOException {
		dataOutputStream.writeInt(command);
		dataOutputStream.flush();
		System.out.println("Send: " + command);
	}
	
	public boolean receiveBooleanCommand() throws IOException {
		boolean command = dataInputStream.readBoolean();
		System.out.println("Receive:" + command);
		return command;
	}
	
	public void sendBooleanCommand(boolean command) throws IOException {
		dataOutputStream.writeBoolean(command);
		dataOutputStream.flush();
		System.out.println("Send: " + command);
	}

	public void close() {
		connection.close();
		connection = null;
		dataInputStream = null;
		dataOutputStream = null;
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
