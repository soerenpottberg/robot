package parcours.level;

import java.util.ArrayList;
import java.util.List;

import parcours.level.base.Level;
import parcours.task.FollowRightWallTaskStateFull;
import parcours.task.base.Task;
import parcours.task.labyrinth.config.StartLabyrinthConfiguration;


public class StartLevel extends Level {
	
	@Override
	public String getLabel() {
		return "Start Level";
	}

	@Override
	public List<Task> createTaskList() {
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(new FollowRightWallTaskStateFull(new StartLabyrinthConfiguration()));
		return taskList;
	}

}
