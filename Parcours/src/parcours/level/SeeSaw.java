package parcours.level;

import java.util.ArrayList;
import java.util.List;

import parcours.level.base.Level;
import parcours.task.FollowLineSpeedTask;
import parcours.task.base.Task;


public class SeeSaw extends Level {
	
	@Override
	public String getLabel() {
		return "See Saw";
	}

	@Override
	public List<Task> createTaskList() {
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(new FollowLineSpeedTask());
		return taskList;
	}

}
