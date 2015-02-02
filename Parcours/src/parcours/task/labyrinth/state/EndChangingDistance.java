package parcours.task.labyrinth.state;

import lejos.nxt.Motor;
import parcours.task.labyrinth.LabyrinthContext;

public class EndChangingDistance extends LabyrinthStateBase {
	
	public String name() {
		return "EChDist";
	}
	
	public enum DistanceChange {
		SMALL_INCREASE_OF_DISTANCE,
		LARGE_INCREASE_OF_DISTANCE,
		DECREASE_OF_DISTANCE;
	}

	private DistanceChange distanceChange;

	public EndChangingDistance(DistanceChange distanceChange) {
		this.distanceChange = distanceChange;		
	}

	

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.isMoving()) {
			return;
		}
		Motor.A.flt(true);
		Motor.B.flt(true);
		
		
		int turnAngle = getTurnAngle(context);
		int turnBackAngle = -turnAngle;
		context.rotate(-turnBackAngle);
		context.setState(LabyrinthState.FINISH_MOVEMENT);
	}



	private int getTurnAngle(LabyrinthContext context) {
		switch (distanceChange) {
		case DECREASE_OF_DISTANCE:
			return context.getDecreaseDistanceAngle();
		case LARGE_INCREASE_OF_DISTANCE:
			return context.getLargeInreaseDistanceAngle();
		case SMALL_INCREASE_OF_DISTANCE:
			return context.getSmallInreaseDistanceAngle();
		}
		throw new IllegalArgumentException();
	}

}
