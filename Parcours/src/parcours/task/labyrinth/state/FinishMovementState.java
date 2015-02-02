package parcours.task.labyrinth.state;

import parcours.task.labyrinth.LabyrinthContext;

public class FinishMovementState extends LabyrinthStateBase {
	
	public String name() {
		return "FinMov";
	}

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.isMoving()) {
			return;
		}
		
		context.forward();
		context.setState(LabyrinthState.NORMAL);
	}

}
