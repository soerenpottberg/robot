package parcours.level.test;

import parcours.level.base.SingleTaskLevel;
import parcours.task.RightTurnTask;

public class TestRightTurnLevel extends SingleTaskLevel {
	
	public TestRightTurnLevel() {
		super(new RightTurnTask());
	}

	@Override
	public String getLabel() {
		return "Right Turn";
	}

}
