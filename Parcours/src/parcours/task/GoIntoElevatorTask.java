package parcours.task;

import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import utils.RobotDesign;

public class GoIntoElevatorTask extends ControllerTask {
	protected DifferentialPilot pilot;
	protected TouchSensor touchSensorRight, touchSensorLeft;
	
	@Override
	protected void init() {
		touchSensorRight = RobotDesign.touchSensorRight;
		touchSensorLeft = RobotDesign.touchSensorLeft;
		pilot = RobotDesign.differentialPilot;
		pilot.setTravelSpeed(20);
		pilot.travel(-5);
		Motor.C.rotate(-90);			
		pilot.rotate(-30);
	}

	@Override
	protected void control() {
		pilot.setTravelSpeed(10);
		pilot.forward();
		if (touchSensorRight.isPressed()){
			pilot.rotate(15);
		}
		pilot.travel(5);
		if (touchSensorLeft.isPressed()){
			pilot.rotate(-10);
		}
	}

	@Override
	protected boolean abort() {
		// TODO Auto-generated method stub
		return (touchSensorRight.isPressed() && touchSensorLeft.isPressed());
	}

	@Override
	protected void tearDown() {
		// TODO Auto-generated method stub
		//pilot.travel(-3);
		pilot.stop();
	}

}
