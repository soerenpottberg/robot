package parcours.task.wall.state;

public class EndChangingDistance extends LabyrinthStateBase {

	private int turnAngle;

	public EndChangingDistance(int turnAngle) {
		this.turnAngle = turnAngle;
	}

	

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().rotate(turnAngle, true);
		context.setState(LabyrinthState.FINISH_MOVEMENT);
	}

}
