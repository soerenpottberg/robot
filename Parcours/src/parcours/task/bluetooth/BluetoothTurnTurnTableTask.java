package parcours.task.bluetooth;

import java.io.IOException;

import parcours.bluetooth.TurnTableConnection;
import parcours.task.Task;

public class BluetoothTurnTurnTableTask extends Task {

	private TurnTableConnection connection;

	public BluetoothTurnTurnTableTask(TurnTableConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void init() {
	}

	@Override
	protected void control() {
		try {
			connection.sendTurnCommand();
			connection.receiveOkay();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected boolean abort() {
		return true;
	}

	@Override
	protected void tearDown() {
	}

}
