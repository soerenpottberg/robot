package parcours.level;

import parcours.level.base.SingleTaskLevel;
import parcours.task.FollowRightWallTaskStateFull;
import parcours.task.labyrinth.config.MainLabyrinthConfiguration;


public class Labyrinth extends SingleTaskLevel {
	
	public Labyrinth() {
		super(new FollowRightWallTaskStateFull(new MainLabyrinthConfiguration(), true));
	}

	public Labyrinth(boolean detectEnd) {
		super(new FollowRightWallTaskStateFull(new MainLabyrinthConfiguration(), detectEnd));
	}

	@Override
	public String getLabel() {
		return "Labyrinth";
	}

}
