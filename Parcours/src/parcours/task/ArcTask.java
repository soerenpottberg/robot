package parcours.task;

import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;
import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class ArcTask extends ControllerTask {

	protected DifferentialPilot pilot;
	



	@Override
	protected void init() {
		
		pilot = RobotDesign.differentialPilot;	
		pilot.setTravelSpeed(50);
		
	}


	@Override
	protected void control() {
		pilot.arc(-60, 90);
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
