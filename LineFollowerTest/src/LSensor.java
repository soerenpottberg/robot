import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

/**
 * The LightSensor class is thought to be used in conjunction
 * with the PID control.
 * @author Nico Weyand
 *
 */
public class LSensor extends Sensor {
	
	static final float EWMA_ALPHA = 0.125f;
	private static final int MIDDLE_LIGHT_VALUE = 40;
	
	private LightSensor sensor;
	
	/**
	 * Creates a new Light Sensor using the specified port.
	 * @param port : The port using which the sensor is connected to the NXT.
	 */
	public LSensor(SensorPort port) {
		this( port, EWMA_ALPHA, MIDDLE_LIGHT_VALUE );
	}

	/**
	 * Creates a new Light Sensor.
	 * @param ewma_alpha : Alpha value for the Exponentially Weightened Moving
	 * Average used to smoothen the measured values.
	 * @param expected_value : Offset subtracted from any measure before returning.
	 * @param port : The port using which the sensor is connected to the NXT. 
	 */
	public LSensor(SensorPort port, float ewma_alpha, int expected_value ) {
		super(ewma_alpha, expected_value);
		this.sensor = new LightSensor(port);
	}

	/* (non-Javadoc)
	 * @see Sensor#getRawData()
	 */
	@Override
	protected int getRawData() {
		// TODO This could use real raw data for higher precision...
		return sensor.getLightValue();
	}

}
