package parcours.task.labyrinth.state;

public class StartChangingDistance extends LabyrinthStateBase {

	private static final int DISTANCE = 20;
	private final LabyrinthState endChangingDistanceState;

	public StartChangingDistance(LabyrinthState endChangingDistanceState) {
		this.endChangingDistanceState = endChangingDistanceState;
	}

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.isMoving()) {
			return;
		}
		
		context.travel(DISTANCE);
		context.setState(endChangingDistanceState);
	}

}
