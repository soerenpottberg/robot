package parcours.task;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;

public class TravelWithSpeedTask extends ControllerTask {

	protected DifferentialPilot pilot;

	private final int acceleration;
	private final int distance;
	private final int speed;

	public TravelWithSpeedTask(int distance, int speed, int acceleration) {
		this.distance = distance;
		this.speed = speed;
		this.acceleration = acceleration;
	}

	@Override
	protected void init() {
		pilot = RobotDesign.differentialPilot;
		pilot.setAcceleration(acceleration);
		pilot.setTravelSpeed(speed);
		pilot.travel(distance);
	}

	@Override
	protected void control() {
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
