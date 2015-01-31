package parcours.task.labyrinth.state;

import parcours.task.labyrinth.LabyrinthContext;

public class StartChangingDistance extends LabyrinthStateBase {

	private final LabyrinthState endChangingDistanceState;

	public StartChangingDistance(LabyrinthState endChangingDistanceState) {
		this.endChangingDistanceState = endChangingDistanceState;
	}

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.isMoving()) {
			return;
		}
		
		context.travel(context.getAdjustmentDistance());
		context.setState(endChangingDistanceState);
	}

}
