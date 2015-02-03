package parcours.task;

import utils.EWMA;
import utils.RobotDesign;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTMotor;
import lejos.nxt.TouchSensor;
import lejos.util.Delay;

public class FollowLineTaskRaw extends Task {
	
	private static final long MS_COMPLETE_CYCLE_TIME = 12;
	private static final long MS_MEASURE_CYCLE_TIME  = 3;

	private static final int MIDDLE_LIGHT_VALUE = 370; // 260 - 480
	private static final int BASE_POWER = 30;

	private static final int Kp = (int) (0.15 * 100);
	private static final int Ki = (int) (0.01 * 100);
	private static final int Kd = (int) (0 * 100);

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
	private TouchSensor touchSensorLeft;

	@Override
	protected void init() {
		touchSensorLeft = RobotDesign.touchSensorLeft;
		
		ewma = new EWMA(0.125f, MIDDLE_LIGHT_VALUE);
		motorA = RobotDesign.unregulatedMotorRight;
		motorB = RobotDesign.unregulatedMotorLeft;
		light = RobotDesign.lightSensor;
		motorA.setPower(BASE_POWER);
		motorB.setPower(BASE_POWER);
		motorA.forward();
		motorB.forward();
		errorIntegrated = 0;
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
		
		int lightValue = measureLight();
		ewma.addValue(lightValue);
		
		float error = calculateError(ewma.getValue());
		integrateError(error);
		deriveError(error);
		
		int compensation = pid(error);

		int powerMotorA = BASE_POWER - compensation;
		int powerMotorB = BASE_POWER + compensation;
		setMotorPower(motorA, powerMotorA, lastPowerMotorA);
		setMotorPower(motorB, powerMotorB, lastPowerMotorB);
		
		long time = System.currentTimeMillis();
		// System.out.println(time - lastTime);
		
		while (System.currentTimeMillis() + MS_MEASURE_CYCLE_TIME < nextCycleCompletion) {
			lightValue = measureLight();
			// Wait for a given number of ms before continuing.
			Delay.msDelay(MS_MEASURE_CYCLE_TIME);
		}
		
		lastTime = time;
		lastError = error;
		lastPowerMotorA = powerMotorA;
		lastPowerMotorB = powerMotorB;
	}

	private int pid(float error) {
		return (int) ((Kp * error  + Ki * errorIntegrated + Kd * errorDerivated) / 100);
	}

	private int measureLight() {
		return light.getNormalizedLightValue();
	}

	private float calculateError(float lightValue) {
		return lightValue - MIDDLE_LIGHT_VALUE;
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

	private static void setMotorPower(NXTMotor motor, int power, int oldPower) {
		int absPower = Math.abs(power);
		motor.setPower(absPower);
		if (Math.signum(power) == Math.signum(oldPower)) {
			return;
		}
		if (power > 0) {
			motor.forward();
		} else {
			motor.backward();
		}
	}

	@Override
	protected boolean abort() {
		return touchSensorLeft.isPressed();
	}

	@Override
	protected void tearDown() {
		RobotDesign.differentialPilot.stop();
	}

}
