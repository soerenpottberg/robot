package parcours.level.test;

import parcours.level.base.SingleTaskLevel;
import parcours.task.UTurnTask;

public class TestUTurnLevel extends SingleTaskLevel {
	
	public TestUTurnLevel() {
		super(new UTurnTask());
	}

	@Override
	public String getLabel() {
		return "U-Turn";
	}

}
