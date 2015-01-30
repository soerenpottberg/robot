package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.detector.LineDetector;

public class FindLineTask extends Task {
	private static final int BASE_SPEED = 20;
	private static final int INITIAL_TRAVEL_DISTANCE = 15; // used to clear the 3 lines at the beginning
	
	private static final int BACKOFF_DISTANCE = -3;
	private static final int IMPACT_CORRECTION_ANGLE = 15;
	
	private DifferentialPilot pilot;
	private LineDetector lineDetector;
	private TouchSensor touchR, touchL;

	@Override
	protected void init() {
		touchR = new TouchSensor(SensorPort.S4);
		touchL = new TouchSensor(SensorPort.S2);
		
		pilot = new DifferentialPilot(8.16, 13, Motor.B, Motor.A);
		lineDetector = new LineDetector();
		pilot.setTravelSpeed(BASE_SPEED);
		pilot.travel(INITIAL_TRAVEL_DISTANCE);
		pilot.forward();
	}

	@Override
	protected void control() {
		if ( touchR.isPressed() ) {
			pilot.stop();
			pilot.travel(BACKOFF_DISTANCE);
			pilot.rotate(IMPACT_CORRECTION_ANGLE);
			pilot.forward();
		}
		
		if (touchL.isPressed() ) {
			pilot.stop();
			pilot.travel(BACKOFF_DISTANCE);
			pilot.rotate(-1 * IMPACT_CORRECTION_ANGLE);
			pilot.forward();
		}
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
