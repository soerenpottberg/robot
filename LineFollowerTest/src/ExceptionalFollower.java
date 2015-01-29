import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.util.Delay;


public class ExceptionalFollower {
	private static final long MS_COMPLETE_CYCLE_TIME = 50;
	private static final long MS_MEASURE_CYCLE_TIME  = 7;
	private static final long MS_REMAINING_TIME_INSUFICCIENT_WARNING_TIME = 15;
	
	
	private static final int PRECISION_FACTOR = 100;
	private static final int BASE_POWER = 30;

	// TODO: are those the optimum values?
	private static final double K_CRITICAL = 1.5;
	private static final double T_PERIOD = 0.05;
	private static final double DELTA_2 = MS_COMPLETE_CYCLE_TIME; // previously: 2.5 / 1000; // 2-3 ms

	private static final double Kp_CALC = 0.6 * K_CRITICAL;
	private static final double Ki_CALC = 2 * Kp_CALC * DELTA_2 / T_PERIOD;
	private static final double Kd_CALC = Kp_CALC * T_PERIOD / (8 * DELTA_2);

	private static final int Kp = (int) (Kp_CALC * PRECISION_FACTOR);
	private static final int Ki = (int) (Ki_CALC * PRECISION_FACTOR) + 2;
	private static final int Kd = (int) (Kd_CALC * PRECISION_FACTOR);
	
	// allows reducing the integral by an exp. value if necessary
	private static final float ALPHA_INTEGRAL = 0.0f;

	//static final float EWMA_ALPHA = 0.125f;
	
	private Sensor s_input;
	private NXTMotor MotorA;
	private NXTMotor MotorB;
	private int lastPowerMotorA = 0;
	private int lastPowerMotorB = 0;
	
	// TODO: parametrize this class with values for power, Kp, Ki, Kd, ...
	// TODO: add mechanism to leave the loop dynamically
	// (create abstract class as parameter that evaluates whether to leave the loop for good)
	public ExceptionalFollower(Sensor s_input) {
		this.s_input = s_input;
		this.MotorA = new NXTMotor(MotorPort.A);
		this.MotorB = new NXTMotor(MotorPort.B);
	}
	
	private void initMotors() {
		MotorA.setPower(BASE_POWER);
		MotorB.setPower(BASE_POWER);
		MotorA.forward();
		MotorB.forward();
	}

	public void follow() {
		initMotors();

		double nextCycleCompletion = System.currentTimeMillis();
		// TODO there must be an exit to the loop at some point!!!
		while (true) {
			nextCycleCompletion += MS_COMPLETE_CYCLE_TIME;
			// get at least 1 time new data from the sensor.
			float deviation = s_input.measure();
			
			// Warn if insufficient cycle time is detected.
			// This can be an early warning system for all kinds of problems.
			if ( nextCycleCompletion - System.currentTimeMillis()
				 < MS_REMAINING_TIME_INSUFICCIENT_WARNING_TIME ) {
				Sound.beep();
			}
			
			// regulate...
			int compensation = pid( deviation );
			output( compensation );
			calculateMotorSpeeds( compensation );
			
			// do as many measures as is possible before the current cycle ends
			int count = 0;
			while ( System.currentTimeMillis() + MS_MEASURE_CYCLE_TIME
					< nextCycleCompletion ) {
				++count;
				s_input.measure();
				// Wait for a given number of ms before continuing.
				Delay.msDelay( MS_MEASURE_CYCLE_TIME );
			}
			
			// System.out.println(count);
			
			// at this point, the most recent measures are weightened
			// with a factor of 1-(1-ewma_alpha)^cycles in the moving average.
			// This means one might have to adjust ewma_alpha is a way that
			// (1-ewma_alpha)^cycles < 0.2 for this to run smoothly.
			// use count to determine avg number of cycles.
		}
}
	
	private void calculateMotorSpeeds(int compensation) {
		int motorBreak = Math.min(BASE_POWER, Math.abs(compensation));
		int powerMotorA = BASE_POWER - motorBreak + compensation;
		int powerMotorB = BASE_POWER - motorBreak - compensation;
		// TODO: muss da nicht noch ein Vorfaktor rein (n*compensation)?
		
		setMotorPower(MotorA, powerMotorA, lastPowerMotorA);
		setMotorPower(MotorB, powerMotorB, lastPowerMotorB);
		lastPowerMotorA = powerMotorA;
		lastPowerMotorB = powerMotorB;
	}

static int counter = 1;
static int val_max = 0;
static void output(int value) {
	counter %= 5;
	if ( counter == 0 ) {
		System.out.println(val_max);
		val_max = -10000;
	}
	val_max = Math.max(value, val_max);
	++counter;
}

static int  x2 = 0;
static long t2 = 0;
static int  x3 = 0;
static long t3 = 0;


public int pid(int sensor_error) { 
	final int  x1 = sensor_error;
	final long t1 = System.currentTimeMillis();
	
	int delta_t = (int)(t1 - t2);
	final int integral100 = integrate(x1, x2, delta_t) +
			  ( ( sensor_error > 0 ) ? 2 * x1 : 0 );
	final int derivate100 = derive(t1, t3, x1, x2, x3);
	
	// remember last two measures
	x3 = x2;
	x2 = x1;
	t3 = t2;
	t2 = t1;
	
	return ( sensor_error * PRECISION_FACTOR * Kp +
			 integral100  * Ki +
			 derivate100  * Kd 
			 ) / ( PRECISION_FACTOR * PRECISION_FACTOR );
}

/**
 * Calculates the derivate for a parabol of three equally spaced points (x1;t1), (x2;t2); (x3;t3).
 * (x1;t1) is the first point in time and (x3;t3) the last/current one. (e.g. t3 > t1)
 * Returns 100 times the derivate at the point (x3;t3).
 * t is supposed to be given in ms
 */
public int derive( long t1, long t3, int x1, int x2, int x3 ) {
	final int delta_t = (int)( (t3 - t1) / 2 );
	final int x1_x2 = x1 - x2;
	final int x2_x3 = x2 - x3;
	final int x3_x1 = x3 - x1;
	final int x1_sqr = x1 * x1;
	final int x2_sqr = x2 * x2;
	final int x3_sqr = x3 * x3;
	
	final int MULTIPLICATOR = 100;
	
	int divisor = x1_x2 * x2_x3 * x3_x1;
	//divisor = ( divisor == 0 ) ? 1 : divisor;
	
	//ax^2 + bx + c
	int a = MULTIPLICATOR * ( 2 * x2 - (x1 + x3) ) * delta_t;
	int b = MULTIPLICATOR * ( x1_sqr - 2 * x2_sqr + x3_sqr ) * delta_t;
	
	if ( divisor == 0 ) {
		a *= 5;
		b *= 10;
	} else {
		a /= divisor;
		a /= divisor;
	}
	
	return a * x3 + b;
}

static int integral100 = 0;
/**
 * Integrates the x (sensor error) measures over time.
 * @param x1 : the current measure
 * @param x2 : the last measure
 * @param delta_t : the time between the last two measures
 * @return 100 times the current integration value.
 */
private int integrate(int x1, int x2, int delta_t) {
	return (int)( ( 1f - ALPHA_INTEGRAL ) * integral100 ) + 
           (int)( (x1 + x2) * PRECISION_FACTOR / 2 * delta_t );
}

private static void setMotorPower(NXTMotor motor, int power, int oldPower) {
	final int absPower = Math.abs(power);
	motor.setPower( absPower );
	
	float power_signum = Math.signum(power);
	if ( power_signum == Math.signum(oldPower)) {
		return;
	}
	
	if ( power_signum == -1.0f) {
		motor.backward();
	} else {
		motor.forward();
	}
}

}