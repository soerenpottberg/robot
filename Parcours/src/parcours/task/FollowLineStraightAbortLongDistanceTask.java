package parcours.task;

import parcours.debug.DebugOutput;
import parcours.detector.LapsedTimeDetector;
import parcours.task.base.ControllerTask;
import parcours.task.state.LineSideState;
import parcours.utils.EWMA;
import parcours.utils.RobotDesign;
import lejos.nxt.LightSensor;
import lejos.nxt.NXTMotor;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;

public class FollowLineStraightAbortLongDistanceTask extends ControllerTask {
	
	private static final int END_OF_LINE_CHECK_ENABLE_INTERVAL_MS = 1000;
	private static final int DETECTION_COUNTER_THRESHOLD = 5;
	private static final int DISTANCE_DETECTION_THRESHOLD = 60;
	private static final long MS_COMPLETE_CYCLE_TIME = 12;
	private static final long MS_MEASURE_CYCLE_TIME  = 3;

	private static final int BASE_POWER = 30;

	private static final float Kp = 0.055f;
	private static final float Ki = 0.0055f;
	private static final float Kd = 0.0f;//90f;

	private LightSensor light;
	private NXTMotor motorA;
	private NXTMotor motorB;

	private EWMA lastErrors;
	private float errorDerived;
	private float errorIntegrated;
	private int lastPowerMotorA;
	private int lastPowerMotorB;
	private long nextCycleCompletion;
	private EWMA ewma;
	
	private DebugOutput out;
	
	
	private LapsedTimeDetector lapsedTimeDetector;
	private short detectionCounter = 0;
	private UltrasonicSensor distanceSensor;
	
	LineSideState state;
	
	
	private final float targetColor = RobotDesign.BLACK_RAW + 0.4f * (RobotDesign.SILVER_RAW - RobotDesign.BLACK_RAW);
	
	public FollowLineStraightAbortLongDistanceTask( LineSideState state ) {
		this.state = state;
	}
	
	@Override
	protected void init() {
		errorIntegrated = 1000;
		
		lastErrors = new EWMA(0.125f, 0f);
		ewma = new EWMA(0.125f, targetColor);
		if ( state.rightSideState ) {
			motorA = RobotDesign.unregulatedMotorRight;
			motorB = RobotDesign.unregulatedMotorLeft;
		} else {
			motorB = RobotDesign.unregulatedMotorRight;
			motorA = RobotDesign.unregulatedMotorLeft;
		}
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
		
		out = new DebugOutput();
		out.setDescription( 0, "cycle_t" );
		out.setDescription( 1, "deviat." );
		out.setDescription( 2, "compens" );
		out.setDescription( 4, "Kp * p" );
		out.setDescription( 5, "Ki * i" );
		out.setDescription( 7, "Kd * d" );
	}

	@Override
	protected void control() {
		nextCycleCompletion += MS_COMPLETE_CYCLE_TIME;
		
		final float lightValue = measureLight();
		final float error = calculateError(lightValue);
		integrateError(error);
		deriveError(error);
		
		final int compensation = pid(error);
		
		out.write(1, error);
		out.write(2, compensation);
		out.write(4, error * Kp);
		out.write(5, errorIntegrated * Ki);
		out.write(7, errorDerived * Kd);

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
		return (int) (Kp * error + Ki * errorIntegrated + Kd * errorDerived);
	}

	private float measureLight() {
		return ewma.addValue( light.getNormalizedLightValue() );
	}

	private float calculateError(float lightValue) {
		return lightValue - targetColor;
	}
	
	private void integrateError(float error) {
		errorIntegrated = 0.95f * errorIntegrated + error;
	}
	
	private void deriveError(float error) {
		final float last = lastErrors.getValue();
		errorDerived = error - last;
		lastErrors.addValue(error);
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