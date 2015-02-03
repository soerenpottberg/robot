package parcours.task;

import lejos.nxt.Sound;
import utils.RobotDesign;

public class TraversOpenedDoorTask extends TravelHandleCollisionTask {
	
	
	
	private boolean isMovingArc = false;
	private static final int ARC_GOAL = 30;
	
	public TraversOpenedDoorTask() {
		super(100, (int)(RobotDesign.differentialPilot.getMaxTravelSpeed()));
	}

	@Override
	protected void init() {		
		super.init();
	}

	@Override
	protected void control() {
		if ( isMovingArc && pilot.getMovementIncrement() > ARC_GOAL ) {
			distanceRemaining -= pilot.getMovementIncrement();
			travelRemainingDistance();
			isMovingArc = false;
		}
		super.control();
	}

	@Override
	protected boolean abort() {
		return super.abort();
	}

	@Override
	protected void tearDown() {
		super.tearDown();
	}
	
	@Override
	protected void turn(int angle) {
		Sound.beep();
		isMovingArc = true;
		
		distanceRemaining -= pilot.getMovementIncrement();
		// turn left
		if (angle > 0) {
			pilot.arcForward( 20 );
		} else { // turn right
			pilot.arcForward( -20 );
		}
	}

}
