import lejos.nxt.SensorPort;


public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sensor s = new LSensor(SensorPort.S1);
		ExceptionalFollower f = new ExceptionalFollower(s);
		f.follow();
	}

}
