package parcours.task.wall.state;

public class End90DegreeRightTurn extends WallStateBase {


	@Override
	public void handleNoButtonIsPressed(WallStateContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().travel(30, true);
		context.setState(WallState.FINISH_MOVEMENT);
	}

}
