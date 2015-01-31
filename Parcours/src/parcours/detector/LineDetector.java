package parcours.detector;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import utils.EWMA;
import debug.DebugOutput;
import debug.TimingDebug;

/**
 * This detects a single line if one is located beneath the light sensor.
 * Note that this class is meant for use in an environment with very high
 * refresh rates, so don't use it within a timed loop.
 * @author Nico Weyand
 *
 */
public class LineDetector {
	private static final int CYCLE_TIME_STARTING_VALUE = 1000;
	private static final int WHITE_LINE_DETECTION_THRESHOLD = 39;
	private static final float EWMA_ALPHA = 0.7f;
	private static final int DETECTION_TIME_MS = 50;
	private static final float BLACK = 0;
	
	private DebugOutput out;
	private TimingDebug timingDebug;
	
	private long tLastCycleStart;
	
	private float tCurrentDetectionIntervalLength = 0.0f;
	private EWMA ewma;
	private LightSensor lightSensor;

	public LineDetector() {
		// This makes sure that the detector generates no hit after its initialization if the first measure is by chance a hit.
		tLastCycleStart = System.currentTimeMillis() + CYCLE_TIME_STARTING_VALUE; // TODO: It might be removed now, see below.
		
		// Initialize ewma with black
		ewma = new EWMA(EWMA_ALPHA, BLACK);
		lightSensor = new LightSensor(SensorPort.S1);
		
		// for debugging purposes only:
		out = new DebugOutput();
		timingDebug = new TimingDebug(out, 10, 0, 1);
	}

	public boolean hasDetected() {
		// get the current cycle length (smoothed)
		final long tNow = System.currentTimeMillis();
		final long tDifference = tNow - tLastCycleStart;
		tLastCycleStart = tNow;
		
		// debug output:
		timingDebug.triggerCycle();
		
		ewma.addValue(lightSensor.getLightValue());
		if (ewma.getValue() > WHITE_LINE_DETECTION_THRESHOLD ) {
			tCurrentDetectionIntervalLength += tDifference;
		} else {
			tCurrentDetectionIntervalLength = 0;
		}

		return tCurrentDetectionIntervalLength > DETECTION_TIME_MS;
	}
}
