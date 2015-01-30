package parcours.task.wall.state;

public abstract class WallStateBase {

	public final void handleBothButtonsPressed(WallStateContext context, int distance) {
		context.getPilot().travel(-25, true);
		context.setState(WallState.START_90_DEGREE_LEFT_TURN);
	}

	public abstract void handleLeftButtonPressed(WallStateContext context, int distance);

	public abstract void handleRightButtonPressed(WallStateContext context, int distance);

	public abstract void handleNoButtonIsPressed(WallStateContext context, int distance);
	
}
