import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;

public class Lightsensor {
	private static final int MIDDLE_LIGHT_VALUE = 45;
	private static final int BASE_POWER = 25;
	private static final int Kp = (int) (1.5 * 100); // 1.5 critical; 1.0
	private static final int Ki = (int) (0.0 * 100); // 0.25
	private static final int Kd = (int) (0.0 * 100);

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
			if (error > 25) {
				Sound.beep();
				// errorIntegrated += 70;
			}
			errorDerivated = (error - lastError);
			int compensation = (error * Kp + errorIntegrated * Ki + errorDerivated
					* Kd) / 100;
			int powerMotorA = BASE_POWER + compensation;
			int powerMotorB = BASE_POWER - compensation;
			setMotorPower(MotorA, powerMotorA, lastPowerMotorA);
			setMotorPower(MotorB, powerMotorB, lastPowerMotorB);
			long time = System.currentTimeMillis();
			System.out.println(lastTime - time);
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