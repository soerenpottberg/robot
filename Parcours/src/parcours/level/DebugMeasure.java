package parcours.level;

import parcours.task.DebugMeasureTask;
import parcours.task.Task;


public class DebugMeasure extends Level {

	@Override
	public String getLabel() {
		return "Measure";
	}

	@Override
	public Task[] getTasks() {
		Task[] t = new Task[1];
		t[0] = new DebugMeasureTask();
		return t;
	}

}
