package parcours.task;

import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;
import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.robotics.navigation.DifferentialPilot;

public class RightTurnTask extends ControllerTask {

	private static final int BACKWARD_DISTANCE = -5;
	private static final int RIGHT_ANGLE = -85;

	@Override
	protected void init() {
		DifferentialPilot pilot = RobotDesign.differentialPilot;
		pilot.setTravelSpeed(20);
		Sound.beep();
		pilot.travel(BACKWARD_DISTANCE);
		Sound.beep();
		pilot.rotate(RIGHT_ANGLE);
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
		Motor.A.suspendRegulation();
		Motor.B.suspendRegulation();
	}

}
