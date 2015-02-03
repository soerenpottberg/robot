import lejos.nxt.*;

public class MotorRotate {
	public static void main(String[] args) throws Exception {
		Motor.C.setSpeed(900);
		while (true) {
			Motor.C.rotate(10);
			Motor.C.rotate(-10);
		}
	}

}