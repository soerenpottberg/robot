package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.detector.LineDetector;

public class FindLineTask extends Task {

	private DifferentialPilot pilot;
	private LineDetector lineDetector;
	private TouchSensor touchR, touchL;

	@Override
	protected void init() {
		touchR = new TouchSensor(SensorPort.S4);
		touchL = new TouchSensor(SensorPort.S2);
		
		pilot = new DifferentialPilot(8.16, 13, Motor.B, Motor.A);
		lineDetector = new LineDetector();
		pilot.setTravelSpeed(20);
		pilot.travel(15);
		pilot.forward();
	}

	@Override
	protected void control() {
		if ( touchR.isPressed() ) {
			pilot.stop();
			pilot.travel(-3);
			pilot.rotate(+10);
			pilot.forward();
		}
		
		if (touchL.isPressed() ) {
			pilot.stop();
			pilot.travel(-3);
			pilot.rotate(-10);
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
