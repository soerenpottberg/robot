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
			int error = 20-a;
			
			if (error > 5){
				pilot.travelArc(10, 10);
				pilot.forward();
			}
			else if (error < -5){
				pilot.travelArc(-10, 10);
				pilot.travelArc(10, 10);
				pilot.forward();
			}
			
			/*int errordistance = 20-a;
			if (errordistance > 10) {
				pilot.travelArc(-5, 5);
				pilot.travelArc(5,5);
				pilot.forward();
			}
			else if (errordistance < -10) {
				pilot.steer(10);
			} 
			
			//int error = 2 * errordistance;
			
			/*else if (Math.abs(errordistance)<5){
				pilot.forward();
				//pilot.arcForward(Math.signum(errordistance)*15+ 2*error);
			}
			else {
				pilot.steer(-errordistance);
				//pilot.arcForward(Math.signum(errordistance)*15+error);
			}
			// int b = (a + 5) % 255;
			/*Motor.A.forward();
			Motor.B.forward();
			int errordistance = 20 - a;
			if (errordistance > 10) {
				errordistance = 10;
			}
			if (errordistance < -10) {
				errordistance = -10;
			}
			int error = 2 * errordistance;

			Motor.A.setSpeed(Startspeed + error);
			Motor.B.setSpeed(Startspeed - error);
			System.out.println(error);

			
			 * int oldb=35; if (b < 20) { Motor.A.setSpeed(Startspeed + error);
			 * Motor.B.setSpeed(Startspeed - error); } else if (b >= 20 && b <=
			 * 25) { Motor.A.setSpeed(Startspeed); Motor.B.setSpeed(Startspeed);
			 * } else if (b>25&& b<=35){ Motor.A.setSpeed(Startspeed - error);
			 * Motor.B.setSpeed(Startspeed + error); else{ if(Math.abs(b-old)<2)
			 * } }
			 */
		}
	}
}