package parcours.task.boss.state;

//import lejos.nxt.Motor;
import parcours.task.boss.BossContext;

public class FinishMovementState extends BossStateBase {
	
	public String name() {
		return "FinMov";
	}

	/*@Override
	public void handleNoButtonIsPressed(ElevatorContext context, int distance) {
		if(context.isMoving()) {
			return;
		}
		Motor.A.flt(true);
		Motor.B.flt(true);
		
		context.forward();
		context.setState(ElevatorState.NORMAL);
	}*/

	@Override
	public void handleNoButtonIsPressed(BossContext context) {
		// TODO Auto-generated method stub
		/*if(context.isMoving()) {
			return;
		}
		context.forward();
		context.setState(ElevatorState.NORMAL);		*/
	}

	

	@Override
	public void handleButtonPressed(BossContext context) {
		if(context.isMoving()) {
			return;
		}
		context.rotate(10);
		}
	}
