package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.detector.LineDetector;

public class FollowRightWallTask extends Task {

	private UltrasonicSensor ultra;
	private DifferentialPilot pilot;
	private boolean isAboarted = false;

	
	private TouchSensor touchl;
	private TouchSensor touchr; 

	private LineDetector lineDetector;


	@Override
	protected void init() {
		ultra = new UltrasonicSensor(SensorPort.S3);
		pilot = new DifferentialPilot(8.16, 13, Motor.B, Motor.A);
		touchr = new TouchSensor(SensorPort.S4);
		touchl = new TouchSensor(SensorPort.S2);
		lineDetector = new LineDetector();
	}

	@Override
	protected void control() {
		int a = ultra.getDistance();
		pilot.setTravelSpeed(20);
		pilot.forward();

		if (touchl.isPressed() && a < 25){
			pilot.travel(-25,true);
			awaitRotation();
			pilot.rotate(90,true);
			awaitRotation();
		} else if (touchl.isPressed() && a >= 25) {
			pilot.travel(-10,true);
			awaitRotation();
			pilot.rotate(-15,true);
			awaitRotation();
		} else if (touchr.isPressed()) {
			//Sound.beep();
			pilot.travel(-10,true);
			awaitRotation();
			pilot.rotate(20,true);
			awaitRotation();
		} else if ((a < 15) && (touchr.isPressed() == false)) {
			pilot.rotate(20, true);
			awaitRotation();
			pilot.travel(20, true);
			awaitRotation();
			pilot.rotate(-20, true);
			awaitRotation();
		} else if (a > 15 && a < 20) {
			pilot.rotate(10, true);
			awaitRotation();
			pilot.travel(20, true);
			awaitRotation();
			pilot.rotate(-10, true);
			awaitRotation();
		} else if ((a > 25 && a < 50)  && (touchr.isPressed() == false)) {
			pilot.rotate(-10, true);
			awaitRotation();
			pilot.travel(20, true);
			awaitRotation();
			pilot.rotate(10, true);
			awaitRotation();
		} else if (a > 55) {
			pilot.travel(7, true);
			awaitRotation();
			pilot.rotate(-90, true);
			awaitRotation();
			pilot.travel(25, true);
			awaitRotation();
		}
	}

	private void awaitRotation() {
		while (pilot.isMoving()) {
			if(abort()) {
				isAboarted  = true;
			}
			if (touchr.isPressed()) {
				//Sound.beep();
			}
			Thread.yield();
		}
	}

	@Override
	protected boolean abort() {
		return isAboarted || lineDetector.hasDetected();
	}

	@Override
	protected void tearDown() {
		Motor.B.suspendRegulation();
		Motor.A.suspendRegulation();
	}

}
