import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Motor;

public class Lightsensor {
	private static final int MIDDLE_LIGHT_VALUE = 45;
	private static final int BASE_SPEED = 300;
	private static final float Kp = 5;

	public static void main(String[] args) throws Exception {
		LightSensor light = new LightSensor(SensorPort.S1);
		Motor.A.forward();
		Motor.B.forward();
		Motor.A.setSpeed(BASE_SPEED);
		Motor.B.setSpeed(BASE_SPEED);
		while (true) {
			int lightValue = light.getLightValue();
			int error = lightValue - MIDDLE_LIGHT_VALUE;
			float compensation = error * Kp;
			System.out.println(compensation);
			Motor.A.setSpeed(BASE_SPEED + compensation);
			Motor.B.setSpeed(BASE_SPEED - compensation);
		}
	}
}