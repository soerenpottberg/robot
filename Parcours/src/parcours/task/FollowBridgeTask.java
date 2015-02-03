package parcours.task;

import utils.RobotDesign;
import lejos.nxt.LightSensor;
import lejos.nxt.NXTMotor;

public class FollowBridgeTask extends Task {

	private static final int MIDDLE_LIGHT_VALUE = 30; // Bridge = 36 ; Side = 21
	private static final int BASE_POWER = 50;

	private static final int Kp = (int) (2 * 100);
	private static final int Ki = (int) (0.1 * 100);
	private static final int Kd = (int) (0 * 100);

	private LightSensor light;
	private NXTMotor motorA;
	private NXTMotor motorB;

	private int errorIntegrated;
	private int errorDerivated;
	private int lastError;
	private int lastPowerMotorA;
	private int lastPowerMotorB;

	@Override
	protected void init() {
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
	}

	@Override
	protected void control() {
		int lightValue = measureLight();
		
		int error = calculateError(lightValue);
		integrateError(error);
		deriveError(error);
		
		int compensation = pid(error);

		int powerMotorA = BASE_POWER + compensation;
		int powerMotorB = BASE_POWER - compensation;
		setMotorPower(motorA, powerMotorA, lastPowerMotorA);
		setMotorPower(motorB, powerMotorB, lastPowerMotorB);
		
		lastError = error;
		lastPowerMotorA = powerMotorA;
		lastPowerMotorB = powerMotorB;
	}

	private int pid(int error) {
		return (Kp * error  + Ki * errorIntegrated + Kd * errorDerivated) / 100;
	}

	private int measureLight() {
		return light.getLightValue();
	}

	private int calculateError(int lightValue) {
		return lightValue - MIDDLE_LIGHT_VALUE;
	}

	private void deriveError(int error) {
		errorDerivated = (error - lastError);
	}

	private void integrateError(int error) {
		errorIntegrated = (int) (2f / 3f * errorIntegrated) + error;
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
		return false;
	}

	@Override
	protected void tearDown() {
	}

}