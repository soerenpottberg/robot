package parcours.detector;

public class LapsedTimeDetector {
	private long tDetection;
	private boolean hasDetected = false;
	
	public LapsedTimeDetector(long tDetectMs) {
		tDetection = System.currentTimeMillis() + tDetectMs;
	}
	
	public boolean hasDetected() {
		return hasDetected || check();
	}
	
	private boolean check() {
		final long tNow = System.currentTimeMillis();
		hasDetected = tNow > tDetection;
		return hasDetected;
	}

}
