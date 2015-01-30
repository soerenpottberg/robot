package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.detector.LineDetector;
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

		if (leftIsPressed && rightIsPressed) {
			context.handleBothButtonsPressed(distance);
		} else if (leftIsPressed) {
			context.handleLeftButtonPressed(distance);
		} else if (rightIsPressed) {
			context.handleRightButtonPressed(distance);
		} else {
			context.handleNoButtonIsPressed(distance);
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
