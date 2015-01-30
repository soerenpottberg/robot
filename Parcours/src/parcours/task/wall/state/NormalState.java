package parcours.task.wall.state;

public class NormalState extends WallStateBase {

	private static final int CUTTING_EDGE = 20;
	private static final int LARGE_TURN_LEFT_ANGLE = 20;
	private static final int SMALL_TURN_LEFT_ANGLE = 10;
	private static final double TURN_RIGHT_ANGLE = -10;
	private static final int FAR_DISTANCE = 60; // There is still a wall
	private static final int MAXIMAL_DISTANCE = 25;
	private static final int MINIMAL_DISTANCE = 20;
	private static final int CLOSE_DISTANCE = 15;

	@Override
	public void handleNoButtonIsPressed(WallStateContext context, int distance) {
		if ((distance < CLOSE_DISTANCE)) {
			context.getPilot().rotate(LARGE_TURN_LEFT_ANGLE, true);
			context.setState(WallState.START_CHANGING_DISTANCE_LEFT);
		} else if (distance > CLOSE_DISTANCE && distance < MINIMAL_DISTANCE) {
			context.getPilot().rotate(SMALL_TURN_LEFT_ANGLE, true);
			context.setState(WallState.START_CHANGING_DISTANCE_LITTLE_LEFT);
		} else if ((distance > MAXIMAL_DISTANCE && distance < FAR_DISTANCE)) {
			context.getPilot().rotate(TURN_RIGHT_ANGLE, true);
			context.setState(WallState.START_CHANGING_DISTANCE_RIGHT);
		} else if (distance > FAR_DISTANCE) {
			context.getPilot().travel(CUTTING_EDGE, true);
			context.setState(WallState.START_90_DEGREE_RIGHT_TURN);
		}
	}

}
