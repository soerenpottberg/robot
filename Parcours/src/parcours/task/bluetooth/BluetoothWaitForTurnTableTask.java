package parcours.task.bluetooth;

import java.io.IOException;

import parcours.bluetooth.TurnTableConnection;
import parcours.task.Task;

public class BluetoothWaitForTurnTableTask extends Task {

	private TurnTableConnection connection;

	public BluetoothWaitForTurnTableTask(TurnTableConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void init() {
	}

	@Override
	protected void control() {
		try {
			connection.receiveHello();
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
