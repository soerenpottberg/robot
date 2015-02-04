package parcours.level;

import java.util.ArrayList;
import java.util.List;

import parcours.level.base.Level;
import parcours.task.GoIntoElevatorTask;
import parcours.task.base.Task;


public class Elevator extends Level {

	@Override
	public String getLabel() {
		return "Elevator";
	}

	@Override
	public List<Task> createTaskList() {
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(new GoIntoElevatorTask());
		return taskList;
	}

}
