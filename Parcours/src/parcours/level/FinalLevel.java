package parcours.level;

import java.util.ArrayList;
import java.util.List;

import parcours.level.base.Level;
import parcours.task.BossTask;
import parcours.task.base.Task;


public class FinalLevel extends Level {

	@Override
	public String getLabel() {
		return "Final Level";
	}

	@Override
	public List<Task> createTaskList() {
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(new BossTask());
		return taskList;
	}

}
