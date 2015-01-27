import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;

public class Lightsensor {
	private static final float BREAK_COOEFICIENT = 0; //10f;
	private static final int MIDDLE_LIGHT_VALUE = 45;
	private static final int BASE_SPEED = 500;
	private static final float Kp = 4;
	private static final float Ki = 0; //2;
	private static final float Kd = 0f;
	private static final long DELTA_T_ms = 10; // in milliseconds
	private static final float DELTA_T = DELTA_T_ms / 1000f; // in seconds

	public static void main(String[] args) throws Exception {
		LightSensor light = new LightSensor(SensorPort.S1);
		Motor.A.forward();
		Motor.B.forward();
		Motor.A.setSpeed(BASE_SPEED);
		Motor.B.setSpeed(BASE_SPEED);
		float errorIntegrated = 0;
		float errorDerivated = 0;
		int lastError = 0;
		float oldPowerMotorA = 0;
		float oldPowerMotorB = 0;
		float maxBreak = 0;
		int i = 0;
		while (true) {
			if(i % 10 == 0) {
				System.out.println(maxBreak);
				maxBreak = 0;
			}
			int lightValue = light.getLightValue();
			int error = lightValue - MIDDLE_LIGHT_VALUE;
			if((error - lastError) > 1) {
				//System.out.println(error - lastError);
			}
			errorIntegrated = 2f / 3f * errorIntegrated + DELTA_T * error;
			errorDerivated = (error - lastError) / DELTA_T;
			float compensation = error * Kp + errorIntegrated * Ki + errorDerivated * Kd;
			float motorBreak = Math.abs(errorDerivated) * BREAK_COOEFICIENT;
			maxBreak = Math.max(maxBreak, motorBreak);
			float powerMotorA = BASE_SPEED - motorBreak + compensation;
			float powerMotorB = BASE_SPEED - motorBreak - compensation;
			setMotorPower(Motor.A, powerMotorA, oldPowerMotorA);
			setMotorPower(Motor.B, powerMotorB, oldPowerMotorB);
			lastError = error;
			oldPowerMotorA = powerMotorA;
			oldPowerMotorB = powerMotorB;
			i++;
			
			Thread.sleep(DELTA_T_ms);
		}
	}

	private static void setMotorPower(NXTRegulatedMotor motor, float power, float oldPower) {
		float absPower = Math.abs(power);
		motor.setSpeed(absPower);
		if(Math.signum(power) == Math.signum(oldPower)) {
			return;
		}
		if(power > 0) {
			motor.forward();
		} else {
			motor.backward();
		}
	}
}