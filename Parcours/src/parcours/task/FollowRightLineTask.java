package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class FollowRightLineTask extends Task {

	private UltrasonicSensor ultra;
	private DifferentialPilot pilot;

	@Override
	protected void init() {
		ultra = new UltrasonicSensor(SensorPort.S3);
		pilot = new DifferentialPilot(8.16, 13, Motor.B, Motor.A);
	}

	@Override
	protected void control() {
		int a = ultra.getDistance();
		pilot.setTravelSpeed(20);
		pilot.forward();

		if (a < 10) {
			pilot.rotate(10);
			pilot.travel(10);
			pilot.rotate(-10);
		} else if (a > 20 && a < 50) {
			pilot.rotate(-10);
			pilot.travel(15);
			pilot.rotate(10);
		} else if (a > 55) {
			pilot.travel(5);
			pilot.rotate(-90);
			pilot.travel(25);
		}
	}

	@Override
	protected boolean abort() {
		// TODO Return true if a line is detected
		return false;
	}

}
