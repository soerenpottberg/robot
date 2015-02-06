package parcours.task.boss.state;

import parcours.task.boss.BossContext;

public class BackLeft extends BossStateBase {

	@Override
	public void handleNoButtonIsPressed(BossContext context) {
		if(context.isMoving()) {
			return;
		}
		context.rotate(90, false);
		context.travel(50);
		context.setState(BossState.LEFT);
	}

	@Override
	public void handleButtonPressed(BossContext context) {
		context.travel(-30, false);
	}

}
