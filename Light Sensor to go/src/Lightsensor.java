import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;

public class Lightsensor {
	private static final int MIDDLE_LIGHT_VALUE = 45;
	private static final int BASE_SPEED = 500;
	private static final float Kp = 3;
	private static final float Ki = 2;
	private static final float Kd = 4f;
	private static final float DELTA_T = 0.5f;

	public static void main(String[] args) throws Exception {
		LightSensor light = new LightSensor(SensorPort.S1);
		Motor.A.forward();
		Motor.B.forward();
		Motor.A.setSpeed(BASE_SPEED);
		Motor.B.setSpeed(BASE_SPEED);
		float errorIntegrated = 0;
		float errorDerivated = 0;
		float lastError = 0;
		while (true) {
			int lightValue = light.getLightValue();
			int error = lightValue - MIDDLE_LIGHT_VALUE;
			errorIntegrated = 2f / 3f * errorIntegrated + DELTA_T * error;
			errorDerivated = (error - lastError) / DELTA_T;
			float compensation = error * Kp + errorIntegrated * Ki + errorDerivated * Kd;
			System.out.println(errorDerivated);
			setMotorPower(Motor.A, BASE_SPEED + compensation);
			setMotorPower(Motor.B, BASE_SPEED - compensation);
			lastError = error;
		}
	}

	private static void setMotorPower(NXTRegulatedMotor motor, float power) {
		float absPower = Math.abs(power);
		motor.setSpeed(absPower);
		if(power > 0) {
			motor.forward();
		} else {
			motor.backward();
		}
	}
}