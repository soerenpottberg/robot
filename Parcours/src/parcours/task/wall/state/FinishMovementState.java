package parcours.task.wall.state;

public class FinishMovementState extends WallStateBase {



	@Override
	public void handleNoButtonIsPressed(WallStateContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().forward();
		context.setState(WallState.NORMAL);
	}

}
