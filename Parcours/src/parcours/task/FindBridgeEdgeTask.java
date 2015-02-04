package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.detector.BridgeEdgeDetector;
import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;

public class FindBridgeEdgeTask extends ControllerTask {
	private static final int BASE_SPEED = 20;
	
	private DifferentialPilot pilot;
	private BridgeEdgeDetector bridgeEdgeDetector;

	@Override
	protected void init() {		
		pilot = RobotDesign.differentialPilot;
		bridgeEdgeDetector = new BridgeEdgeDetector();
		pilot.setTravelSpeed(BASE_SPEED);
		Motor.C.rotate(90);
		pilot.rotate(10);
		pilot.forward();
	}

	@Override
	protected void control() {
		
	}

	@Override
	protected boolean abort() {
		return bridgeEdgeDetector.hasDetected();
	}

	@Override
	protected void tearDown() {
		pilot.stop();
		Sound.beep();
		Motor.B.suspendRegulation();
		Motor.A.suspendRegulation();
	}

}
