package parcours.level;

import parcours.task.FollowRightWallTaskStateFull;
import parcours.level.base.Level;
import parcours.task.ControllerTask;
import parcours.task.labyrinth.config.MainLabyrinthConfiguration;


public class Labyrinth extends Level {
	
	private static int TASK_COUNT = 1;
	private static ControllerTask[] tasks = new ControllerTask[TASK_COUNT];
	
	static {
		int i = 0;
		tasks[i++] = new FollowRightWallTaskStateFull(new MainLabyrinthConfiguration());
	}

	@Override
	public String getLabel() {
		return "Labyrinth";
	}

	@Override
	public ControllerTask[] getTasks() {
		return tasks;
	}

}
