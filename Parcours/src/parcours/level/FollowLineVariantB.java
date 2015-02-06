package parcours.level;

import parcours.level.base.SingleTaskLevel;
import parcours.task.FollowLineExtremeTask;

public class FollowLineVariantB extends SingleTaskLevel {

	public FollowLineVariantB() {
		super(new FollowLineExtremeTask());
	}

	@Override
	public String getLabel() {
		return "Follow Line (B)";
	}

}
