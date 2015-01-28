import lejos.nxt.*;

public class MotorRotate {
	public static void main(String[] args) throws Exception {
		while (true) {
			Motor.C.rotate(90);
			Thread.sleep(1000);
			Motor.C.rotate(-90);
			Thread.sleep(1000);
		}
	}

}