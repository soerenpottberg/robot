package parcours.level.test;

import parcours.level.SingleTaskLevel;
import parcours.task.RightTurnTask;

public class TestRightTurnLevel extends SingleTaskLevel {
	
	public TestRightTurnLevel() {
		super(new RightTurnTask());
	}

	@Override
	public String getLabel() {
		return "Test: Right Turn";
	}

}
