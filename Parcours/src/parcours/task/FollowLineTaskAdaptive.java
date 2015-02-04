package parcours.task;

import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;
import lejos.nxt.LightSensor;
import lejos.nxt.NXTMotor;
import lejos.nxt.Sound;

public class FollowLineTaskAdaptive extends ControllerTask {

	private static final int FOUND_BLACK = -15;
	private static final int FIND_BLACK_COMPENSATION = 20;
	private static final int SWITCH_EDGE_ERROR = 30;
	private static final int MIDDLE_LIGHT_VALUE = 40;
	private static final int BASE_POWER = 40;

	private static final double K_CRITICAL = 3;
	private static final double T_PERIOD = 0.05;
	private static final double DELTA_2 = 2.5 / 1000; // 2-3 ms

	private static final double Kp_CALC = 0.45 * K_CRITICAL; // 0.6
	private static final double Ki_CALC = 1.2 * Kp_CALC * DELTA_2 / T_PERIOD; // 2
	private static final double Kd_CALC = Kp_CALC * T_PERIOD / (8 * DELTA_2); // 1

	private static final int Kp = (int) ((0 * Kp_CALC + 0.5) * 100); // 1.0
	private static final int Ki = (int) ((0 * Ki_CALC + 0.2) * 100) + 2; // 0.25
	private static final int Kd = (int) (0 * Kd_CALC * 100);

	private LightSensor light;
	private NXTMotor motorA;
	private NXTMotor motorB;

	private int errorIntegrated;
	private int errorDerivated;
	private int lastError;
	private int lastPowerMotorA;
	private int lastPowerMotorB;
	@SuppressWarnings("unused")
	private long lastTime;
	private boolean isInverted = false;
	private boolean findBlack = false;

	@Override
	protected void init() {
		motorA = RobotDesign.unregulatedMotorRight;
		motorB = RobotDesign.unregulatedMotorLeft;
		light  = RobotDesign.lightSensor;
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
	}

	@Override
	protected void control() {
		int lightValue = measureLight();

		int error = calculateError(lightValue);

		integrateError(error);
		deriveError(error);

		int compensation = pid(error);

		if (!findBlack && errorIntegrated >= SWITCH_EDGE_ERROR) {
			Sound.beep();
			isInverted = !isInverted;
			findBlack = true;
		}

		if (findBlack) {
			if (error <= FOUND_BLACK) {
				findBlack = false;
				Sound.playTone(200, 200);
			} else {
				compensation = FIND_BLACK_COMPENSATION;
			}
		}

		int powerMotorA;
		int powerMotorB;

		if (!findBlack) {
			if (!isInverted) {
				powerMotorA = BASE_POWER - compensation;
				powerMotorB = BASE_POWER + compensation;
			} else {
				powerMotorA = BASE_POWER + compensation;
				powerMotorB = BASE_POWER - compensation;
			}
		} else {
			if (!isInverted) {
				powerMotorA = -compensation;
				powerMotorB = +compensation;
			} else {
				powerMotorA = +compensation;
				powerMotorB = -compensation;
			}
		}
		RobotDesign.setMotorPower(motorA, powerMotorA, lastPowerMotorA);
		RobotDesign.setMotorPower(motorB, powerMotorB, lastPowerMotorB);

		long time = System.currentTimeMillis();
		// System.out.println(time - lastTime);

		lastTime = time;
		lastError = error;
		lastPowerMotorA = powerMotorA;
		lastPowerMotorB = powerMotorB;
	}

	private int pid(int error) {
		return (Kp * error + Ki * errorIntegrated + Kd * errorDerivated) / 100;
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

	@Override
	protected boolean abort() {
		// TODO Detect line end
		// TODO Use ultrasonic or touch sensors
		return false;
	}

	@Override
	protected void tearDown() {
		// TODO Auto-generated method stub

	}

}
