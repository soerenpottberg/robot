import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class tentimes{
	public static void main(String[] args) throws Exception {
		DifferentialPilot pilot = new DifferentialPilot(8.16, 13.75, Motor.B,
				Motor.A);
		pilot.rotate(3600);
		while(true){}
	   }
	}