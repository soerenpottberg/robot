import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.TouchSensor;


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
		TouchSensor t1 = new TouchSensor(SensorPort.S2);
		TouchSensor t2 = new TouchSensor(SensorPort.S4);
		

		out.setDescription( 0, "cycl_ms" );
		out.setDescription( 1, "light" );
		out.setDescription( 2, "distanc" );
		out.setDescription( 3, "tacho A" );
		out.setDescription( 4, "tacho B" );
		out.setDescription( 5, "tacho C" );
		out.setDescription( 6, "touch 1" );
		out.setDescription( 7, "touch 2" );
		
		long tNow = System.currentTimeMillis();
		long tCycle = 0;
		while ( true ) {
			out.write(0, (float) tCycle);
			out.write(1, (float) s.getLightValue());
			out.write(2, (float) u.getDistance());
			out.write(3, (float) MotorA.getTachoCount());
			out.write(4, (float) MotorB.getTachoCount());
			out.write(5, (float) MotorC.getTachoCount());
			out.write(6, (float) (t1.isPressed() ? 1 : 0));
			out.write(7, (float) (t2.isPressed() ? 1 : 0));
			
			
			long tmp = System.currentTimeMillis();
			tCycle = tmp - tNow;
			tNow = tmp;
		}

	}

}
