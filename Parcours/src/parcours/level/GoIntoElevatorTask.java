package parcours.level;

import lejos.nxt.Motor;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.task.Task;
import utils.RobotDesign;

public class GoIntoElevatorTask extends Task {
	protected DifferentialPilot pilot;
	protected TouchSensor touchSensorRight, touchSensorLeft;
	
	@Override
	protected void init() {
		touchSensorRight = RobotDesign.touchSensorRight;
		touchSensorLeft = RobotDesign.touchSensorLeft;
		pilot = RobotDesign.differentialPilot;
		pilot.setTravelSpeed(20);
		pilot.travel(-5);
	}

	@Override
	protected void control() {
		pilot.travelArc(-25, -3);
		//pilot.travel(-5);
		Motor.C.rotate(-60);
	}

	@Override
	protected boolean abort() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void tearDown() {
		// TODO Auto-generated method stub

	}

}
