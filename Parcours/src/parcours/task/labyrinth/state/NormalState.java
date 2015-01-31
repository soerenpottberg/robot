package parcours.task.labyrinth.state;

public class NormalState extends LabyrinthStateBase {

	private static final int CUTTING_EDGE = 20;
	private static final int LARGE_TURN_LEFT_ANGLE = 20;
	private static final int SMALL_TURN_LEFT_ANGLE = 10;
	private static final double TURN_RIGHT_ANGLE = -10;
	private static final int FAR_DISTANCE = 60; // There is still a wall
	private static final int MAXIMAL_DISTANCE = 25;
	private static final int MINIMAL_DISTANCE = 20;
	private static final int CLOSE_DISTANCE = 15;

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if ((distance < CLOSE_DISTANCE)) {
			context.rotate(LARGE_TURN_LEFT_ANGLE);
			context.setState(LabyrinthState.START_CHANGING_DISTANCE_LEFT);
		} else if (distance > CLOSE_DISTANCE && distance < MINIMAL_DISTANCE) {
			context.rotate(SMALL_TURN_LEFT_ANGLE);
			context.setState(LabyrinthState.START_CHANGING_DISTANCE_LITTLE_LEFT);
		} else if ((distance > MAXIMAL_DISTANCE && distance < FAR_DISTANCE)) {
			context.rotate(TURN_RIGHT_ANGLE);
			context.setState(LabyrinthState.START_CHANGING_DISTANCE_RIGHT);
		} else if (distance > FAR_DISTANCE) {
			context.travel(CUTTING_EDGE);
			context.setState(LabyrinthState.START_90_DEGREE_RIGHT_TURN);
		}
	}

}
