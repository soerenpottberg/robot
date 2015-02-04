package parcours.task.bluetooth;

import java.io.IOException;

import parcours.bluetooth.GateConnection;
import parcours.task.base.NonCrucialBluetoothTask;
import parcours.task.base.Task;

public class BluetoothCloseGateTask extends NonCrucialBluetoothTask implements Task {

	private GateConnection connection;

	public BluetoothCloseGateTask(GateConnection connection) {
		this.connection = connection;
	}

	@Override
	protected void communicate() throws IOException {
		connection.sendClose();
		connection.receiveOkay();
	}

}
