package parcours.task;

import lejos.nxt.Sound;
import utils.RobotDesign;

public class TraversOpenedDoorTask extends TravelHandleCollisionTask {
	
	//private boolean isMovingArc = false;
	//private static final int ARC_GOAL = 25;
	
	public TraversOpenedDoorTask() {
		super(140, (int)(RobotDesign.differentialPilot.getMaxTravelSpeed()));
	}

	@Override
	protected void init() {		
		super.init();
	}

	@Override
	protected void control() {
		/*if ( isMovingArc && pilot.getMovementIncrement() > ARC_GOAL ) {
			distanceRemaining -= pilot.getMovementIncrement();
			isMovingArc = false;
			travelRemainingDistance();
		}*/
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
		//distanceRemaining += DISTANCE_LOST_BY_TURN;
		distanceRemaining -= pilot.getMovementIncrement();
		//pilot.travel(BACKWARD_DISTANCE);
		//distanceRemaining -= pilot.getMovementIncrement(); // BACKWARD_DISTANCE	
		pilot.rotate(angle);
		travelRemainingDistance();
		Sound.beep();
		/*isMovingArc = true;
		
		distanceRemaining -= pilot.getMovementIncrement();
		// turn left
		if (angle > 0) {
			pilot.arcForward( 100 );
		} else { // turn right
			pilot.arcForward( -100 );
		}*/
	}

}
