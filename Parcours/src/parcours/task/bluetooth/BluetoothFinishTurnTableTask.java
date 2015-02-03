package parcours.task.bluetooth;

import java.io.IOException;

import parcours.bluetooth.TurnTableConnection;
import parcours.task.Task;

public class BluetoothFinishTurnTableTask extends Task {

	private TurnTableConnection connection;

	public BluetoothFinishTurnTableTask(TurnTableConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void init() {
	}

	@Override
	protected void control() {
		try {
			connection.sendCyaCommand();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
