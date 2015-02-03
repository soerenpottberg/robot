package parcours.level.test;

import parcours.level.base.SingleTaskLevel;
import parcours.task.TravelHandleCollisionTask;

public class TestTravelLevel extends SingleTaskLevel {
	
	public TestTravelLevel() {
		super(new TravelHandleCollisionTask(100, 20));
	}

	@Override
	public String getLabel() {
		return "Travel";
	}

}
