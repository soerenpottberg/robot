package parcours.task.elevator.state;

import parcours.task.elevator.ElevatorContext;

public abstract class ElevatorStateBase {
	
	public abstract String name();

	/*public void handleBothButtonsPressed(ElevatorContext context,
			int distance) {
		
	}*/

	public abstract void handleLeftButtonPressed(ElevatorContext context);
	public abstract void handleRightButtonPressed(ElevatorContext context);

	public abstract void handleNoButtonIsPressed(ElevatorContext context);

}
