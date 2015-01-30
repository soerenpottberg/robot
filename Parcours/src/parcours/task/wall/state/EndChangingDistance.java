package parcours.task.wall.state;

public class EndChangingDistance extends WallStateBase {

	private int turnAngle;

	public EndChangingDistance(int turnAngle) {
		this.turnAngle = turnAngle;
	}

	

	@Override
	public void handleNoButtonIsPressed(WallStateContext context, int distance) {
		if(context.getPilot().isMoving()) {
			return;
		}
		
		context.getPilot().rotate(turnAngle, true);
		context.setState(WallState.FINISH_MOVEMENT);
	}

}
