package parcours.task.labyrinth.state;

import parcours.task.labyrinth.LabyrinthContext;

public class StartGoingLeft extends LabyrinthStateBase {
	
	public String name() {
		return "SGoL";
	}

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if (context.isMoving()) {
			return;
		}

		context.rotate(context.getGoingLeftAngle());
		context.setState(LabyrinthState.FINISH_MOVEMENT);
	}

}
