package parcours.task.labyrinth.state;

import parcours.task.labyrinth.LabyrinthContext;

public class End90DegreeRightTurn extends LabyrinthStateBase {

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.isMoving()) {
			return;
		}
		
		context.travel(context.getAfterCuttingEdge());
		context.setState(LabyrinthState.FINISH_MOVEMENT);
	}

}
