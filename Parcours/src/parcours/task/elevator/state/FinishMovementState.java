package parcours.task.elevator.state;

//import lejos.nxt.Motor;
import parcours.task.elevator.ElevatorContext;

public class FinishMovementState extends ElevatorStateBase {
	
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
	public void handleNoButtonIsPressed(ElevatorContext context) {
		// TODO Auto-generated method stub
		if(context.isMoving()) {
			return;
		}
		context.forward();
		context.setState(ElevatorState.NORMAL);		
	}

	@Override
	public void handleLeftButtonPressed(ElevatorContext context) {
		if(context.isMoving()) {
			return;
		}
		context.rotate(-15);
	}

	@Override
	public void handleRightButtonPressed(ElevatorContext context) {
		if(context.isMoving()) {
			return;
		}
		context.rotate(10);
		}
	}
