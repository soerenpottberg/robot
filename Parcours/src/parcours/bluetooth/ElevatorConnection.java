package parcours.bluetooth;

import java.io.IOException;

public class ElevatorConnection extends BluetoothConnection {

	private static final String BRICK_NAME = "Lift";

	public ElevatorConnection() {
		super(BRICK_NAME);
	}

	public enum ElevatorCommand {
		GO_DOWN, IS_DOWN, CLOSE_CONNECTION, UNKNOWN;

		public static ElevatorCommand getByOrdinal(int commandOrdinal) {
			if (commandOrdinal >= values().length) {
				return UNKNOWN;
			}
			return values()[commandOrdinal];
		}
	}

	public void sendDownCommand() throws IOException {
		sendCommand(ElevatorCommand.GO_DOWN);
	}

	public void sendIsDownCommand() throws IOException {
		sendCommand(ElevatorCommand.IS_DOWN);
	}

	public void receiveOkay() throws IOException {
		boolean command = receiveBooleanCommand();
		assertCommand(command);
	}

	public void sendCyaCommand() throws IOException {
		sendCommand(ElevatorCommand.CLOSE_CONNECTION);
	}

	private void assertCommand(boolean command) throws IOException {
		if (!command) {
			System.out.println("Invalid command:");
			System.out.println("Expected: true");
			throw new IOException("Invalid Command");
		}
	}

	private void sendCommand(ElevatorCommand command) throws IOException {
		sendIntegerCommand(command.ordinal());
		System.out.println("Send: " + command.name());
	}

}
