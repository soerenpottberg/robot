import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class go {
	public static void main(String[] args) throws Exception {
		DifferentialPilot pilot= new DifferentialPilot(8.16, 13, Motor.B,Motor.A);

		TouchSensor touchl=new TouchSensor(SensorPort.S2);
		TouchSensor touchr= new TouchSensor(SensorPort.S4);
		while(true){
			pilot.setTravelSpeed(20);
			pilot.forward();
			if (touchr.isPressed()){
				pilot.travel(-5);
				pilot.travelArc(8,5);
			} 
			else if (touchl.isPressed()){
				pilot.travel(-5);
				pilot.travelArc(-8,5);
			} 
		}
		
	}
}