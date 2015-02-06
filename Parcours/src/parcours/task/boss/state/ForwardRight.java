package parcours.task.boss.state;

import parcours.task.boss.BossContext;

public class ForwardRight extends BossStateBase {

	@Override
	public void handleButtonPressed(BossContext context) {
		context.travel(-30, false);
		context.setState(BossState.BACK_RIGHT);
	}

	@Override
	public void handleNoButtonIsPressed(BossContext context) {
	}

}
