package parcours.task;

import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;

public class GoIntoElevatorTask extends ControllerTask {
	protected DifferentialPilot pilot;
	protected TouchSensor touchSensorRight, touchSensorLeft;
	
	@Override
	protected void init() {
		pilot = RobotDesign.differentialPilot;
		touchSensorRight = RobotDesign.touchSensorRight;
		touchSensorLeft = RobotDesign.touchSensorLeft;
		pilot.setTravelSpeed(10);
	}

	@Override
	protected void control() {
		pilot.forward();
		if (touchSensorRight.isPressed()){
			pilot.rotate(15);
		}
		if (touchSensorLeft.isPressed()){
			pilot.rotate(-10);
		}
	}

	@Override
	protected boolean abort() {
		return (touchSensorRight.isPressed() && touchSensorLeft.isPressed());
	}

	@Override
	protected void tearDown() {
		pilot.stop();
	}

}
