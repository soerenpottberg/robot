package parcours.utils;
import lejos.nxt.LightSensor;

/**
 * The LightSensor class is thought to be used in conjunction
 * with the PID control.
 * @author Nico Weyand
 *
 */
public class LightSensorEvaluation extends SensorEvaluation {
	private LightSensor sensor;
	
	/**
	 * Creates a new Light Sensor Evaluation.
	 * @param alpha : Alpha value for the Exponentially Weightened Moving
	 * Average used to smoothen the measured values.
	 * @param targetValue : Offset subtracted from any measure before returning.
	 * @param n : choose a value bigger than 10!
	 */
	public LightSensorEvaluation(float alpha, float targetValue, int n ) {
		super(alpha, targetValue, n);
		this.sensor = RobotDesign.lightSensor;
	}

	@Override
	protected int getSensorData() {
		// TODO This could use real raw data for higher precision...
		return sensor.getLightValue();
	}

}
