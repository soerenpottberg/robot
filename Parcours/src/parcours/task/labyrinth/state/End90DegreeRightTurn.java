package parcours.task.labyrinth.state;

public class End90DegreeRightTurn extends LabyrinthStateBase {


	private static final int AFTER_TURN_CUTTING_EDGE = 30;

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().travel(AFTER_TURN_CUTTING_EDGE, true);
		context.setState(LabyrinthState.FINISH_MOVEMENT);
	}

}
