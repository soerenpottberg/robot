package parcours.task;

import debug.DebugOutput;
import lejos.nxt.LightSensor;
import lejos.nxt.NXTMotor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import utils.RobotDesign;

public class DebugMeasureTask extends Task {
	NXTMotor motorR;
	NXTMotor motorL;
	NXTMotor motorS;
		
	DebugOutput out;
	UltrasonicSensor u;
	LightSensor s;
	TouchSensor touchR;
	TouchSensor touchL;
	
	long tNow;
	long tCycle;

	@Override
	protected void init() {
		motorR = RobotDesign.unregulatedMotorRight;
		motorL = RobotDesign.unregulatedMotorLeft;
		motorS = RobotDesign.unregulatedMotorSensor;
		
		motorR.flt();
		motorL.flt();
		
		out    = new DebugOutput();
		u      = RobotDesign.distanceSensor;
		s      = RobotDesign.lightSensor;
		touchL = RobotDesign.touchSensorLeft;
		touchR = RobotDesign.touchSensorRight;
		

		out.setDescription( 0, "cycl_ms" );
		out.setDescription( 1, "light" );
		out.setDescription( 2, "distanc" );
		out.setDescription( 3, "tacho R" );
		out.setDescription( 4, "tacho L" );
		out.setDescription( 5, "tacho S" );
		out.setDescription( 6, "touch R" );
		out.setDescription( 7, "touch L" );
		
		tNow = System.currentTimeMillis();
		tCycle = 0;
	}

	@Override
	protected void control() {
		out.write(0, (int) tCycle);
		out.write(1, s.getLightValue());
		out.write(2, u.getDistance());
		out.write(3, motorR.getTachoCount());
		out.write(4, motorL.getTachoCount());
		out.write(5, motorS.getTachoCount());
		out.write(6, touchR.isPressed() ? 1 : 0);
		out.write(7, touchL.isPressed() ? 1 : 0);


		long tmp = System.currentTimeMillis();
		tCycle = tmp - tNow;
		tNow = tmp;
	}

	@Override
	protected boolean abort() {
		return false;
	}

	@Override
	protected void tearDown() {
	}

}
