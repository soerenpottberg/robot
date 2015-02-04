package parcours.task;

import parcours.task.base.ControllerTask;
import parcours.utils.EWMA;
import parcours.utils.RobotDesign;
import lejos.nxt.LightSensor;
import lejos.nxt.NXTMotor;
import lejos.util.Delay;

public class FollowLineStraightTask extends ControllerTask {
	
	private static final long MS_COMPLETE_CYCLE_TIME = 12;
	private static final long MS_MEASURE_CYCLE_TIME  = 3;

	private static final int BASE_POWER = 50;

	private static final float Kp = 0.10f;
	private static final float Ki = 0.01f;
	private static final float Kd = 0.00f;

	private LightSensor light;
	private NXTMotor motorA;
	private NXTMotor motorB;

	private float errorIntegrated;
	private float errorDerivated;
	private float lastError;
	private int lastPowerMotorA;
	private int lastPowerMotorB;
	@SuppressWarnings("unused")
	private long lastTime;
	private long nextCycleCompletion;
	private EWMA ewma;
	
	private final float targetColor = RobotDesign.BLACK_RAW +
			0.9f * ((RobotDesign.SILVER_RAW - RobotDesign.BLACK_RAW) / 2);
	
	@Override
	protected void init() {
		ewma = new EWMA(0.125f, targetColor);
		motorA = RobotDesign.unregulatedMotorRight;
		motorB = RobotDesign.unregulatedMotorLeft;
		light = RobotDesign.lightSensor;
		motorA.setPower(BASE_POWER);
		motorB.setPower(BASE_POWER);
		motorA.forward();
		motorB.forward();
		errorDerivated = 0;
		lastError = 0;
		lastPowerMotorA = 0;
		lastPowerMotorB = 0;
		lastTime = System.currentTimeMillis();
		
		nextCycleCompletion = System.currentTimeMillis();
	}

	@Override
	protected void control() {
		nextCycleCompletion += MS_COMPLETE_CYCLE_TIME;
		
		final float lightValue = measureLight();
		final float error = calculateError(lightValue);
		integrateError(error);
		deriveError(error);
		
		final int compensation = pid(error);

		final int powerMotorA = BASE_POWER - compensation;
		final int powerMotorB = BASE_POWER + compensation;
		RobotDesign.setMotorPower(motorA, powerMotorA, lastPowerMotorA);
		RobotDesign.setMotorPower(motorB, powerMotorB, lastPowerMotorB);
		
		final long tNow = System.currentTimeMillis();
		// System.out.println(time - lastTime);
		
		while ( System.currentTimeMillis() + MS_MEASURE_CYCLE_TIME < nextCycleCompletion ) {
			measureLight();
			// Wait for a given number of ms before continuing.
			Delay.msDelay( MS_MEASURE_CYCLE_TIME );
		}
		
		lastTime = tNow;
		lastError = error;
		lastPowerMotorA = powerMotorA;
		lastPowerMotorB = powerMotorB;
	}

	private int pid(float error) {
		return (int) (Kp * error + Ki * errorIntegrated  + Kd * errorDerivated);
	}

	private float measureLight() {
		return ewma.addValue( light.getNormalizedLightValue() );
	}

	private float calculateError(float lightValue) {
		return lightValue - targetColor;
	}

	private void deriveError(float error) {
		errorDerivated = (error - lastError);
	}
	
	private void integrateError(float error) {
		errorIntegrated = (2f / 3f * errorIntegrated) + error;
		if (error > 0) {
			// Sound.beep();
			errorIntegrated += error;
		}
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