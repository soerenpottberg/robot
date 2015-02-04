package parcours.task;

import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;
import lejos.nxt.Motor;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class GoInFrontOfElevatorTask extends ControllerTask {
	protected DifferentialPilot pilot;
	protected TouchSensor touchSensorRight, touchSensorLeft;
	
	@Override
	protected void init() {
		touchSensorRight = RobotDesign.touchSensorRight;
		touchSensorLeft = RobotDesign.touchSensorLeft;
		pilot = RobotDesign.differentialPilot;
		pilot.setTravelSpeed(20);
		pilot.travel(-5);
		Motor.C.rotate(-90);			
		pilot.rotate(50);
		pilot.travel(-15);
		pilot.rotate(-50);
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
	}

}
