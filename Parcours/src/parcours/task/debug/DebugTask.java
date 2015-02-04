package parcours.task.debug;

import lejos.util.Delay;
import parcours.task.base.Task;

public class DebugTask implements Task {

	private final String message;

	public DebugTask(String message) {
		this.message = message;
	}

	@Override
	public void run() {
		System.out.println(message);
		Delay.msDelay(1000);
	}

}
