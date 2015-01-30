import sensor.evaluation.LightSensorEvaluation;
import sensor.evaluation.SensorEvaluation;
import lejos.nxt.SensorPort;


public class ExceptionalMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SensorEvaluation s = new LightSensorEvaluation(SensorPort.S1);
		ExceptionalFollower f = new ExceptionalFollower(s, new LoopCondition[0]);
		
		
		f.follow();
	}

}
