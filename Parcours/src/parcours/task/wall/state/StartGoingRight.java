package parcours.task.wall.state;

public class StartGoingRight extends WallStateBase {

	@Override
	public void handleNoButtonIsPressed(WallStateContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().rotate(20, true);
		context.setState(WallState.FINISH_MOVEMENT);
	}

}
