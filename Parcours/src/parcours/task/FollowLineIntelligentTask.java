package parcours.task;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.robotics.RegulatedMotor;
import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;

public class FollowLineIntelligentTask extends ControllerTask {
	
	/*
	 * Black ~ 25;  Black < 30
	 * Gray  ~ 35
	 * White ~ 45
	 */

	private static final int MEASURE_SPEED = 25;
	private static final int FULL_SPEED = 900;
	private static final int MESSURE_ANGLE = 10;
	private static final int BACKWARD = -5;
	private static final int DETECT_LIGHT_VALUE = 40;
	private static final int MIDDLE_LIGHT_VALUE = 35; // TODO: try to increase
	private static final int NOT_LOST_LINE_VALUE = 45; // TODO: also reduce?
	private static final int LOST_LINE_MAX = 100;
	private static final int BASE_SPEED = 150;

	private static final int Kp = (int) (5 * 100);
	private static final int Ki = (int) (0.1 * 100);
	private static final int Kd = (int) (0 * 100);

	private TouchSensor touchSensorRight;
	private LightSensor light;
	private RegulatedMotor motorA;
	private RegulatedMotor motorB;

	private int errorIntegrated;
	private int errorDerivated;
	private int lastError;
	private int lastPowerMotorA;
	private int lastPowerMotorB;
	private int lostLineCounter = 0;

	@Override
	protected void init() {
		touchSensorRight = RobotDesign.touchSensorRight;
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

		//System.out.println(lostLineCounter);
		if (lightValue <= NOT_LOST_LINE_VALUE) {
			lostLineCounter++;
		} else {
			lostLineCounter = 0;
		}
		if (lostLineCounter >= LOST_LINE_MAX) {
			RobotDesign.differentialPilot.stop();
			RobotDesign.differentialPilot.travel(BACKWARD);
			RobotDesign.differentialPilot.rotate(MESSURE_ANGLE);
			lostLineCounter = 0;
			// fast and wait
			Motor.C.setSpeed(FULL_SPEED);
			Motor.C.rotate(90, false);
			// slow and non-blocking
			Motor.C.setSpeed(MEASURE_SPEED);
			Motor.C.rotate(-90, true);			
			int angle = findLine();
			boolean foundLine = (angle != -1);
			if(foundLine) {
				error = 0;
				errorIntegrated = 0;
				errorDerivated = 0;
				Sound.playTone(50 * angle, 200);
				RobotDesign.differentialPilot.rotate(angle);
			} else {
				// TODO: Maybe stronger negative integral
				RobotDesign.differentialPilot.rotate(-MESSURE_ANGLE);
				lostLineCounter = - 3 * LOST_LINE_MAX;
			}
			Motor.A.forward(); // TODO: set oldSpeed to zero instead
			Motor.B.forward(); // TODO: set oldSpeed to zero instead
		}

		int compensation = pid(error);

		int powerMotorA = BASE_SPEED + compensation;
		int powerMotorB = BASE_SPEED - compensation;
		RobotDesign.setMotorSpeed(motorA, powerMotorA, lastPowerMotorA);
		RobotDesign.setMotorSpeed(motorB, powerMotorB, lastPowerMotorB);

		lastError = error;
		lastPowerMotorA = powerMotorA;
		lastPowerMotorB = powerMotorB;

		// Motor.C.rotate(10);
		// Motor.C.rotate(-10);
	}

	private int findLine() {
		int detectedLight = -1;
		while (Motor.C.isMoving()) {
			int lightValue = measureLight();
			System.out.println(lightValue);
			if(lightValue >= DETECT_LIGHT_VALUE) {
				detectedLight = Motor.C.getTachoCount();
			}
		}
		return detectedLight;
	}

	private int pid(int error) {
		return (Kp * error + Ki * errorIntegrated + Kd * errorDerivated) / 100;
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
		return touchSensorRight.isPressed();
	}

	@Override
	protected void tearDown() {
		RobotDesign.differentialPilot.stop();
	}

}
