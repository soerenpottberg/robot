package parcours.detector;

public class LapsedTimeDetector {
	private long tDetection;
	private boolean hasDetected = false;
	private boolean armed = false;
	
	public void arm() {
		if ( !armed )
		{
			this.armed = true;
			tDetection += System.currentTimeMillis();
		}
	}

	public LapsedTimeDetector(long tDetectMs) {
		tDetection = tDetectMs;
	}
	
	public boolean hasDetected() {
		return armed && (hasDetected || check());
	}
	
	private boolean check() {
		final long tNow = System.currentTimeMillis();
		hasDetected = tNow > tDetection;
		return hasDetected;
	}

}
