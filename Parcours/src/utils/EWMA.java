package utils;

/**
 * This class implements an exponential weightenend moving average (EWMA) algorithm.
 * @author Nico Weyand
 *
 */
public class EWMA {
	private float alpha;
	private float ewma;
	
	/**
	 * @param alpha : The weightening value
	 * @param initialValue : The initial value of the average. Setting this to a
	 * value close to the center of the expected range of input values allows for
	 * your ewma to converge faster with your measures.
	 */
	public EWMA(float alpha, float initialValue) {
		this.alpha = alpha;
		this.ewma  = initialValue;
	}
	
	public float getWeightingDecrease() {
		return alpha;
	}
	public void setWeightingDecrease(float alpha) {
		this.alpha = alpha;
	}
	
	/**
	 * Adds a new value to the ewma. 
	 * @param value : The value to be added.
	 * @return The current value of the ewma.
	 */
	public float addValue(float value) {
		ewma = ( 1 - alpha ) * ewma + alpha * value;
		return ewma;
	}
	
	/**
	 * @return The current value of the ewma.
	 */
	public float getValue() {
		return ewma;
	}
}
