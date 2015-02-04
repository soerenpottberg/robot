package parcours.utils;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

public class RobotDesign {
	// After changing the design, these values need to be obtained from DebugMeasure!
	public static final int BLACK  = 26;
	public static final int SILVER = 49;
	
	public static final int BLACK_RAW = 260;
	public static final int SILVER_RAW = 480;
	
	public static final int BRIDGE_RAW = 360;
	public static final int EMPTY_SPACE_RAW = 260;
	
	public static final double wheelDiameter = 8.16;  // Diameter in mm printed on wheels
	public static final double trackWidth    = 13.72;
	
	public static final TouchSensor       touchSensorRight   = new TouchSensor(SensorPort.S4);
	public static final TouchSensor       touchSensorLeft    = new TouchSensor(SensorPort.S2);
	public static final LightSensor       lightSensor        = new LightSensor(SensorPort.S1);
	public static final UltrasonicSensor  distanceSensor     = new UltrasonicSensor(SensorPort.S3);
	
	public static final NXTMotor          unregulatedMotorRight  = new NXTMotor(MotorPort.A);
	public static final NXTMotor          unregulatedMotorLeft   = new NXTMotor(MotorPort.B);
	public static final NXTMotor          unregulatedMotorSensor = new NXTMotor(MotorPort.C);
	
	public static final DifferentialPilot differentialPilot      = new DifferentialPilot(wheelDiameter, trackWidth, Motor.B, Motor.A);
	public static final RegulatedMotor REGULATED_MOTOR_RIGHT = Motor.A;
	public static final RegulatedMotor REGULATED_MOTOR_LEFT = Motor.B;

	public static void setMotorPower(NXTMotor motor, int power, int oldPower) {
		final int absPower = Math.abs( power );
		motor.setPower( absPower );
		
		final float power_signum = Math.signum( power );
		if ( power_signum != Math.signum( oldPower ) ) {
			if ( power_signum < -0.9f) {
				motor.backward();
			} else {
				motor.forward();
			}
		}
	}

	public static void setMotorSpeed(RegulatedMotor motor, int speed,
			int oldSpeed) {
		final int absPower = Math.abs(speed);
		motor.setSpeed(absPower);

		final float signum = Math.signum(speed);
		if (signum != Math.signum(oldSpeed)) {
			if (signum < -0.9f) {
				motor.backward();
			} else {
				motor.forward();
			}
		}
	}
}
