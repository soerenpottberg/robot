import lejos.nxt.*;

;

public class distance {
	public static void main(String[] args) throws Exception {
		UltrasonicSensor ultrasonic = new UltrasonicSensor(SensorPort.S2);
		while (true) {
			int b;
			b = ultrasonic.getDistance();
			System.out.println(b);
		}

	}

}