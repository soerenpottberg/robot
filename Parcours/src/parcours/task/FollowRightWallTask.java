package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.detector.LineDetector;
import utils.RobotDesign;

public class FollowRightWallTask extends ControllerTask {

	private UltrasonicSensor distanceSensor;
	private DifferentialPilot pilot;
	private boolean isAboarted = false;

	
	private TouchSensor touchL;
	private TouchSensor touchR; 

	private LineDetector lineDetector;


	@Override
	protected void init() {
		distanceSensor = RobotDesign.distanceSensor;
		pilot          = RobotDesign.differentialPilot;
		touchR         = RobotDesign.touchSensorRight;
		touchL         = RobotDesign.touchSensorLeft;
		
		lineDetector = new LineDetector();
	}

	@Override
	protected void control() {
		int a = distanceSensor.getDistance();
		pilot.setTravelSpeed(20);
		pilot.forward();

		if (touchL.isPressed() && a < 25){
			pilot.travel(-25,true);
			awaitRotation();
			pilot.rotate(90,true);
			awaitRotation();
		} else if (touchL.isPressed() && a >= 25) {
			pilot.travel(-10,true);
			awaitRotation();
			pilot.rotate(-15,true);
			awaitRotation();
		} else if (touchR.isPressed()) {
			//Sound.beep();
			pilot.travel(-10,true);
			awaitRotation();
			pilot.rotate(20,true);
			awaitRotation();
		} else if ((a < 15) && (touchR.isPressed() == false)) {
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
		} else if ((a > 25 && a < 50)  && (touchR.isPressed() == false)) {
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
			if (touchR.isPressed()) {
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
