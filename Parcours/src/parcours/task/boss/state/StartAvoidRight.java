package parcours.task.boss.state;

import parcours.task.boss.BossContext;

public class StartAvoidRight extends BossStateBase {

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleButtonPressed(BossContext context) {
	
	}

	@Override
	public void handleNoButtonIsPressed(BossContext context) {
		if(context.isMoving()) {
			return;
		}
		
		context.rotate(-90,false);
		context.setState(BossState.LEFT);
	}

}
