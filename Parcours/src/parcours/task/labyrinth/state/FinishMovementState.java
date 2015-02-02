package parcours.task.labyrinth.state;

import lejos.nxt.Motor;
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
		Motor.A.flt(true);
		Motor.B.flt(true);
		
		context.forward();
		context.setState(LabyrinthState.NORMAL);
	}

}
