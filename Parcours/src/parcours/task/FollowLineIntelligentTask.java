package parcours.task;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.robotics.RegulatedMotor;
import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;

public class FollowLineIntelligentTask extends ControllerTask {

	private static final int DETECT_LIGHT_VALUE = 50;
	private static final int MIDDLE_LIGHT_VALUE = 35;
	private static final int NOT_LOST_LINE_VALUE = 45;
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

		System.out.println(lostLineCounter);
		if (lightValue <= NOT_LOST_LINE_VALUE) {
			lostLineCounter++;
		} else {
			lostLineCounter = 0;
		}
		if (lostLineCounter >= LOST_LINE_MAX) {
			Sound.beep();
			RobotDesign.differentialPilot.stop();
			RobotDesign.differentialPilot.travel(-5);
			lostLineCounter = 0;
			Motor.C.setSpeed(100);
			Motor.C.rotate(90, true);
			boolean foundLine = findLine();
			if(foundLine) {
				error = 0;
				errorIntegrated = 0;
				errorDerivated = 0;
				RobotDesign.differentialPilot.rotate(45);
			} else {
				lostLineCounter = - 3 * LOST_LINE_MAX;
			}
			Motor.C.setSpeed(900);
			Motor.C.rotate(-90);
			Motor.A.forward();
			Motor.B.forward();
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

	private boolean findLine() {
		boolean detectedLight = false;
		while (Motor.C.isMoving()) {
			int lightValue = measureLight();
			if(lightValue >= DETECT_LIGHT_VALUE) {
				detectedLight = true;
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
