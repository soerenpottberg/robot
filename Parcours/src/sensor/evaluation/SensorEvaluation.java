package sensor.evaluation;

/**
 * The Sensor class provides an interface for sensors to be used with
 * the PID control.
 * @author Nico Weyand
 */
public abstract class SensorEvaluation {
	private float alpha;
	private int targetValue;
	private float ewma;
	
	/**
	 * Creates a new Sensor with support for value smooting based on
	 * an Exponentially Weightened Moving Average (EWMA).
	 * @param alpha weighting decrease
	 * @param targetValue
	 */
	public SensorEvaluation(float alpha, int targetValue) {
		this.alpha = alpha;
		this.targetValue = targetValue;
	}
	
	public float getWeightingDecrease() {
		return alpha;
	}
	public void setWeightingDecrease(float alpha) {
		this.alpha = alpha;
	}
	
	public int getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(int targetValue) {
		this.targetValue = targetValue;
	}
	
	protected abstract int getSensorData();
	
	/**
	 * Returns an exponentially smoothed sensor data.
	 * @return The sensor data reduced by an offset of expected_value.
	 */
	public float measureError() {
		caclulateEWMA();
		return calculateError();
	}

	private void caclulateEWMA() {
		ewma = ( 1 - alpha ) * ewma + alpha * getSensorData();
	}

	private float calculateError() {
		return ewma - targetValue;
	}
}
