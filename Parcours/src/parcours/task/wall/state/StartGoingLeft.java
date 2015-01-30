package parcours.task.wall.state;

public class StartGoingLeft extends WallStateBase {



	private static final int GOING_LEFT_ANGLE = -15;

	@Override
	public void handleNoButtonIsPressed(WallStateContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().rotate(GOING_LEFT_ANGLE, true);
		context.setState(WallState.FINISH_MOVEMENT);
	}

}
