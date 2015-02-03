package parcours.level;

import parcours.level.base.Level;
import parcours.task.FollowRightWallTaskStateFull;
import parcours.task.ControllerTask;
import parcours.task.labyrinth.config.StartLabyrinthConfiguration;


public class StartLevel extends Level {
	
	private static int TASK_COUNT = 1;
	private static ControllerTask[] tasks = new ControllerTask[TASK_COUNT];
	
	static {
		int i = 0;
		tasks[i++] = new FollowRightWallTaskStateFull(new StartLabyrinthConfiguration());
	}
	
	@Override
	public String getLabel() {
		return "Start Level";
	}

	@Override
	public ControllerTask[] getTasks() {
		return tasks;
	}

}
