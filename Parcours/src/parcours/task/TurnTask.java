package parcours.task;

import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;
import lejos.robotics.navigation.DifferentialPilot;

public class TurnTask extends ControllerTask {

	private int angle, rotateSpeed;
	
	public TurnTask(int rotateSpeed, int angle) {
		this.angle = angle;
		this.rotateSpeed = rotateSpeed;
	}

	@Override
	protected void init() {
		DifferentialPilot pilot = RobotDesign.differentialPilot;
		pilot.setRotateSpeed(rotateSpeed);
		pilot.rotate(angle);
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
		RobotDesign.differentialPilot.setRotateSpeed(.8f * RobotDesign.differentialPilot.getMaxRotateSpeed());
	}

}
