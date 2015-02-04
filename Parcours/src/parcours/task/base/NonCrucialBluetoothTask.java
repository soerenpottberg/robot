package parcours.task.base;

import java.io.IOException;

public abstract class NonCrucialBluetoothTask implements Task {
	
	@Override
	public void run() {
		try {
			communicate();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	protected abstract void communicate() throws IOException;


}
