package parcours.task.wall.state;

public class Start90DegreeRightTurn extends WallStateBase {



	private static final int RIGHT_ANGLE = -90;

	@Override
	public void handleNoButtonIsPressed(WallStateContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().rotate(RIGHT_ANGLE, true);
		context.setState(WallState.END_90_DEGREE_RIGHT_TURN);
	}

}
