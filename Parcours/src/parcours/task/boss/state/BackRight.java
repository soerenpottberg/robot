package parcours.task.boss.state;

import parcours.task.boss.BossContext;

public class BackRight extends BossStateBase{

	@Override
	public void handleNoButtonIsPressed(BossContext context) {
		if(context.isMoving()) {
			return;
		}
		turn90degreeLeftAndTravel(context);
	}

	@Override
	public void handleButtonPressed(BossContext context) {
		turn90degreeLeftAndTravel(context);
	}

	private void turn90degreeLeftAndTravel(BossContext context) {
		context.rotate(90, false);
		context.travel(50);
		context.setState(BossState.RIGHT);
	}

}
