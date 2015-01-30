package parcours.task.wall.state;

public class Start90DegreeLeftTurn extends WallStateBase {


	private static final int LEFT_ANGLE = 90;

	@Override
	public void handleNoButtonIsPressed(WallStateContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().rotate(LEFT_ANGLE, true);
		context.setState(WallState.FINISH_MOVEMENT);
	}

}
