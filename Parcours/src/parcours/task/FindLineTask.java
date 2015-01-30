package parcours.task;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class FindLineTask extends Task {

	private static final int WHITE_LINE_THRESHOLD = 50;
	private UltrasonicSensor ultra;
	private DifferentialPilot pilot;
	private LightSensor light;
	private boolean isAboarted = false;

	@Override
	protected void init() {
		ultra = new UltrasonicSensor(SensorPort.S3);
		pilot = new DifferentialPilot(8.16, 13, Motor.B, Motor.A);
		light = new LightSensor(SensorPort.S1);
		pilot.setTravelSpeed(20);
		pilot.forward();
	}

	@Override
	protected void control() {
		// TODO check for collision		
	}

	@Override
	protected boolean abort() {
		return isAboarted || light.getLightValue() >= WHITE_LINE_THRESHOLD;
	}

	@Override
	protected void tearDown() {
		pilot.stop();
		Motor.B.suspendRegulation();
		Motor.A.suspendRegulation();
	}

}
