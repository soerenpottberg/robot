package sensor.evaluation;
import utils.RobotDesign;
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
	 */
	public LightSensorEvaluation(float alpha, int targetValue ) {
		super(alpha, targetValue);
		this.sensor = RobotDesign.lightSensor;
	}

	@Override
	protected int getSensorData() {
		// TODO This could use real raw data for higher precision...
		return sensor.getLightValue();
	}

}
