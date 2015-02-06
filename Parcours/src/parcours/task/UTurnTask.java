package parcours.task;

import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import parcours.task.base.ControllerTask;
import parcours.utils.RobotDesign;

public class UTurnTask extends ControllerTask {

	protected DifferentialPilot pilot;

	@Override
	protected void init() {

		pilot = RobotDesign.differentialPilot;
		pilot.stop();
		
		System.out.println("U-Turn");
		System.out.println(90);
		pilot.setTravelSpeed(90);
		pilot.rotate(180);
		Delay.msDelay(1000);
		
		System.out.println(0.8 * pilot.getMaxRotateSpeed());
		pilot.setTravelSpeed(0.8 * pilot.getMaxRotateSpeed());
		pilot.rotate(180);
		Delay.msDelay(1000);
		
		System.out.println("Right-Angle");
		System.out.println(90);
		pilot.setTravelSpeed(90);
		pilot.rotate(90);
		Delay.msDelay(1000);
		
		System.out.println(90);
		pilot.setTravelSpeed(90);
		pilot.rotate(90);
		Delay.msDelay(1000);
		
		System.out.println(0.8 * pilot.getMaxRotateSpeed());
		pilot.setTravelSpeed(0.8 * pilot.getMaxRotateSpeed());
		pilot.rotate(90);
		Delay.msDelay(1000);
		
		System.out.println(0.8 * pilot.getMaxRotateSpeed());
		pilot.setTravelSpeed(0.8 * pilot.getMaxRotateSpeed());
		pilot.rotate(90);
		Delay.msDelay(1000);
	}

	@Override
	protected void control() {
	}

	@Override
	protected boolean abort() {
		return true;
	}

	@Override
	protected void tearDown() {
	}

}
