import lejos.nxt.*;

public class Ultrasonic {
	public static void main(String[] args) throws Exception{
		UltrasonicSensor ultrasonic = new UltrasonicSensor(SensorPort.S2);
		while (true) {
			int b;
			b = ultrasonic.getDistance();
			System.out.println(b);
			if (b<12){
				Motor.B.forward();
				Motor.A.stop();
			}
			else if(b>=12&&b<=15) {
				Motor.B.forward();
				Motor.A.forward();
			}
			else{
				Motor.B.stop();
				Motor.A.forward();
			}
		}

	}

}