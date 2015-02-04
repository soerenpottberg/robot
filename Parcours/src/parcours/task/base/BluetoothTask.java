package parcours.task.base;

import java.io.IOException;

public abstract class BluetoothTask implements Task {

	@Override
	public void run() {
		try {
			communicate();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract void communicate() throws IOException;

}
