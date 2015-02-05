package parcours.task;


import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import parcours.task.base.ControllerTask;
import parcours.utils.LightSensorEvaluation;
import parcours.utils.RobotDesign;
import parcours.utils.SensorEvaluation;

public class FollowBridgeSuspensionTask extends ControllerTask {
	private static final float DEVIATION_FROM_GRAY_TARGET = 0.1f;
	private static final int BASE_SPEED = 20;

	private static final long MS_COMPLETE_CYCLE_TIME = 12;
	//private static final long MS_MEASURE_CYCLE_TIME  = 3;
	private static final long MS_REMAINING_TIME_INSUFICCIENT_WARNING_TIME = 7;

	private static final float Kp = 40;
	private static final float Ki = 05;
	//private static final int Kd = (int) (00 * ACCURACY_FACTOR);


	private SensorEvaluation lightSensor;
	private RegulatedMotor motorA;
	private RegulatedMotor motorB;
	
	private long nextCycleCompletion;

	private float errorIntegrated;
	//private int errorDerivated;
	//private int lastError;
	private int lastPowerMotorA;
	private int lastPowerMotorB;

	@Override
	protected void init() {
		DifferentialPilot pilot = RobotDesign.differentialPilot;
		pilot.setTravelSpeed(100);
		pilot.travel( 20 );
		pilot.stop();
		Motor.B.suspendRegulation();
		Motor.A.suspendRegulation();

		final float gray   = (RobotDesign.BLACK + RobotDesign.SILVER) / 2;
		final float target = gray - DEVIATION_FROM_GRAY_TARGET * (RobotDesign.SILVER - RobotDesign.BLACK);
		lightSensor = new LightSensorEvaluation(0.15f, target, 8 );
		
		motorA = RobotDesign.REGULATED_MOTOR_RIGHT;
		motorB = RobotDesign.REGULATED_MOTOR_LEFT;
		motorA.setSpeed(BASE_SPEED);
		motorB.setSpeed(BASE_SPEED);
		motorA.forward();
		motorB.forward();
		errorIntegrated = 0;
		//errorDerivated = 0;
		//lastError = 0;
		lastPowerMotorA = 0;
		lastPowerMotorB = 0;

		nextCycleCompletion = System.currentTimeMillis();
	}

	@Override
	protected void control() {
		nextCycleCompletion += MS_COMPLETE_CYCLE_TIME;
		// get at least 1 time new data from the sensor.
		final float deviation = lightSensor.measureError();

		// Warn if insufficient cycle time is detected.
		// This can be an early warning system for all kinds of problems.
		if ( nextCycleCompletion - System.currentTimeMillis()
				< MS_REMAINING_TIME_INSUFICCIENT_WARNING_TIME ) {
			Sound.beep();

			// beeping is synchronous, so without this a beep would cause an 
			// other one and so on...
			nextCycleCompletion = System.currentTimeMillis() +
					MS_COMPLETE_CYCLE_TIME;
		}

		integrateError(deviation);
		//deriveError(error);
		
		int compensation = pid(deviation);

		int powerMotorA = BASE_SPEED + compensation;
		int powerMotorB = BASE_SPEED - compensation;
		RobotDesign.setMotorSpeed(motorA, powerMotorA, lastPowerMotorA);
		RobotDesign.setMotorSpeed(motorB, powerMotorB, lastPowerMotorB);
		
		//lastError = error;
		lastPowerMotorA = powerMotorA;
		lastPowerMotorB = powerMotorB;
	}

	private int pid(float deviation) {
		return (int)(Kp * deviation  + Ki * errorIntegrated/* + Kd * errorDerivated*/);
	}

	/*private void deriveError(int error) {
		errorDerivated = (error - lastError);
	}*/

	private void integrateError(float deviation) {
		errorIntegrated = errorIntegrated + deviation;
	}

	@Override
	protected boolean abort() {
		return false;
	}

	@Override
	protected void tearDown() {
		RobotDesign.differentialPilot.stop();
	}

}
