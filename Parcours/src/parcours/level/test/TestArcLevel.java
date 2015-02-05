package parcours.level.test;

import parcours.level.base.SingleTaskLevel;
import parcours.task.ArcTask;
import parcours.task.TravelHandleCollisionTask;

public class TestArcLevel extends SingleTaskLevel {
	
	public TestArcLevel() {
		super(new ArcTask());
	}

	@Override
	public String getLabel() {
		return "Arc";
	}

}
