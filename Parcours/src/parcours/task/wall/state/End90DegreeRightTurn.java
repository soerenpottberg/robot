package parcours.task.wall.state;

public class End90DegreeRightTurn extends WallStateBase {


	private static final int AFTER_TURN_CUTTING_EDGE = 30;

	@Override
	public void handleNoButtonIsPressed(WallStateContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().travel(AFTER_TURN_CUTTING_EDGE, true);
		context.setState(WallState.FINISH_MOVEMENT);
	}

}
