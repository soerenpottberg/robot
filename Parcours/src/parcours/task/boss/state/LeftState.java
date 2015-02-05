package parcours.task.boss.state;

import parcours.task.boss.BossContext;

public class LeftState extends BossStateBase {

	@Override
	public void handleButtonPressed(BossContext context) {
		context.rotate(180, false);
		context.travel(100);
		context.setState(BossState.RIGHT);
	}

	@Override
	public void handleNoButtonIsPressed(BossContext context) {
		if(context.isMoving()) {
			return;
		}
		
		context.rotate(-90, false);
		context.forward();
		context.setState(BossState.FORWARD_LEFT);
	}

}
