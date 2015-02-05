package parcours.level.test;

import parcours.level.base.SingleTaskLevel;
import parcours.task.TurnTask;

public class TestRightTurnLevel extends SingleTaskLevel {
	
	public TestRightTurnLevel() {
		super(new TurnTask());
	}

	@Override
	public String getLabel() {
		return "Right Turn";
	}

}
