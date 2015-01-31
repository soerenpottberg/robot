package parcours.task.labyrinth.state;

public class Start90DegreeRightTurn extends LabyrinthStateBase {



	private static final int RIGHT_ANGLE = -90;

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().rotate(RIGHT_ANGLE, true);
		context.setState(LabyrinthState.END_90_DEGREE_RIGHT_TURN);
	}

}
