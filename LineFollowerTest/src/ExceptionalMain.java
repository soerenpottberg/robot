import lejos.nxt.SensorPort;


public class ExceptionalMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sensor s = new LSensor(SensorPort.S1);
		ExceptionalFollower f = new ExceptionalFollower(s, new LoopCondition[0]);
		f.follow();
	}

}
