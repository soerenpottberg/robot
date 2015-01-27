import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Motor;

public class Lightsensor {
	private static final int MIDDLE_LIGHT_VALUE = 45;
	private static final int BASE_SPEED = 300;
	private static final float Kp = 5;
	private static final float Ki = 2;
	private static final float DELTA_T = 0.5f;

	public static void main(String[] args) throws Exception {
		LightSensor light = new LightSensor(SensorPort.S1);
		Motor.A.forward();
		Motor.B.forward();
		Motor.A.setSpeed(BASE_SPEED);
		Motor.B.setSpeed(BASE_SPEED);
		float errorIntegrated = 0;
		while (true) {
			int lightValue = light.getLightValue();
			int error = lightValue - MIDDLE_LIGHT_VALUE;
			errorIntegrated = 2f / 3f * errorIntegrated + DELTA_T * error;
			float compensation = error * Kp + errorIntegrated * Ki;
			System.out.println(errorIntegrated);
			Motor.A.setSpeed(BASE_SPEED + compensation);
			Motor.B.setSpeed(BASE_SPEED - compensation);
		}
	}
}