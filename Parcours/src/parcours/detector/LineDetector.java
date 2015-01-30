package parcours.detector;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LineDetector {
	
	private static final int WHITE_LINE_THRESHOLD = 50;

	private LightSensor light;

	public LineDetector() {
		light = new LightSensor(SensorPort.S1);
	}

	public boolean hasDetected() {
		return light.getLightValue() >= WHITE_LINE_THRESHOLD;
	}

}
