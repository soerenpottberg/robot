package parcours.level.base;

import parcours.task.ControllerTask;

public abstract class SingleTaskLevel extends Level {
	
	private static final int TASK_COUNT = 1;
	private ControllerTask task;
	
	public SingleTaskLevel(ControllerTask task) {
		this.task = task;
	}

	@Override
	public ControllerTask[] getTasks() {
		ControllerTask[] tasks = new ControllerTask[TASK_COUNT];
		tasks[0] = task;
		return tasks;
	}

}
