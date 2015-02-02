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
		
		context.travel(context.getAdjustmentDistance());
		context.setState(endChangingDistanceState);
	}

}
