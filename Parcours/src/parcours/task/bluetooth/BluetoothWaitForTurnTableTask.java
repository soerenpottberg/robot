package parcours.task.bluetooth;

import java.io.IOException;

import lejos.nxt.Sound;

import parcours.bluetooth.TurnTableConnection;
import parcours.task.base.BluetoothTask;

public class BluetoothWaitForTurnTableTask extends BluetoothTask {

	private TurnTableConnection connection;

	public BluetoothWaitForTurnTableTask(TurnTableConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void communicate() throws IOException {
		Sound.beep();
		Sound.beepSequence();
		Sound.beepSequenceUp();
		connection.receiveHello();
	}

}
