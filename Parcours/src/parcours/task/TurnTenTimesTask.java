package parcours.task;

import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class TurnTenTimesTask extends ControllerTask {
	private static final int TURN_ANGLE = 10 * 360;

	protected DifferentialPilot pilot;

	public TurnTenTimesTask(int acceleration, int speed) {
		this.pilot = RobotDesign.differentialPilot;
		pilot.setAcceleration(acceleration);
		pilot.setRotateSpeed(speed);
	}

	@Override
	protected void init() {
		pilot.rotate(TURN_ANGLE);
	}

	@Override
	protected void control() {
	}

	@Override
	protected boolean abort() {
		return true;
	}

	@Override
	protected void tearDown() {
		pilot.stop();
		Motor.B.suspendRegulation();
		Motor.A.suspendRegulation();
	}

}
