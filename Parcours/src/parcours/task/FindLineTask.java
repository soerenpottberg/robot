package parcours.task;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.detector.LineDetector;

public class FindLineTask extends Task {

	private DifferentialPilot pilot;
	private LineDetector lineDetector;

	@Override
	protected void init() {
		pilot = new DifferentialPilot(8.16, 13, Motor.B, Motor.A);
		lineDetector = new LineDetector();
		pilot.setTravelSpeed(20);
		pilot.forward();
	}

	@Override
	protected void control() {
		// TODO check for collision		
	}

	@Override
	protected boolean abort() {
		return lineDetector.hasDetected();
	}

	@Override
	protected void tearDown() {
		pilot.stop();
		Motor.B.suspendRegulation();
		Motor.A.suspendRegulation();
	}

}
