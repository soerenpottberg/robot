package parcours.task.wall.state;

public abstract class WallStateBase {

	public final void handleBothButtonsPressed(WallStateContext context,
			int distance) {
		context.getPilot().travel(-25, true);
		context.setState(WallState.START_90_DEGREE_LEFT_TURN);
	}

	public final void handleLeftButtonPressed(WallStateContext context,
			int distance) {
		// TODO Auto-generated method stub
		if (distance < 25) {
			context.getPilot().travel(-20, true);
			context.setState(WallState.START_90_DEGREE_LEFT_TURN);
			/*
			 * awaitRotation(); pilot.rotate(90,true); awaitRotation();
			 */
		} else {
			context.getPilot().travel(-10, true);
			context.setState(WallState.START_GOING_LEFT);
			/*
			 * a1waitRotation(); pi1lot.rotate(-15,true); awaitRotation();
			 */
		}
	}

	public final void handleRightButtonPressed(WallStateContext context,
			int distance) {
		context.getPilot().travel(-10,true);
		context.setState(WallState.START_GOING_RIGHT);
		/*awaitRotation();
		pilot.rotate(20,true);
		awaitRotation();*/
	}

	public abstract void handleNoButtonIsPressed(WallStateContext context,
			int distance);

}
