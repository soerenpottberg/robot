package parcours.level;

import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;



public class GoStraightFastTask extends ControllerTask {
	
	protected DifferentialPilot pilot;
	protected TouchSensor touchSensorRight, touchSensorLeft;

	@Override
	protected void init() {
		touchSensorRight = RobotDesign.touchSensorRight;
		touchSensorLeft = RobotDesign.touchSensorLeft;
		pilot = RobotDesign.differentialPilot;
		pilot.setTravelSpeed(0.8 * pilot.getMaxTravelSpeed());
		pilot.forward();
	}

	@Override
	protected void control() {
	}

	@Override
	protected boolean abort() {
		return touchSensorLeft.isPressed() || touchSensorRight.isPressed();
	}

	@Override
	protected void tearDown() {

	}

}
