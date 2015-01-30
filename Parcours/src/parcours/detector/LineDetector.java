package parcours.detector;

import sensor.evaluation.LightSensorEvaluation;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

/**
 * This detects a single line if one is located beneath the light sensor.
 * Note that this class is meant for use in an environment with very high
 * refresh rates, so don't use it within a timed loop.
 * @author Nico Weyand
 *
 */
public class LineDetector {
	
	private static final int WHITE_LINE_THRESHOLD = 50;
	private static final float EWMA_ALPHA = 0.1f;
	private static final int SAMPLING_COUNT = 25;

	private LightSensorEvaluation evaluation;
	private int count = 0;

	public LineDetector() {
		evaluation = new LightSensorEvaluation( SensorPort.S1, EWMA_ALPHA, WHITE_LINE_THRESHOLD );
	}

	public boolean hasDetected() {
		if ( evaluation.measureError() > 0 ) {
			++count;
		} else {
			count = 0;
		}
		
		return count > SAMPLING_COUNT;
	}
}
