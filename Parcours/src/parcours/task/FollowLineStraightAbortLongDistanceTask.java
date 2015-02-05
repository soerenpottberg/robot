package parcours.task;

import parcours.detector.LapsedTimeDetector;
import parcours.task.base.ControllerTask;
import parcours.utils.EWMA;
import parcours.utils.RobotDesign;
import lejos.nxt.LightSensor;
import lejos.nxt.NXTMotor;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;

public class FollowLineStraightTask extends ControllerTask {
	
	private static final int END_OF_LINE_CHECK_ENABLE_INTERVAL_MS = 1000;
	private static final int DETECTION_COUNTER_THRESHOLD = 4;
	private static final int DISTANCE_DETECTION_THRESHOLD = 50;
	private static final long MS_COMPLETE_CYCLE_TIME = 12;
	private static final long MS_MEASURE_CYCLE_TIME  = 3;

	private static final int BASE_POWER = 50;

	private static final float Kp = 0.10f;
	private static final float Ki = 0.015f;

	private LightSensor light;
	private NXTMotor motorA;
	private NXTMotor motorB;

	private float errorIntegrated;
	private int lastPowerMotorA;
	private int lastPowerMotorB;
	private long nextCycleCompletion;
	private EWMA ewma;
	
	
	private LapsedTimeDetector lapsedTimeDetector;
	private short detectionCounter = 0;
	private UltrasonicSensor distanceSensor;
	
	
	private final float targetColor = RobotDesign.BLACK_RAW +
			0.9f * ((RobotDesign.SILVER_RAW - RobotDesign.BLACK_RAW) / 2);
	
	@Override
	protected void init() {
		ewma = new EWMA(0.125f, targetColor);
		motorA = RobotDesign.unregulatedMotorRight;
		motorB = RobotDesign.unregulatedMotorLeft;
		light = RobotDesign.lightSensor;
		motorA.setPower(BASE_POWER);
		motorB.setPower(BASE_POWER);
		motorA.forward();
		motorB.forward();
		lastPowerMotorA = 0;
		lastPowerMotorB = 0;
		
		nextCycleCompletion = System.currentTimeMillis();
		
		lapsedTimeDetector = new LapsedTimeDetector( END_OF_LINE_CHECK_ENABLE_INTERVAL_MS );
		lapsedTimeDetector.arm();
		
		distanceSensor = RobotDesign.distanceSensor;
	}

	@Override
	protected void control() {
		nextCycleCompletion += MS_COMPLETE_CYCLE_TIME;
		
		final float lightValue = measureLight();
		final float error = calculateError(lightValue);
		integrateError(error);
		
		final int compensation = pid(error);

		final int powerMotorA = BASE_POWER - compensation;
		final int powerMotorB = BASE_POWER + compensation;
		RobotDesign.setMotorPower(motorA, powerMotorA, lastPowerMotorA);
		RobotDesign.setMotorPower(motorB, powerMotorB, lastPowerMotorB);
		
		while ( System.currentTimeMillis() + MS_MEASURE_CYCLE_TIME < nextCycleCompletion ) {
			measureLight();
			// Wait for a given number of ms before continuing.
			Delay.msDelay( MS_MEASURE_CYCLE_TIME );
		}
		
		lastPowerMotorA = powerMotorA;
		lastPowerMotorB = powerMotorB;
	}

	private int pid(float error) {
		return (int) (Kp * error + Ki * errorIntegrated);
	}

	private float measureLight() {
		return ewma.addValue( light.getNormalizedLightValue() );
	}

	private float calculateError(float lightValue) {
		return lightValue - targetColor;
	}
	
	private void integrateError(float error) {
		errorIntegrated = (2f / 3f * errorIntegrated) + error;
		if (error > 0) {
			errorIntegrated += error;
		}
	}

	@Override
	protected boolean abort() {
		return lapsedTimeDetector.hasDetected() && checkDistanceDetection();
	}
	
	private boolean checkDistanceDetection() {
		if( distanceSensor.getDistance() > DISTANCE_DETECTION_THRESHOLD ) {
			++detectionCounter;
		} else {
			detectionCounter = 0;
		}
		
		return detectionCounter > DETECTION_COUNTER_THRESHOLD;
	}

	@Override
	protected void tearDown() {
		RobotDesign.differentialPilot.stop();
	}
}