package parcours.bluetooth;

import java.io.IOException;

public class GateConnection extends BluetoothConnection {

	private static final String BRICK_NAME = "TestName"; // TestName is the
															// actual name

	public GateConnection() {
		super(BRICK_NAME);
	}

	public void sendClose() throws IOException {
		sendBooleanCommand(true);
	}

	public void receiveOkay() throws IOException {
		boolean command = receiveBooleanCommand();
		assertCommand(command);
	}

	private void assertCommand(boolean command) throws IOException {
		if (!command) {
			System.out.println("Invalid command:");
			System.out.println("Expected: true");
			throw new IOException("Invalid Command");
		}
	}

}
