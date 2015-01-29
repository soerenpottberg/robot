import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class Ultrasonic {
	public static void main(String[] args) throws Exception {
		UltrasonicSensor Ultra = new UltrasonicSensor(SensorPort.S3);
		DifferentialPilot pilot= new DifferentialPilot(8.16, 13, Motor.B,Motor.A);

		while (true) {
			int a = Ultra.getDistance();
			pilot.setTravelSpeed(20);
			pilot.forward();
			
			if (a<10) {
				pilot.rotate(10);
				pilot.travel(10);
				pilot.rotate(-10);
			}
			
			else if (a > 20 && a < 50){
				pilot.rotate(-10);
				pilot.travel(15);
				pilot.rotate(10);
			}
			else if(a > 55){
				pilot.travel(5);
				pilot.rotate(-90);
				pilot.travel(25);
			}
		}
	}
}