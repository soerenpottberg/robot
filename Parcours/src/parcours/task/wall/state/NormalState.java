package parcours.task.wall.state;

public class NormalState extends WallStateBase {

	private static final int FAR_DISTANCE = 50; // There is still a wall
	private static final int MAXIMAL_DISTANCE = 25;
	private static final int MINIMAL_DISTANCE = 20;
	private static final int CLOSE_DISTANCE = 15;

	

	@Override
	public void handleNoButtonIsPressed(WallStateContext context, int distance) {
		if ((distance < CLOSE_DISTANCE)) {
			context.getPilot().rotate(20, true);
			context.setState(WallState.START_CHANGING_DISTANCE_LEFT);
			/*
			 * awaitRotation(); pilot.travel(20, true); awaitRotation();
			 * pilot.rotate(-20, true); awaitRotation();
			 */
		} else if (distance > CLOSE_DISTANCE && distance < MINIMAL_DISTANCE) {
			context.getPilot().rotate(10, true);
			context.setState(WallState.START_CHANGING_DISTANCE_LITTLE_LEFT);
			/*
			 * pilot.rotate(10, true); awaitRotation(); pilot.travel(20, true);
			 * awaitRotation(); pilot.rotate(-10, true); awaitRotation();
			 */
		} else if ((distance > MAXIMAL_DISTANCE && distance < FAR_DISTANCE)) {
			context.getPilot().rotate(-10, true);
			context.setState(WallState.START_CHANGING_DISTANCE_RIGHT);
			/*
			 * pilot.rotate(-10, true); awaitRotation(); pilot.travel(20, true);
			 * awaitRotation(); pilot.rotate(10, true); awaitRotation();
			 */
		} else if (distance > FAR_DISTANCE) {
			context.getPilot().travel(5, true);
			context.setState(WallState.START_90_DEGREE_RIGHT_TURN);
			/*
			 * pilot.travel(7, true); awaitRotation(); pilot.rotate(-90, true);
			 * awaitRotation(); pilot.travel(25, true); awaitRotation();
			 */
		}
	}

}
