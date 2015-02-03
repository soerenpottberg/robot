package parcours.task.bluetooth;

import java.io.IOException;

import parcours.bluetooth.TurnTableConnection;

public class BluetoothFinishTurnTableTask extends BluetoothTask {

	private TurnTableConnection connection;

	public BluetoothFinishTurnTableTask(TurnTableConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void communicate() throws IOException {
		connection.sendCyaCommand();
	}

}
