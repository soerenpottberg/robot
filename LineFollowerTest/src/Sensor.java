
/**
 * The Sensor class provides an interface for sensors to be used with
 * the PID control.
 * @author Nico Weyand
 */
public abstract class Sensor {
	private float ewma_alpha;
	private int expected_value;
	private float ewma;
	
	@SuppressWarnings("unused")
	private Sensor(){}
	
	/**
	 * Creates a new Sensor with support for value smooting based on
	 * an Exponentially Weightened Moving Average (EWMA).
	 * @param ewma_alpha
	 * @param expected_value
	 */
	public Sensor(float ewma_alpha, int expected_value) {
		this.ewma_alpha = ewma_alpha;
		this.expected_value = expected_value;
	}
	
	public float getEwma_alpha() {
		return ewma_alpha;
	}
	public void setEwma_alpha(float ewma_alpha) {
		this.ewma_alpha = ewma_alpha;
	}
	
	public int getExpected_value() {
		return expected_value;
	}
	public void setExpected_value(int expected_value) {
		this.expected_value = expected_value;
	}
	
	protected abstract int getRawData();
	
	/**
	 * Returns an exponentially smoothed sensor data.
	 * @return The sensor data reduced by an offset of expected_value.
	 */
	public float measure() {
		ewma = ( 1 - ewma_alpha ) * ewma + ewma_alpha * getRawData();
		return ewma - expected_value;
	}

}
