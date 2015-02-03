package parcours.task.bluetooth;

import java.io.IOException;

import parcours.bluetooth.TurnTableConnection;

public class BluetoothWaitForTurnTableTask extends BluetoothTask {

	private TurnTableConnection connection;

	public BluetoothWaitForTurnTableTask(TurnTableConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void communicate() throws IOException {
		connection.receiveHello();
	}

}
