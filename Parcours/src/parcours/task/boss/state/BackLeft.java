package parcours.task.boss.state;

import parcours.task.boss.BossContext;

public class BackLeft extends BossStateBase {

	@Override
	public void handleNoButtonIsPressed(BossContext context) {
		if(context.isMoving()) {
			return;
		}
		turn90degreeRightAndTravel(context);
	}

	@Override
	public void handleButtonPressed(BossContext context) {
		turn90degreeRightAndTravel(context);
	}

	private void turn90degreeRightAndTravel(BossContext context) {
		context.rotate(-90, false);
		context.travel(50);
		context.setState(BossState.LEFT);
	}

}
