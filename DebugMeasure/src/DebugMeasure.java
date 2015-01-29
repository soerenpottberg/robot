import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;


public class DebugMeasure {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		NXTMotor MotorA = new NXTMotor(MotorPort.A);
		NXTMotor MotorB = new NXTMotor(MotorPort.B);
		NXTMotor MotorC = new NXTMotor(MotorPort.C);
		
		MotorA.flt();
		MotorB.flt();
		
		DebugOutput out = new DebugOutput();
		UltrasonicSensor u = new UltrasonicSensor(SensorPort.S3);
		LightSensor s = new LightSensor(SensorPort.S1);

		out.setDescription( 0, "cycl_ms" );
		out.setDescription( 1, "light" );
		out.setDescription( 2, "range" );
		out.setDescription( 3, "distanc" );
		out.setDescription( 4, "tacho A" );
		out.setDescription( 5, "tacho B" );
		out.setDescription( 6, "tacho C" );
		
		long tNow = System.currentTimeMillis();
		long tCycle = 0;
		while ( true ) {
			out.write(0, (float) tCycle);
			out.write(1, (float) s.getLightValue());
			out.write(2, (float) u.getRange());
			out.write(3, (float) u.getDistance());
			out.write(4, (float) MotorA.getTachoCount());
			out.write(5, (float) MotorB.getTachoCount());
			out.write(6, (float) MotorC.getTachoCount());
			
			long tmp = System.currentTimeMillis();
			tCycle = tmp - tNow;
			tNow = tmp;
		}

	}

}
