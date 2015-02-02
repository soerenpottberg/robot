package parcours.task.labyrinth.state;

import parcours.task.labyrinth.LabyrinthContext;

public class Start90DegreeLeftTurn extends LabyrinthStateBase {
	
	public String name() {
		return "S90LT";
	}

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
