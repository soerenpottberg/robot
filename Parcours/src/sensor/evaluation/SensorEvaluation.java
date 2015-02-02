package sensor.evaluation;

import utils.EWMA;

/**
 * The Sensor class provides an interface for sensors to be used with
 * the PID control.
 * @author Nico Weyand
 */
public abstract class SensorEvaluation {
	private EWMA ewma;
	private float targetValue;
	
	private int n;
	private float lastMeasures[];
	private int it = 0;
	private float avg;
	
	private float lastSlidingAvgResults[];
	private long  lastSlidingAvgTimes[];
	
	/**
	 * Creates a new Sensor with support for value smooting based on
	 * an Exponentially Weightened Moving Average (EWMA).
	 * @param alpha weighting decrease
	 * @param targetValue
	 * @param n : The number of values to store for the sliding average. Must be >= 1!
	 */
	public SensorEvaluation(float alpha, float targetValue, int n) {
		this.ewma         = new EWMA(alpha, targetValue);
		this.targetValue  = targetValue;
		this.n            = n;
		this.lastMeasures = new float[n];
		
		avg                   = 0; // sliding avg of measure - targetValue
		lastSlidingAvgResults = new float[n];
		lastSlidingAvgTimes   = new long[n];
		
		long j = System.currentTimeMillis() - n - 2;
		for (int i = 0; i < n; ++i) {
			lastMeasures[i] =0;
			lastSlidingAvgResults[i] = 0;
			lastSlidingAvgTimes[i] = ++j;
		}
	}
	
	public float getTargetValue() {
		return targetValue;
	}
	
	protected abstract int getSensorData();
	
	/**
	 * Returns an exponentially smoothed sensor data.
	 * @return The sensor data reduced by an offset of expected_value.
	 */
	public float measureError() {
		int currentValue = getSensorData();
		
		avg -= lastMeasures[it];
		lastMeasures[it] = ( currentValue - targetValue ) / n;
		avg += lastMeasures[it];
		lastSlidingAvgResults[it] = avg;
		lastSlidingAvgTimes[it] = System.currentTimeMillis();
		it %= n;
		
		return ewma.addValue( currentValue ) - targetValue;
	}
	
	public float slidingAverage() {
		return avg;
	}
	
	public float[] pt3Val = new float[3];
	public long[] pt3Time = new long[3];
	
	/**
	 * Fills pt3Val and pt3Time with values for easy access.
	 */
	public void get3Pt() {
		// most recent value
		pt3Time[2] = lastSlidingAvgTimes[it];
		pt3Val[2] = lastSlidingAvgResults[it];
		
		// value in the middle between most recent and last
		int it_tmp = ( it + n / 2 ) % n;
		pt3Time[1] = lastSlidingAvgTimes[it_tmp];
		pt3Val[1] = lastSlidingAvgResults[it_tmp];
		
		// oldest / last value
		it_tmp = ( it + n - 1 ) % n;
		pt3Time[0] = lastSlidingAvgTimes[it_tmp];
		pt3Val[0] = lastSlidingAvgResults[it_tmp];
	}
}
