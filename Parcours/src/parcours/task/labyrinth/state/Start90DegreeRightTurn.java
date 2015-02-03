package parcours.task.labyrinth.state;

import lejos.nxt.Sound;
import parcours.task.labyrinth.LabyrinthContext;

public class Start90DegreeRightTurn extends LabyrinthStateBase {
	
	public String name() {
		return "S90RT";
	}

	private static final int RIGHT_ANGLE = -90;
	
	@Override
	public void handleBothButtonsPressed(LabyrinthContext context, int distance) {
		context.travel(context.getBackwardCrashed());
	}
	
	@Override
	public void handleLeftButtonPressed(LabyrinthContext context, int distance) {
		context.travel(context.getBackwardCrashed());
	}
	
	@Override
	public void handleRightButtonPressed(LabyrinthContext context, int distance) {
		context.travel(context.getBackwardCrashed());
	}

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		Sound.beep();
		if(context.isMoving()) {
			return;
		}
		
		context.rotate(RIGHT_ANGLE);
		context.setState(LabyrinthState.END_90_DEGREE_RIGHT_TURN);
	}

}
