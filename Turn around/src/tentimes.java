import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class tentimes{
	public static void main(String[] args) throws Exception {
		DifferentialPilot pilot = new DifferentialPilot(8.16, 15, Motor.B,
				Motor.A);
		pilot.rotate(5400);
		while(true){}
	   }
	}