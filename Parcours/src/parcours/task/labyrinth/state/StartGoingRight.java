package parcours.task.labyrinth.state;

public class StartGoingRight extends LabyrinthStateBase {

	private static final int GOING_RIGHT_ANGLE = 20;

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.isMoving()) {
			return;
		}
		
		context.rotate(GOING_RIGHT_ANGLE);
		context.setState(LabyrinthState.FINISH_MOVEMENT);
	}

}
