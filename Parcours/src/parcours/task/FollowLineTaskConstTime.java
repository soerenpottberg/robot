package parcours.task;

import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;

public class FollowLineTaskConstTime extends Task {

	private static final int MIDDLE_LIGHT_VALUE = 40;
	private static final int BASE_POWER = 30;

	private static final double K_CRITICAL = 3;
	private static final double T_PERIOD = 0.05;
	private static final double DELTA_2 = 2.5 / 1000; // 2-3 ms

	private static final double Kp_CALC = 0.45 * K_CRITICAL; // 0.6
	private static final double Ki_CALC = 1.2 * Kp_CALC * DELTA_2 / T_PERIOD; // 2
	private static final double Kd_CALC = Kp_CALC * T_PERIOD / (8 * DELTA_2); // 1

	private static final int Kp = (int) ((0 * Kp_CALC + 2.5) * 100); // 1.0
	private static final int Ki = (int) ((0 * Ki_CALC + 0.05) * 100) + 2; // 0.25
	private static final int Kd = (int) (0 * Kd_CALC * 100);

	private LightSensor light;
	private NXTMotor MotorA;
	private NXTMotor MotorB;

	private int errorIntegrated;
	private int errorDerivated;
	private int lastError;
	private int lastPowerMotorA;
	private int lastPowerMotorB;
	@SuppressWarnings("unused")
	private long lastTime;

	@Override
	protected void init() {
		MotorA = new NXTMotor(MotorPort.A);
		MotorB = new NXTMotor(MotorPort.B);
		light = new LightSensor(SensorPort.S1);
		MotorA.setPower(BASE_POWER);
		MotorB.setPower(BASE_POWER);
		MotorA.forward();
		MotorB.forward();
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

		int powerMotorA = BASE_POWER - compensation;
		int powerMotorB = BASE_POWER + compensation;
		setMotorPower(MotorA, powerMotorA, lastPowerMotorA);
		setMotorPower(MotorB, powerMotorB, lastPowerMotorB);
		
		long time = System.currentTimeMillis();
		// System.out.println(time - lastTime);
		
		lastTime = time;
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
		// TODO Detect line end
		// TODO Use ultrasonic or touch sensors
		return false;
	}

	@Override
	protected void tearDown() {
		// TODO Auto-generated method stub
		
	}

}