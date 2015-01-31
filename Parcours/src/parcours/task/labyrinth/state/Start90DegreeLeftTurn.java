package parcours.task.labyrinth.state;

public class Start90DegreeLeftTurn extends LabyrinthStateBase {


	private static final int LEFT_ANGLE = 90;

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().rotate(LEFT_ANGLE, true);
		context.setState(LabyrinthState.FINISH_MOVEMENT);
	}

}
