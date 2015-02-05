package parcours.task.boss.state;

import parcours.task.boss.BossContext;
import parcours.task.labyrinth.state.LabyrinthState;

public class StartAvoidRight extends BossStateBase {

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleButtonPressed(BossContext context) {
		//context.setState(BossState.START_AVOID_LEFT);
		
	}

	@Override
	public void handleNoButtonIsPressed(BossContext context) {
		if(context.isMoving()) {
			return;
		}
		
		context.rotate(-90);
		context.setState(BossState.LEFT);
		
	}

}
