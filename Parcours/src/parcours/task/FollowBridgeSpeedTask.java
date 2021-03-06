package parcours.task;

import lejos.nxt.LightSensor;
import lejos.nxt.TouchSensor;
import lejos.robotics.RegulatedMotor;
import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;

public class FollowBridgeSpeedTask extends ControllerTask {

	private static final int MIDDLE_LIGHT_VALUE = 30; // Bridge = 36 ; Side = 21
	private static final int BASE_SPEED = 300;

	private static final int Kp = (int) (5 * 100);
	private static final int Ki = (int) (1 * 100);
	private static final int Kd = (int) (0 * 100);

	private TouchSensor touchSensorLeft;
	private LightSensor light;
	private RegulatedMotor motorA;
	private RegulatedMotor motorB;

	private int errorIntegrated;
	private int errorDerivated;
	private int lastError;
	private int lastPowerMotorA;
	private int lastPowerMotorB;

	@Override
	protected void init() {
		touchSensorLeft = RobotDesign.touchSensorLeft;
		motorA = RobotDesign.REGULATED_MOTOR_RIGHT;
		motorB = RobotDesign.REGULATED_MOTOR_LEFT;
		light = RobotDesign.lightSensor;
		motorA.setSpeed(BASE_SPEED);
		motorB.setSpeed(BASE_SPEED);
		motorA.forward();
		motorB.forward();
		errorIntegrated = 0;
		errorDerivated = 0;
		lastError = 0;
		lastPowerMotorA = 0;
		lastPowerMotorB = 0;
	}

	@Override
	protected void control() {
		int lightValue = measureLight();
		
		int error = calculateError(lightValue);
		integrateError(error);
		deriveError(error);
		
		int compensation = pid(error);

		int powerMotorA = BASE_SPEED + compensation;
		int powerMotorB = BASE_SPEED - compensation;
		RobotDesign.setMotorSpeed(motorA, powerMotorA, lastPowerMotorA);
		RobotDesign.setMotorSpeed(motorB, powerMotorB, lastPowerMotorB);
		
		lastError = error;
		lastPowerMotorA = powerMotorA;
		lastPowerMotorB = powerMotorB;
	}

	private int pid(int error) {
		return (Kp * error  + Ki * errorIntegrated + Kd * errorDerivated) / 100;
	}

	private int measureLight() {
		return light.getLightValue();
	}

	private int calculateError(int lightValue) {
		return lightValue - MIDDLE_LIGHT_VALUE;
	}

	private void deriveError(int error) {
		errorDerivated = (error - lastError);
	}

	private void integrateError(int error) {
		errorIntegrated = (int) (2f / 3f * errorIntegrated) + error;
	}

	@Override
	protected boolean abort() {
		return touchSensorLeft.isPressed();
	}

	@Override
	protected void tearDown() {
		RobotDesign.differentialPilot.stop();
	}

}
