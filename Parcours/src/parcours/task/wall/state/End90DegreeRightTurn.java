package parcours.task.wall.state;

public class End90DegreeRightTurn extends WallStateBase {

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
		
		context.getPilot().travel(25, true);
		context.setState(WallState.FINISH_MOVEMENT);
	}

}
