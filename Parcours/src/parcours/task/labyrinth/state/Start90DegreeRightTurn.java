package parcours.task.labyrinth.state;

public class Start90DegreeRightTurn extends LabyrinthStateBase {



	private static final int RIGHT_ANGLE = -90;

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.isMoving()) {
			return;
		}
		
		context.rotate(RIGHT_ANGLE);
		context.setState(LabyrinthState.END_90_DEGREE_RIGHT_TURN);
	}

}
