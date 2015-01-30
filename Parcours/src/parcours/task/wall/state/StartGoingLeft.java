package parcours.task.wall.state;

public class StartGoingLeft extends WallStateBase {



	@Override
	public void handleNoButtonIsPressed(WallStateContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().rotate(-15, true);
		context.setState(WallState.FINISH_MOVEMENT);
	}

}
