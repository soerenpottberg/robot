package parcours.task.boss.state;

import parcours.task.boss.BossContext;

public abstract class BossStateBase {
	
	public abstract String name();

	/*public void handleBothButtonsPressed(ElevatorContext context,
			int distance) {
		
	}*/

	public abstract void handleButtonPressed(BossContext context);
	
	public void handleNoButtonIsPressed(BossContext context){
		context.forward();
	}

}
