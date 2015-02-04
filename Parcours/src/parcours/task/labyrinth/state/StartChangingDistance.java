package parcours.task.labyrinth.state;

import lejos.nxt.Motor;
import parcours.task.labyrinth.LabyrinthContext;

public class StartChangingDistance extends LabyrinthStateBase {
	
	public String name() {
		return "SChDi";
	}

	private final LabyrinthState endChangingDistanceState;

	public StartChangingDistance(LabyrinthState endChangingDistanceState) {
		this.endChangingDistanceState = endChangingDistanceState;
	}

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.isMoving()) {
			return;
		}
		Motor.A.flt(true);
		Motor.B.flt(true);
		
		boolean direction = context.getDirectionLeft();
		int dist1;
		double rad;
		
		if(direction){
			final int a = context.getWallDistance();
			final int teta = context.getLargeIncreaseDistanceAngle();
			final int b = context.getMinimalDistance();
			rad = Math.toRadians(teta);
			dist1 = (int) ((b-a)/(Math.sin(rad)));
		}
		else{
			//Sound.beep();
			final int a = context.getWallDistance();
			final int teta = context.getDecreaseDistanceAngle();
			final int b = context.getMaximalDistance();
			rad = Math.toRadians(teta);
			dist1 = (int) ((a-b)/Math.cos(rad));
		}
		
	
		final int dist = Math.max(dist1, context.getAdjustmentDistance());
		context.travel(dist);
		context.setState(endChangingDistanceState);
	}

}
