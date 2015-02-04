package parcours.task.bluetooth;

import java.io.IOException;

import parcours.bluetooth.TurnTableConnection;
import parcours.task.base.BluetoothTask;

public class BluetoothTurnTurnTableTask extends BluetoothTask {

	private TurnTableConnection connection;

	public BluetoothTurnTurnTableTask(TurnTableConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void communicate() throws IOException {
		connection.sendTurnCommand();
		connection.receiveOkay();
	}

}
