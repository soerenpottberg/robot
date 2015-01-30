import lejos.nxt.*;
import lejos.robotics.Touch;
import lejos.robotics.navigation.DifferentialPilot;

public class gogogo {
	public static void main(String[] args) throws Exception {
		DifferentialPilot pilot = new DifferentialPilot(8.16, 13, Motor.B,
				Motor.A);

		TouchSensor touchl = new TouchSensor(SensorPort.S2);
		TouchSensor touchr = new TouchSensor(SensorPort.S4);
		UltrasonicSensor Ultra = new UltrasonicSensor(SensorPort.S3);

		while (true) {
			pilot.setTravelSpeed(20);
			pilot.setRotateSpeed(60);
			//pilot.setAcceleration(2);
			pilot.forward();
			int a = Ultra.getDistance();

		
			if (touchl.isPressed() && a < 25){
				pilot.travel(-25);
				//pilot.travelArc(8, 5);
				pilot.rotate(90);
			}
			else if (touchl.isPressed() && a >= 25) {
				pilot.travel(-10);
				//pilot.travelArc(-8, 5);
				pilot.rotate(-15);
			}
			else if (touchr.isPressed() && (touchl.isPressed() == false) ) {
				//pilot.travel(-5);
				//pilot.travelArc(8, 5);
				//Sound.beep();
				pilot.travel(-10);
				pilot.rotate(15);
			}

			else if (a<15){
				pilot.rotate(20);
				pilot.travel(20);
				pilot.rotate(-20);
			}
			else if ( a > 15 && a < 20) {
				pilot.rotate(10);
				pilot.travel(20);
				pilot.rotate(-10);
			}

			else if (a > 30 && a < 50) {
				pilot.rotate(-10);
				pilot.travel(20);
				pilot.rotate(10);
				
			} else if (a > 65) {
				Sound.beep();
				pilot.travel(15);
				pilot.rotate(-90);
				pilot.travel(25);
			}
		}

	}
}
