package parcours.task;

import parcours.detector.LineDetector;
import parcours.task.base.ControllerTask;
import parcours.task.boss.BossContext;
import parcours.task.labyrinth.LabyrinthContext;
import parcours.utils.EWMA;
import parcours.utils.RobotDesign;
import lejos.nxt.LightSensor;
import lejos.nxt.NXTMotor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class BossTask extends ControllerTask {
	
	private DifferentialPilot pilot;
	//private boolean isAboarted = false;

	private TouchSensor touchL;
	private TouchSensor touchR;

	private BossContext context;


	@Override
	protected void init() {
		touchR = RobotDesign.touchSensorRight;
		touchL = RobotDesign.touchSensorLeft;
		pilot = RobotDesign.differentialPilot;
		
		context = new BossContext(pilot);
		pilot.setTravelSpeed(60);
		//pilot.setRotateSpeed( 500 );
		//pilot.setAcceleration( 300 ); // default 210
		pilot.forward();
		
	}

	@Override
	protected void control() {

		boolean leftIsPressed = touchL.isPressed();
		boolean rightIsPressed = touchR.isPressed();

		if (leftIsPressed || rightIsPressed) {
			context.handleButtonPressed();
		} else {
			context.handleNoButtonIsPressed();
		}

	}

	@Override
	protected boolean abort() {
		return false;
	}

	@Override
	protected void tearDown() {
	}

}
