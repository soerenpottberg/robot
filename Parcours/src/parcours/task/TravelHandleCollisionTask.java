package parcours.task;

import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;
import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class TravelHandleCollisionTask extends ControllerTask {
	private static final int BACKWARD_DISTANCE = -10;
	private static final int CORRECTION_ANGLE = 20;
	private static final int DISTANCE_LOST_BY_TURN = 2;

	protected DifferentialPilot pilot;
	protected TouchSensor touchSensorRight, touchSensorLeft;

	private final int distance;
	protected int distanceRemaining;
	private int speed;

	public TravelHandleCollisionTask(int distance, int speed) {
		this.distance = distance;
		this.speed = speed;
	}

	@Override
	protected void init() {
		touchSensorRight = RobotDesign.touchSensorRight;
		touchSensorLeft = RobotDesign.touchSensorLeft;
		pilot = RobotDesign.differentialPilot;

		distanceRemaining = distance;
		pilot.setTravelSpeed(speed);
		travelRemainingDistance();
	}

	protected void travelRemainingDistance() {
		pilot.travel(distanceRemaining, true);
	}

	@Override
	protected void control() {
		if (touchSensorRight.isPressed()) {
			turnLeft();
		}

		if (touchSensorLeft.isPressed()) {
			turnRight();
		}
	}

	private void turnRight() {
		turn(-CORRECTION_ANGLE);
	}

	private void turnLeft() {
		turn(CORRECTION_ANGLE);
	}

	protected void turn(int angle) {
		distanceRemaining += DISTANCE_LOST_BY_TURN;
		distanceRemaining -= pilot.getMovementIncrement();
		pilot.travel(BACKWARD_DISTANCE);
		distanceRemaining -= pilot.getMovementIncrement(); // BACKWARD_DISTANCE	
		pilot.rotate(angle);
		travelRemainingDistance();
	}

	@Override
	protected boolean abort() {
		if ( !pilot.isMoving() ) {
			Sound.beepSequence();
		}
		return !pilot.isMoving();
	}

	@Override
	protected void tearDown() {
		pilot.stop();
		Motor.B.suspendRegulation();
		Motor.A.suspendRegulation();
	}

}
