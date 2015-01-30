package sensor.evaluation;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

/**
 * The LightSensor class is thought to be used in conjunction
 * with the PID control.
 * @author Nico Weyand
 *
 */
public class LightSensorEvaluation extends SensorEvaluation {
	
	static final float EWMA_ALPHA = 0.125f;
	private static final int MIDDLE_LIGHT_VALUE = 40;
	
	private LightSensor sensor;
	
	/**
	 * Creates a new Light Sensor using the specified port.
	 * @param port : The port using which the sensor is connected to the NXT.
	 */
	public LightSensorEvaluation(SensorPort port) {
		this( port, EWMA_ALPHA, MIDDLE_LIGHT_VALUE );
	}

	/**
	 * Creates a new Light Sensor.
	 * @param alpha : Alpha value for the Exponentially Weightened Moving
	 * Average used to smoothen the measured values.
	 * @param targetValue : Offset subtracted from any measure before returning.
	 * @param port : The port using which the sensor is connected to the NXT. 
	 */
	public LightSensorEvaluation(SensorPort port, float alpha, int targetValue ) {
		super(alpha, targetValue);
		this.sensor = new LightSensor(port);
	}

	@Override
	protected int getSensorData() {
		// TODO This could use real raw data for higher precision...
		return sensor.getLightValue();
	}

}
