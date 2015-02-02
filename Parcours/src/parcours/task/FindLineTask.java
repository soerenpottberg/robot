package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.detector.LineDetector;
import utils.RobotDesign;

public class FindLineTask extends Task {
	private static final int BASE_SPEED = 20;	
	private static final int BACKOFF_DISTANCE = -3;
	private static final int IMPACT_CORRECTION_ANGLE = 15;
	
	private DifferentialPilot pilot;
	private LineDetector lineDetector;
	private TouchSensor touchR, touchL;

	@Override
	protected void init() {
		touchR = RobotDesign.touchSensorRight;
		touchL = RobotDesign.touchSensorLeft;
		
		pilot = RobotDesign.differentialPilot;
		lineDetector = new LineDetector();
		pilot.setTravelSpeed(BASE_SPEED);
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
