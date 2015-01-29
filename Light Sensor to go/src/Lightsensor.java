import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;

public class Lightsensor {
	private static final int MIDDLE_LIGHT_VALUE = 45;
	private static final int BASE_POWER = 25;

	private static final double K_CRITICAL = 1.5;
	private static final double T_PERIOD = 0.05;
	private static final double DELTA_2 = 2.5 / 1000; // 2-3 ms

	private static final double Kp_CALC = 0.6 * K_CRITICAL;
	private static final double Ki_CALC = 2 * Kp_CALC * DELTA_2 / T_PERIOD;
	private static final double Kd_CALC = Kp_CALC * T_PERIOD / (8 * DELTA_2);

	private static final int Kp = (int) (Kp_CALC * 100);
	private static final int Ki = (int) (Ki_CALC * 100) + 2;
	private static final int Kd = (int) (Kd_CALC * 100);

	public static void main(String[] args) throws Exception {
		NXTMotor MotorA = new NXTMotor(MotorPort.A);
		NXTMotor MotorB = new NXTMotor(MotorPort.B);
		LightSensor light = new LightSensor(SensorPort.S1);
		MotorA.setPower(BASE_POWER);
		MotorB.setPower(BASE_POWER);
		MotorA.forward();
		MotorB.forward();
		int errorIntegrated = 0;
		int errorDerivated = 0;
		int lastError = 0;
		int lastlastError = 0;
		int lastPowerMotorA = 0;
		int lastPowerMotorB = 0;
		long lastTime = System.currentTimeMillis();
		while (true) {
			int lightValue = light.getLightValue();
			int error = lightValue - MIDDLE_LIGHT_VALUE;
			errorIntegrated = (int) (2f / 3f * errorIntegrated) + error;
			System.out.println(errorIntegrated);
			if (error > 0) {
				//Sound.beep();
				errorIntegrated += error;
			}
			errorDerivated = (error - lastError);
			int compensation = (error * Kp + errorIntegrated * Ki + errorDerivated
					* Kd) / 100;
			int motorBreak = Math.min(BASE_POWER, Math.abs(compensation));
			int powerMotorA = BASE_POWER - motorBreak - compensation;
			int powerMotorB = BASE_POWER - motorBreak + compensation;
			setMotorPower(MotorA, powerMotorA, lastPowerMotorA);
			setMotorPower(MotorB, powerMotorB, lastPowerMotorB);
			long time = System.currentTimeMillis();
			//System.out.println(time - lastTime);
			lastTime = time;
			lastlastError = lastError;
			lastError = error;
			lastPowerMotorA = powerMotorA;
			lastPowerMotorB = powerMotorB;
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
}