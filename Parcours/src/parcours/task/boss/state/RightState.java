package parcours.task.boss.state;

import parcours.task.boss.BossContext;

public class RightState extends BossStateBase {

	@Override
	public String name() {
		return "Right state";
	}

	@Override
	public void handleButtonPressed(BossContext context) {
		context.getPilot().arc(-20, 90);
		context.setState(BossState.START_AVOID_RIGHT);
		
	}

	@Override
	public void handleNoButtonIsPressed(BossContext context) {
		context.forward();		
	}

}
