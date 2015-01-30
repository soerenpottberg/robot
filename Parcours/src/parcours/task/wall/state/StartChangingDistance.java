package parcours.task.wall.state;

public class StartChangingDistance extends WallStateBase {

	private final WallState endChangingDistanceState;

	public StartChangingDistance(WallState endChangingDistanceState) {
		this.endChangingDistanceState = endChangingDistanceState;
	}

	@Override
	public void handleLeftButtonPressed(WallStateContext context, int distance) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleRightButtonPressed(WallStateContext context, int distance) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleNoButtonIsPressed(WallStateContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().travel(20, true);
		context.setState(endChangingDistanceState);
	}

}
