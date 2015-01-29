package turntable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.Motor;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.robotics.navigation.DifferentialPilot;

public class TurnTable {

	private enum TurnTableCommand {
		HELLO, TURN, DONE, CYA, UNKNOWN;

		public static TurnTableCommand getByOrdinal(int commandOrdinal) {
			if (commandOrdinal >= values().length) {
				return UNKNOWN;
			}
			return values()[commandOrdinal];
		}
	}

	public static void main(String[] args) {
		TurnTable turnTable = new TurnTable();
		turnTable.use();
	}

	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;

	private void use() {
		// TODO Auto-generated method stub

		String deviceName = "TurnTable";
		RemoteDevice device = lookupDevice(deviceName);
		BTConnection connection = Bluetooth.connect(device);
		try {
			dataOutputStream = connection.openDataOutputStream();
			dataInputStream = connection.openDataInputStream();

			TurnTableCommand command = receiveCommand();
			assertCommand(command, TurnTableCommand.HELLO);

			// drive forward
			DifferentialPilot pilot = new DifferentialPilot(8.16, 13, Motor.B,
					Motor.A);
			pilot.travel(100);

			sendCommand(TurnTableCommand.TURN);

			command = receiveCommand();
			assertCommand(command, TurnTableCommand.DONE);

			// drive backward
			pilot.travel(-100);

			sendCommand(TurnTableCommand.CYA);

		} catch (IOException e) {
			System.out.println("IOException");
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private RemoteDevice lookupDevice(String deviceName) {
		RemoteDevice device = Bluetooth.getKnownDevice(deviceName);
		if (device == null) {
			log("unknown device" + deviceName);
			log("cannot connect to TurnTable");
		}
		return device;
	}

	private void assertCommand(TurnTableCommand command,
			TurnTableCommand assertetedCommand) throws IOException {
		if (command != assertetedCommand) {
			log("Invalid command:");
			log("Expected:" + assertetedCommand);
			throw new IOException("Invalid Command");
		}
	}

	private TurnTableCommand receiveCommand() throws IOException {
		int commandOrdinal = dataInputStream.readInt();
		TurnTableCommand command = TurnTableCommand
				.getByOrdinal(commandOrdinal);
		log("Receive:" + command.name());
		return command;
	}

	private void sendCommand(TurnTableCommand command) throws IOException {
		dataOutputStream.writeInt(command.ordinal());
		dataOutputStream.flush();
		log("Send: " + command.name());
	}

	private void log(String message) {
		System.out.println(message);
	}

}
