package parcours.level.test;

import parcours.level.base.SingleTaskLevel;
import parcours.task.GoIntoElevatorTask;

public class TestGoIntoElevator extends SingleTaskLevel {
	
	public TestGoIntoElevator() {
		super(new GoIntoElevatorTask());
	}

	@Override
	public String getLabel() {
		return "Go Into Elevator";
	}

}
