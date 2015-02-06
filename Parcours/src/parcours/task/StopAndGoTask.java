package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;

public class StopAndGoTask extends ControllerTask {

	private static final int BACKWARD_DISTANCE = -10;
	
	protected DifferentialPilot pilot;

	private final int acceleration;
	private final int distance;
	protected int distanceRemaining;
	private final int speed;
	protected TouchSensor touchSensorRight, touchSensorLeft;


	public StopAndGoTask(int distance, int speed, int acceleration) {
		this.distance = distance;
		this.speed = speed;
		this.acceleration = acceleration;
	}

	@Override
	protected void init() {
		touchSensorRight = RobotDesign.touchSensorRight;
		touchSensorLeft = RobotDesign.touchSensorLeft;
		pilot = RobotDesign.differentialPilot;
		pilot.setAcceleration(acceleration);
		pilot.setTravelSpeed(speed);
		
		distanceRemaining = distance;
		travelRemainingDistance();
	}
	
	protected void travelRemainingDistance() {
		pilot.travel(distanceRemaining, true);
	}

	@Override
	protected void control() {
		if (touchSensorRight.isPressed() || touchSensorLeft.isPressed()) {
			distanceRemaining -= pilot.getMovementIncrement();
			pilot.travel(BACKWARD_DISTANCE);
			distanceRemaining -= pilot.getMovementIncrement(); // BACKWARD_DISTANCE	
			distanceRemaining += 8;
			Delay.msDelay(5 * 1000);
			travelRemainingDistance();
		}
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
