package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import utils.RobotDesign;

public class TravelHandleCollisionTask extends Task {
	private static final int BACKWARD_DISTANCE = -10;
	private static final int CORRECTION_ANGLE = 20;
	private static final int DISTANCE_LOST_BY_TURN = 2;

	private DifferentialPilot pilot;
	private TouchSensor touchSensorRight, touchSensorLeft;

	private final int distance;
	private int distanceRemaining;
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

	private void travelRemainingDistance() {
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

	private void turn(int angle) {
		distanceRemaining += DISTANCE_LOST_BY_TURN;
		distanceRemaining -= pilot.getMovementIncrement();
		pilot.travel(BACKWARD_DISTANCE);
		distanceRemaining -= pilot.getMovementIncrement(); // BACKWARD_DISTANCE	
		pilot.rotate(angle);
		travelRemainingDistance();
	}

	@Override
	protected boolean abort() {
		return !pilot.isMoving();
	}

	@Override
	protected void tearDown() {
		pilot.stop();
		Motor.B.suspendRegulation();
		Motor.A.suspendRegulation();
	}

}
