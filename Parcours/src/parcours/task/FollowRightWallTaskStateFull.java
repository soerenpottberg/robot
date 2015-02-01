package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.detector.LineDetector;
import parcours.task.labyrinth.LabyrinthContext;
import parcours.task.labyrinth.config.LabyrinthConfiguration;

// TODO: Don't stop all the time for turning, do it while maintaining speed
// TODO: verify with higher speeds
// TODO: Measure which values for wheel diameter and track width are optimal (current ones don't perfectly match angles when turning)
// TODO: Move generation of differential pilot object (with optimum parameters) to separate utility class to be used anywhere in the project.
public class FollowRightWallTaskStateFull extends Task {

	private static final int BASE_TRAVEL_SPEED = 30;
	
	private UltrasonicSensor ultra;
	private DifferentialPilot pilot;
	private boolean isAboarted = false;

	private TouchSensor touchL;
	private TouchSensor touchR;

	private LineDetector lineDetector;
	private LabyrinthContext context;

	@Override
	protected void init() {
		ultra = new UltrasonicSensor(SensorPort.S3);
		pilot = new DifferentialPilot(8.16, 13, Motor.B, Motor.A);
		touchR = new TouchSensor(SensorPort.S4);
		touchL = new TouchSensor(SensorPort.S2);
		lineDetector = new LineDetector();
		context = new LabyrinthContext(new LabyrinthConfiguration(), pilot);
		pilot.setTravelSpeed(BASE_TRAVEL_SPEED);
		pilot.forward();
	}

	@Override
	protected void control() {
		int distance = ultra.getDistance();

		boolean leftIsPressed = touchL.isPressed();
		boolean rightIsPressed = touchR.isPressed();

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
