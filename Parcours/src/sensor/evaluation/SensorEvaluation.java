package sensor.evaluation;

import utils.EWMA;

/**
 * The Sensor class provides an interface for sensors to be used with
 * the PID control.
 * @author Nico Weyand
 */
public abstract class SensorEvaluation {
	private EWMA ewma;
	private int targetValue;
	
	/**
	 * Creates a new Sensor with support for value smooting based on
	 * an Exponentially Weightened Moving Average (EWMA).
	 * @param alpha weighting decrease
	 * @param targetValue
	 */
	public SensorEvaluation(float alpha, int targetValue) {
		this.ewma = new EWMA(alpha, targetValue);
		this.targetValue = targetValue;
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
		return ewma.addValue(getSensorData()) - targetValue;
	}
}
