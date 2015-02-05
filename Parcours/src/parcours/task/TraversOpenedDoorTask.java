package parcours.task;

import parcours.utils.RobotDesign;
import lejos.nxt.Sound;

public class TraversOpenedDoorTask extends TravelHandleCollisionTask {
	
	public TraversOpenedDoorTask() {
		super(160, (int)(RobotDesign.differentialPilot.getMaxTravelSpeed()));
	}

	@Override
	protected void init() {		
		super.init();
	}

	@Override
	protected void control() {
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
		distanceRemaining -= pilot.getMovementIncrement();
		distanceRemaining += 10;
		pilot.rotate(angle);
		travelRemainingDistance();
		Sound.beep();
	}

}
