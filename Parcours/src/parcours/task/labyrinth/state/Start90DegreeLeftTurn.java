package parcours.task.labyrinth.state;

public class Start90DegreeLeftTurn extends LabyrinthStateBase {


	private static final int LEFT_ANGLE = 90;

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.isMoving()) {
			return;
		}
		
		context.rotate(LEFT_ANGLE);
		context.setState(LabyrinthState.FINISH_MOVEMENT);
	}

}
