package parcours.task;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.detector.LineDetector;
import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;

public class TurnFindLineTask extends ControllerTask {
	//private static final int BACKWARD_DISTANCE = -30;
	
	private static final int BASE_SPEED = 30;
	private static final int BASE_ACCELERATION = 80;

	private LineDetector detector;
	private DifferentialPilot pilot;
	private int minimalTriggerAngle = 90;

	public TurnFindLineTask() {
	}

	@Override
	protected void init() {
		detector = new LineDetector();
		
		pilot = RobotDesign.differentialPilot;
		pilot.setTravelSpeed(BASE_SPEED);
		pilot.setAcceleration(BASE_ACCELERATION);
		pilot.setRotateSpeed(80);
		
		//pilot.travel(BACKWARD_DISTANCE);
		pilot.rotate(185, true);
	}


	@Override
	protected void control() {
		if ( !pilot.isMoving() ) {
			pilot.reset();
			minimalTriggerAngle = 200;
			pilot.rotate(365, true);
		}
	}

	@Override
	protected boolean abort() {
		return pilot.getAngleIncrement() > minimalTriggerAngle && detector.hasDetected();
	}

	@Override
	protected void tearDown() {
		pilot.stop();
		Motor.B.suspendRegulation();
		Motor.A.suspendRegulation();
	}

}
