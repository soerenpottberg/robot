package parcours.task.wall.state;

public class StartChangingDistance extends LabyrinthStateBase {

	private static final int DISTANCE = 20;
	private final LabyrinthState endChangingDistanceState;

	public StartChangingDistance(LabyrinthState endChangingDistanceState) {
		this.endChangingDistanceState = endChangingDistanceState;
	}

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().travel(DISTANCE, true);
		context.setState(endChangingDistanceState);
	}

}
