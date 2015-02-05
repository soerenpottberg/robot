package parcours.task.boss.state;

import parcours.task.boss.BossContext;

public class LeftState extends BossStateBase {

	@Override
	public String name() {
		return "Left state";
	}

	@Override
	public void handleButtonPressed(BossContext context) {
		context.getPilot().arc(50, -90);
		context.setState(BossState.START_AVOID_LEFT);	
	}

}
