package parcours.level;

import parcours.task.Task;

public abstract class SingleTaskLevel extends Level {
	
	private static final int TASK_COUNT = 1;
	private Task task;
	
	public SingleTaskLevel(Task task) {
		this.task = task;
	}

	@Override
	public Task[] getTasks() {
		Task[] tasks = new Task[TASK_COUNT];
		tasks[0] = task;
		return tasks;
	}

}
