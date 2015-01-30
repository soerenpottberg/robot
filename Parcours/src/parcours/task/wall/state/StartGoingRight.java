package parcours.task.wall.state;

public class StartGoingRight extends WallStateBase {

	private static final int GOING_RIGHT_ANGLE = 20;

	@Override
	public void handleNoButtonIsPressed(WallStateContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().rotate(GOING_RIGHT_ANGLE, true);
		context.setState(WallState.FINISH_MOVEMENT);
	}

}
