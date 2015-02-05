package parcours.level;

import parcours.level.base.SingleTaskLevel;
import parcours.task.FollowLineSpeedTask;

public class FollowLineVariantB extends SingleTaskLevel {

	public FollowLineVariantB() {
		super(new FollowLineSpeedTask());
	}

	@Override
	public String getLabel() {
		return "Follow Line (B)";
	}

}
