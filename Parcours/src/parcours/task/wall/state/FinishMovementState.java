package parcours.task.wall.state;

public class FinishMovementState extends LabyrinthStateBase {



	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().forward();
		context.setState(LabyrinthState.NORMAL);
	}

}
