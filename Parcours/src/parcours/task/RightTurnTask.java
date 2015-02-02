package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.robotics.navigation.DifferentialPilot;
import utils.RobotDesign;

public class RightTurnTask extends Task {

	private static final int BACKWARD_DISTANCE = -5;
	private static final int RIGHT_ANGLE = -70;

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
