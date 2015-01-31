package parcours.task.labyrinth.state;

public class StartGoingLeft extends LabyrinthStateBase {



	private static final int GOING_LEFT_ANGLE = -15;

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().rotate(GOING_LEFT_ANGLE, true);
		context.setState(LabyrinthState.FINISH_MOVEMENT);
	}

}
