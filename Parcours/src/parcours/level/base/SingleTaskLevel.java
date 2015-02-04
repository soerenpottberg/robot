package parcours.level.base;

import java.util.ArrayList;
import java.util.List;

import parcours.task.ControllerTask;
import parcours.task.Task;

public abstract class SingleTaskLevel extends Level {
	
	private ControllerTask task;
	
	public SingleTaskLevel(ControllerTask task) {
		this.task = task;
	}
	
	@Override
	public final List<Task> createTaskList() {
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(task);
		return taskList;
	}

}
