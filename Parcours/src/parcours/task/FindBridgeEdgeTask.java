package parcours.task;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.detector.BridgeEdgeDetector;
import parcours.detector.LapsedTimeDetector;
import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;


public class FindBridgeEdgeTask extends ControllerTask {
	private static final int SMALL_TURN_LEFT_ANGLE = 9;
	private static final int SENSOR_EDGE_DETECTION_POSITION_ANGLE = 90;
	private static final int BASE_SPEED = 20;
	private static final int ACCELERATION = 80;
	
	private DifferentialPilot pilot;
	private BridgeEdgeDetector bridgeEdgeDetector;
	
	private int baseSpeed = BASE_SPEED;
	private int baseAcceleration = ACCELERATION;
	private boolean tryAgain = true;
	
	public FindBridgeEdgeTask() {}
	public FindBridgeEdgeTask(int speed, int acceleration) {
		baseSpeed = speed;
		baseAcceleration = acceleration;
	}

	@Override
	protected void init() {		
		pilot = RobotDesign.differentialPilot;
		bridgeEdgeDetector = new BridgeEdgeDetector();
		
		pilot.setTravelSpeed(baseSpeed);
		pilot.setAcceleration(baseAcceleration);
		
		// move sensor to bridge position.
		Motor.C.rotate(SENSOR_EDGE_DETECTION_POSITION_ANGLE);
		
		// make sure we reach the edge of the bridge during the incline and with a small angle
		pilot.rotate(SMALL_TURN_LEFT_ANGLE);
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
		Motor.B.suspendRegulation();
		Motor.A.suspendRegulation();
	}

}
