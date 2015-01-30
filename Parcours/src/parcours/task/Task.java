package parcours.task;

public abstract class Task {

	public void run() {
		init();

		do {
			control();
		} while (!abort());
		tearDown();
	}

	protected abstract void init();

	protected abstract void control();

	protected abstract boolean abort();

	protected abstract void tearDown();

}
