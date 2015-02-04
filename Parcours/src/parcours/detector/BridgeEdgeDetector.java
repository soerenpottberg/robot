package parcours.detector;

import lejos.nxt.LightSensor;
//import parcours.debug.*;
import parcours.utils.EWMA;
import parcours.utils.RobotDesign;

/**
 * This detects a bridge edge if one is located beneath the light sensor.
 * Note that this class is meant for use in an environment with very high
 * refresh rates, so don't use it within a timed loop.
 */
public class BridgeEdgeDetector {

		private static final int ARM_DETECTOR_DELAY = 1000;
		
		private static final int EDGE_VALUE_INTERVAL = RobotDesign.BRIDGE_RAW - RobotDesign.EMPTY_SPACE_RAW;
		private static final int BRIDGE_EDGE_DETECTION_THRESHOLD =
				RobotDesign.EMPTY_SPACE_RAW + (int)(0.80f * EDGE_VALUE_INTERVAL);
		
		private static final float EWMA_ALPHA = 0.7f;
		private static final int DETECTION_TIME_MS = 45;
		
		//private DebugOutput out;
		//private TimingDebug timingDebug;
		
		private long tLastCycleStart;
		private long tCurrentDetectionIntervalLength = 0;
		
		private EWMA ewma;
		private LightSensor lightSensor;
		
		private LapsedTimeDetector initialTimingDelay;

		public BridgeEdgeDetector() {
			initialTimingDelay = new LapsedTimeDetector( ARM_DETECTOR_DELAY );
			
			// tLastCycleStart will only be used after the initial interval has passed
			// and it is updated to the current value once per cycle in the meantime.
			tLastCycleStart = System.currentTimeMillis();
			
			// Initialize ewma with bridge color (reduces risk of early detection)
			ewma = new EWMA( EWMA_ALPHA, RobotDesign.BRIDGE_RAW );
			lightSensor = RobotDesign.lightSensor;
			
			// for debugging purposes only:
			//out = new DebugOutput();
			//timingDebug = new TimingDebug(out, 10, 0, 1);
		}

		public boolean hasDetected() {
			final long tNow = System.currentTimeMillis();
			initialTimingDelay.arm();
			
			// after the timer has elapsed, we start to take measures (e.g. detector armed)
			if ( initialTimingDelay.hasDetected() ) {
				final long tDifference = tNow - tLastCycleStart;
				tLastCycleStart = tNow;
				
				// debug output:
				//timingDebug.triggerCycle();
				
				final int smoothedMeasureValue = (int)(ewma.addValue( lightSensor.getLightValue() ));
				
				// if value below the threshold: we assume hit
				if ( smoothedMeasureValue < BRIDGE_EDGE_DETECTION_THRESHOLD ) {
					tCurrentDetectionIntervalLength += tDifference;
				} else {
					tCurrentDetectionIntervalLength = 0;
				}

				return tCurrentDetectionIntervalLength > DETECTION_TIME_MS;
			} 
			
			tLastCycleStart = tNow;
			return false;
		}
}
