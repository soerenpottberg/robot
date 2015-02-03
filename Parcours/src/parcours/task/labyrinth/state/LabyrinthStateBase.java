package parcours.task.labyrinth.state;

import parcours.task.labyrinth.LabyrinthContext;

public abstract class LabyrinthStateBase {
	
	public abstract String name();

	public void handleBothButtonsPressed(LabyrinthContext context,
			int distance) {
		context.travel(context.getBackwardBothButton());
		context.setState(LabyrinthState.START_90_DEGREE_LEFT_TURN);
	}

	public void handleLeftButtonPressed(LabyrinthContext context,
			int distance) {
		if (distance < context.getNoWallDistance()) {
			context.travel(context.getBackwardLeftButtonWall());
			context.setState(LabyrinthState.START_90_DEGREE_LEFT_TURN);
		} else {
			context.travel(context.getBackwardLeftButton());
			context.setState(LabyrinthState.START_GOING_LEFT);
		}
	}

	public void handleRightButtonPressed(LabyrinthContext context,
			int distance) {
		context.travel(context.getBackwardRightButton(), true);
		context.setState(LabyrinthState.START_GOING_RIGHT);
	}

	public abstract void handleNoButtonIsPressed(LabyrinthContext context,
			int distance);

}
