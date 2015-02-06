package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.detector.LapsedTimeDetector;
import parcours.detector.LineDetector;
import parcours.task.base.ControllerTask;
import parcours.task.labyrinth.LabyrinthContext;
import parcours.task.labyrinth.config.LabyrinthConfiguration;
import parcours.utils.RobotDesign;

// TODO: Don't stop all the time for turning, do it while maintaining speed
// TODO: verify with higher speeds
// TODO: Measure which values for wheel diameter and track width are optimal (current ones don't perfectly match angles when turning)
// TODO: Move generation of differential pilot object (with optimum parameters) to separate utility class to be used anywhere in the project.
public class FollowRightWallTaskStateFull extends ControllerTask {

	// private static final int BASE_TRAVEL_SPEED = 40;//30

	private UltrasonicSensor distanceSensor;
	private DifferentialPilot pilot;
	// private boolean isAboarted = false;

	private TouchSensor touchL;
	private TouchSensor touchR;

	private LineDetector lineDetector;
	private LabyrinthContext context;

	private LabyrinthConfiguration configuration;
	private boolean detectEnd = true;
	private LapsedTimeDetector timeoutDetector = new LapsedTimeDetector(20 * 1000);

	public FollowRightWallTaskStateFull(LabyrinthConfiguration configuration,
			boolean detectEnd) {
		this.configuration = configuration;
		this.detectEnd = detectEnd;
	}

	public FollowRightWallTaskStateFull(LabyrinthConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	protected void init() {
		distanceSensor = RobotDesign.distanceSensor;
		touchR = RobotDesign.touchSensorRight;
		touchL = RobotDesign.touchSensorLeft;
		pilot = RobotDesign.differentialPilot;

		lineDetector = new LineDetector();
		context = new LabyrinthContext(configuration, pilot);
		pilot.setTravelSpeed(configuration.getBaseTravelSpeed());
		// pilot.setRotateSpeed( 500 );
		pilot.setAcceleration(130); // default 210
		pilot.forward();
		timeoutDetector.arm();
	}

	@Override
	protected void control() {
		int distance = distanceSensor.getDistance();

		boolean leftIsPressed = touchL.isPressed();
		boolean rightIsPressed = touchR.isPressed();

		if (leftIsPressed && rightIsPressed) {
			context.handleBothButtonsPressed(distance);
		} else if (leftIsPressed) {
			context.handleRightButtonPressed(distance);
		} else if (rightIsPressed) {
			context.handleRightButtonPressed(distance);
		} else {
			context.handleNoButtonIsPressed(distance);
		}

	}

	@Override
	protected boolean abort() {
		return detectEnd && timeoutDetector.hasDetected() && lineDetector.hasDetected();
	}

	@Override
	protected void tearDown() {
	}

}
