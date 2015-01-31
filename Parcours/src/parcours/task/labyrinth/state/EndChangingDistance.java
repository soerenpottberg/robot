package parcours.task.labyrinth.state;

public class EndChangingDistance extends LabyrinthStateBase {

	private int turnAngle;

	public EndChangingDistance(int turnAngle) {
		this.turnAngle = turnAngle;
	}

	

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if(context.isMoving()) {
			return;
		}
		
		context.rotate(turnAngle);
		context.setState(LabyrinthState.FINISH_MOVEMENT);
	}

}
