
/**
 * The Sensor class provides an interface for sensors to be used with
 * the PID control.
 * @author Nico Weyand
 */
public abstract class Sensor {
	private float ewma_alpha;
	private int target_value;
	private float ewma;
	
	@SuppressWarnings("unused")
	private Sensor(){}
	
	/**
	 * Creates a new Sensor with support for value smooting based on
	 * an Exponentially Weightened Moving Average (EWMA).
	 * @param ewma_alpha
	 * @param target_value
	 */
	public Sensor(float ewma_alpha, int target_value) {
		this.ewma_alpha = ewma_alpha;
		this.target_value = target_value;
	}
	
	public float getEwmaAlpha() {
		return ewma_alpha;
	}
	public void setEwmaAlpha(float ewma_alpha) {
		this.ewma_alpha = ewma_alpha;
	}
	
	public int getTargetValue() {
		return target_value;
	}
	public void setTargetValue(int target_value) {
		this.target_value = target_value;
	}
	
	protected abstract int getRawData();
	
	/**
	 * Returns an exponentially smoothed sensor data.
	 * @return The sensor data reduced by an offset of expected_value.
	 */
	public float measure() {
		ewma = ( 1 - ewma_alpha ) * ewma + ewma_alpha * getRawData();
		return ewma - target_value;
	}
}
