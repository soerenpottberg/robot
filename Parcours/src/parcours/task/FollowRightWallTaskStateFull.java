package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.detector.LineDetector;
import parcours.task.wall.state.NormalState;
import parcours.task.wall.state.WallStateBase;
import parcours.task.wall.state.WallStateContext;

public class FollowRightWallTaskStateFull extends Task {

	private UltrasonicSensor ultra;
	private DifferentialPilot pilot;
	private boolean isAboarted = false;

	
	private TouchSensor touchl;
	private TouchSensor touchr; 

	private LineDetector lineDetector;
	private WallStateContext context;


	@Override
	protected void init() {
		ultra = new UltrasonicSensor(SensorPort.S3);
		pilot = new DifferentialPilot(8.16, 13, Motor.B, Motor.A);
		touchr = new TouchSensor(SensorPort.S4);
		touchl = new TouchSensor(SensorPort.S2);
		lineDetector = new LineDetector();
		context = new WallStateContext(pilot);
		pilot.setTravelSpeed(20);
		pilot.forward();
	}

	@Override
	protected void control() {
		int distance = ultra.getDistance();
		
		
		
		boolean leftIsPressed = touchl.isPressed();
		boolean rightIsPressed = touchr.isPressed();
		
		if(leftIsPressed && rightIsPressed) {
			context.handleBothButtonsPressed(distance);
		} else if(leftIsPressed) {
			context.handleLeftButtonPressed(distance);
		} else if(rightIsPressed) {
			context.handleRightButtonPressed(distance);
		} else {
			context.handleNoButtonIsPressed(distance);
		}
		
		/*
		///////////////////

		if (touchl.isPressed() && distance < 25){
			pilot.travel(-25,true);
			awaitRotation();
			pilot.rotate(90,true);
			awaitRotation();
		} else if (touchl.isPressed() && distance >= 25) {
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
		} else if ((distance < 15) && (touchr.isPressed() == false)) {
			pilot.rotate(20, true);
			awaitRotation();
			pilot.travel(20, true);
			awaitRotation();
			pilot.rotate(-20, true);
			awaitRotation();
		} else if (distance > 15 && distance < 20) {
			pilot.rotate(10, true);
			awaitRotation();
			pilot.travel(20, true);
			awaitRotation();
			pilot.rotate(-10, true);
			awaitRotation();
		} else if ((distance > 25 && distance < 50)  && (touchr.isPressed() == false)) {
			pilot.rotate(-10, true);
			awaitRotation();
			pilot.travel(20, true);
			awaitRotation();
			pilot.rotate(10, true);
			awaitRotation();
		} else if (distance > 55) {
			pilot.travel(7, true);
			awaitRotation();
			pilot.rotate(-90, true);
			awaitRotation();
			pilot.travel(25, true);
			awaitRotation();
		}
		*/
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
