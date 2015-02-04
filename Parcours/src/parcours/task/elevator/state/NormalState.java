package parcours.task.elevator.state;

import parcours.task.elevator.ElevatorContext;

public class NormalState extends ElevatorStateBase {
	
	public String name() {
		return "Normal";
	}

	@Override
	public void handleLeftButtonPressed(ElevatorContext context) {
		context.getPilot().rotate(-10);
		context.setState(ElevatorState.FINISH_MOVEMENT);
	}

	@Override
	public void handleRightButtonPressed(ElevatorContext context) {
		context.getPilot().rotate(15);
		context.setState(ElevatorState.FINISH_MOVEMENT);
		
	}

	@Override
	public void handleNoButtonIsPressed(ElevatorContext context) {
		
	}


}
