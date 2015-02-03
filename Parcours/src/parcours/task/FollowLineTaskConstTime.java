package parcours.task;

import lejos.nxt.NXTMotor;
import lejos.nxt.Sound;
import lejos.util.Delay;
import sensor.evaluation.LightSensorEvaluation;
import sensor.evaluation.SensorEvaluation;
import utils.RobotDesign;
import debug.DebugOutput;

public class FollowLineTaskConstTime extends ControllerTask {
	private static final int WEIGHTEN_NONE   = 0;
	private static final int WEIGHTEN_LINEAR = 1;
	private static final int WEIGHTEN_SQR    = 2;
	private static final int WEIGHTEN_EXP    = 3;
	
	private static final int WEIGHTENING_SETTING = 0;
	
	// allows reducing the integral by an exp. value if necessary
	private static final float ALPHA_INTEGRAL = 0.02f;
	private static final float INTEGRAL_100_PERCENT = 0.7f;
	
	private static final long MS_COMPLETE_CYCLE_TIME = 12;
	private static final long MS_MEASURE_CYCLE_TIME  = 3;
	private static final long MS_REMAINING_TIME_INSUFICCIENT_WARNING_TIME = 7;
	
	private static final int BASE_POWER = 25;
	
	private static final float DEVIATION_FROM_GRAY_TARGET = 0.2f;

	private static final int K_FACTOR = 100;
	private static final float Kp = 4.0f / K_FACTOR;
	private static final float Ki =  0.0f / K_FACTOR; // 28
	private static final float Kd =  1.00f; // 1800
	
	private SensorEvaluation lightSensor;
	private NXTMotor motorA;
	private NXTMotor motorB;
	private int lastPowerMotorA = 0;
	private int lastPowerMotorB = 0;
	
	private long nextCycleCompletion;
	
	// (y2;t2) represents the previous measure and point in time
	private float y2 = 0.0f;
	private long  t2 = 0;
	
	// (y1;t1) represents the first measure and point in time (t1 < t2 < t3)
	@SuppressWarnings("unused")
	private float y1 = 0.0f;
	@SuppressWarnings("unused")
	private long  t1 = 0;
	
	// for debugging purposes only:
	private DebugOutput out;

	@Override
	protected void init() {
		final float gray   = (RobotDesign.BLACK + RobotDesign.SILVER) / 2;
		final float target = gray - DEVIATION_FROM_GRAY_TARGET * (RobotDesign.SILVER - RobotDesign.BLACK);
		lightSensor = new LightSensorEvaluation(0.15f, target, 8 );
		motorA = RobotDesign.unregulatedMotorRight;
		motorB = RobotDesign.unregulatedMotorLeft;
		motorA.setPower(BASE_POWER);
		motorB.setPower(BASE_POWER);
		motorA.forward();
		motorB.forward();
		
		nextCycleCompletion = System.currentTimeMillis();
		
		out = new DebugOutput();
		out.setDescription( 0, "cycle_t" );
		out.setDescription( 1, "deviat." );
		out.setDescription( 2, "compens" );
		out.setDescription( 3, "MNumber" );

		out.setDescription( 4, "Kp * p" );
		out.setDescription( 5, "Ki * i" );
		out.setDescription( 6, "I Adj." );
		out.setDescription( 7, "Kd * d" );
	}

	@Override
	protected void control() {
		final long tCycleStart = System.currentTimeMillis();
		
		nextCycleCompletion += MS_COMPLETE_CYCLE_TIME;
		// get at least 1 time new data from the sensor.
		final float deviation = lightSensor.measureError();
		
		// Warn if insufficient cycle time is detected.
		// This can be an early warning system for all kinds of problems.
		if ( nextCycleCompletion - tCycleStart
			 < MS_REMAINING_TIME_INSUFICCIENT_WARNING_TIME ) {
			Sound.beep();
			
			// beeping is synchronous, so without this a beep would cause an 
			// other one and so on...
			nextCycleCompletion = System.currentTimeMillis() +
					              MS_COMPLETE_CYCLE_TIME;
		}
		
		// regulate...
		float compensation = pid( deviation );
		calculateMotorSpeeds( compensation );
		
		out.write(1, deviation);
		out.write(2, compensation);
		out.write(0, (int)(System.currentTimeMillis() - tCycleStart));
		
		// do as many measures as is possible before the current cycle ends
		int count = 1; // 1 measure has already been performed at the beginning of the loop...
		while ( System.currentTimeMillis() + MS_MEASURE_CYCLE_TIME
				< nextCycleCompletion ) {
			++count;
			lightSensor.measureError();
			// Wait for a given number of ms before continuing.
			Delay.msDelay( MS_MEASURE_CYCLE_TIME );
		}
		
		out.write(3, count);
		
		// at this point, the most recent measures are weightened
		// with a factor of 1-(1-ewma_alpha)^cycles in the moving average.
		// This means one might have to adjust ewma_alpha is a way that
		// (1-ewma_alpha)^cycles < 0.2 for this to run smoothly.
		// use count to determine avg number of cycles.
	}

	@Override
	protected boolean abort() {
		// TODO Detect line end
		// TODO Use ultrasonic or touch sensors
		return false;
	}

	@Override
	protected void tearDown() {
	}
	
	private float lastIntegral = 0;
	private   int intCount = 0;

	/**
	 * PID regulates the loop :)
	 * @param deviationFromTarget
	 * @return
	 */
	private float pid(float deviationFromTarget) {
		// y3 represents the current measure, which means the last point in time
		final float y3 = deviationFromTarget;
		final long  t3 = System.currentTimeMillis();
		
		// accumulate integral over the last two points
		int last_delta_t = (int)(t3 - t2);
		integral = integrate(y3, y2, last_delta_t) ;
		
		// Make sure to reduce the integral fast enough if it is falling for 2 periods in a row.
		final float tmp = Math.abs( integral );
		if ( tmp < lastIntegral ) {
			++intCount;
		} else {
			intCount = 0;
		}
		
		if (intCount > 1 ) {
			integral /= 2;
		}
			
		lastIntegral = tmp;
		
		float weightedIntegral;
		
		switch (WEIGHTENING_SETTING) {
		case WEIGHTEN_NONE:
			weightedIntegral = integral * Ki;
			break;
		case WEIGHTEN_SQR:
			weightedIntegral = weightenIntegralSqr(integral, Ki);
			break;
		case WEIGHTEN_LINEAR:
			weightedIntegral = weightenIntegralLinear(integral, Ki);
			break;
		case WEIGHTEN_EXP:
			weightedIntegral = weightenIntegralExp(integral, Ki);
			break;
		default:
			weightedIntegral = 0;
		}
		
		// calculate the derivate over the last 3 points
		//final float derivate = derive2pt(y3, y2, last_delta_t);
		//final float derivate = derive3pt(t1, t2, t3, y1, y2, y3);
		final float derivate = derivate(y3,t3);
		
		// remember last two measures
		y1 = y2;
		y2 = y3;
		t1 = t2;
		t2 = t3;

		out.write(4, deviationFromTarget * Kp);
		out.write(5, integral * Ki);
		out.write(6, weightedIntegral);
		out.write(7, derivate * Kd);
		
		// TODO: use algorithm from NXTRegulatedMotor l 715
		// TODO: try out improved derivate.
		// TODO: add integral only if above a certain threshold.
		return deviationFromTarget * Kp + derivate * Kd + weightedIntegral;
	}
	
	private float integral = 0.0f;
	/**
	 * Integrates the x (sensor error) measures over time.
	 * @param y3 : the current measure
	 * @param y2 : the previous measure
	 * @param delta_t : the time between the last two measures
	 * @return The current integration value.
	 */
	private float integrate(float y3, float y2, int delta_t) {
		return ( 1.0f - ALPHA_INTEGRAL ) * integral + y3 * delta_t;
	}
	
	private float weightenIntegralSqr(float integral, float Ki) {
		final float intTmp = integral * Ki;
		return intTmp * ( intTmp / INTEGRAL_100_PERCENT ) * ( intTmp / INTEGRAL_100_PERCENT );
	}
	
	private float weightenIntegralLinear(float integral, float Ki) {
		final float intTmp = integral * Ki;
		return intTmp * ( Math.abs( intTmp ) / INTEGRAL_100_PERCENT );
	}
	
	private float weightenIntegralExp(float integral, float Ki) {
		//-x / (e^2)^x
		final double intTmp = integral * Ki;
		final double e2 = Math.E * Math.E;
		return (float)( intTmp * ( 1 - ( Math.abs(intTmp) / Math.pow( e2, Math.abs( intTmp ) ) ) ) );
	}
	
	private float derivate(float y3, long t3) {
		return derive2pt(y3, y2, (int)(t3-t2));
		/*lightSensor.get3Pt();
		return derive3pt(lightSensor.pt3Time[0], lightSensor.pt3Time[1], lightSensor.pt3Time[2],
				         lightSensor.pt3Val[0],  lightSensor.pt3Val[1],  lightSensor.pt3Val[2] );*/
	}

	/**
	 * Calculates the derivate over 2 points.
	 * @param y3 : the most recent measure
	 * @param y2 : the previous measure
	 * @param delta_t : the time between those two measures
	 * @return The slope of a line between the two given points of data.
	 */
	protected float derive2pt(float y3, float y2, int delta_t) {
		return ( y3 - y2 ) / delta_t;
	}
	
	/**
	 * Calculates the derivate for a parabol threw three points (t1;y1), (t2;y2); (t3;y3).
	 * (t1;y1) is the first point in time and (t3;y3) the last (current) one. (e.g. t1 < t3)
	 * Returns the derivate at the point (t3;y3).
	 */
	protected float derive3pt(long t1, long t2, long t3, float y1, float y2, float y3) {
		final float y2_y1 = y2 - y1;
		final float t2_t1 = t2 - t1;
		final float t2sqr = t2 * t2;
		final float t1sqr = t1 * t1;
		final float t1_t3 = t1 - t3;
		

		final float a = (y2_y1 * (t1_t3) + (y3 - y1) * t2_t1) /
			((t1_t3) * (t2sqr - t1sqr) + t2_t1 * (t3 * t3 - t1sqr));

		final float b = (y2_y1 - a * (t2sqr - t1sqr)) / t2_t1;
		
		return 2 * a * t3 + b;
	}
	
	private void calculateMotorSpeeds(float compensation) {
		int powerMotorA = (int) (BASE_POWER * (1 - compensation));
		int powerMotorB = (int) (BASE_POWER * (1 + compensation));
		
		RobotDesign.setMotorPower(motorA, powerMotorA, lastPowerMotorA);
		RobotDesign.setMotorPower(motorB, powerMotorB, lastPowerMotorB);
		lastPowerMotorA = powerMotorA;
		lastPowerMotorB = powerMotorB;
	}
}
