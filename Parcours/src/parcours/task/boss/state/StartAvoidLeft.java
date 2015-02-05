package parcours.task.boss.state;

import parcours.task.boss.BossContext;

public class StartAvoidLeft extends BossStateBase {

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleButtonPressed(BossContext context) {
		if(context.isMoving()) {
			return;
		}
		
		context.rotate(90);
		context.setState(BossState.RIGHT);
		
	}

	@Override
	public void handleNoButtonIsPressed(BossContext context) {
		
		
	}

}
