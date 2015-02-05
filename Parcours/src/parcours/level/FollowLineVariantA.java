package parcours.level;

import parcours.level.base.SingleTaskLevel;
import parcours.task.FollowLineSpeedTask;

public class FollowLineVariantA extends SingleTaskLevel {

	public FollowLineVariantA() {
		super(new FollowLineSpeedTask());
	}

	@Override
	public String getLabel() {
		return "Follow Line (A)";
	}

}
