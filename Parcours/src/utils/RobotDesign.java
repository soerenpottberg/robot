package utils;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class RobotDesign {
	// After changing the design, these values need to be obtained from DebugMeasure!
	public static final int BLACK  = 26;
	public static final int SILVER = 49;
	
	public static final double wheelDiameter = 8.16; // OK: Diameter in mm printed on wheels
	public static final double trackWidth    = 13;   // TODO: find out value
	
	public static final TouchSensor       touchSensorRight   = new TouchSensor(SensorPort.S4);
	public static final TouchSensor       touchSensorLeft    = new TouchSensor(SensorPort.S2);
	public static final LightSensor       lightSensor        = new LightSensor(SensorPort.S1);
	public static final UltrasonicSensor  distanceSensor     = new UltrasonicSensor(SensorPort.S3);
	
	public static final NXTMotor          unregulatedMotorRight  = new NXTMotor(MotorPort.A);
	public static final NXTMotor          unregulatedMotorLeft   = new NXTMotor(MotorPort.B);
	public static final NXTMotor          unregulatedMotorSensor = new NXTMotor(MotorPort.C);
	
	public static final DifferentialPilot differentialPilot      = new DifferentialPilot(wheelDiameter, trackWidth, Motor.B, Motor.A);
}
