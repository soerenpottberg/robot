package parcours.task.boss.state;

import parcours.task.boss.BossContext;

public class ForwardLeft extends BossStateBase {

	@Override
	public void handleButtonPressed(BossContext context) {
		context.travel(-20, false);
		context.rotate(180, false);
		context.travel(30);
		context.setState(BossState.BACK_LEFT);
	}

	@Override
	public void handleNoButtonIsPressed(BossContext context) {
	}

}
