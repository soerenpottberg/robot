package parcours.task;

import lejos.nxt.Motor;
import parcours.task.base.Task;
import parcours.utils.RobotDesign;

public class ResetTask implements Task {

	@Override
	public void run() {
		RobotDesign.differentialPilot.stop();
		Motor.A.setAcceleration(6000);
		Motor.A.setSpeed(0);
		Motor.B.setAcceleration(6000);
		Motor.B.setSpeed(0);
		RobotDesign.differentialPilot.stop();
		
		/*RobotDesign.differentialPilot.setTravelSpeed(600);
		RobotDesign.differentialPilot.setAcceleration(3 * 600);
		RobotDesign.differentialPilot.setRotateSpeed(180);*/
	}

}
