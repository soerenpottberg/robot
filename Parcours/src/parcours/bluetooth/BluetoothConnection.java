package parcours.bluetooth;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class BluetoothConnection {

	private BTConnection connection;
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;

	public void establish(RemoteDevice device) {
		connection = Bluetooth.connect(device);
		if(connection == null) {
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

	public void close() {
		connection.close();
		connection = null;
		dataInputStream = null;
		dataOutputStream = null;
	}

}
