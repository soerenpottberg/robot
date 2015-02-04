package parcours.level.test;

import parcours.level.base.SingleTaskLevel;
import parcours.task.TurnTenTimesTask;
import parcours.utils.RobotDesign;

public class TestTurnTenTimesLevelFast extends SingleTaskLevel {
	
	public TestTurnTenTimesLevelFast() {
		super(new TurnTenTimesTask( (int)( 4 * RobotDesign.differentialPilot.getMaxRotateSpeed() ),
				                    (int)(     RobotDesign.differentialPilot.getMaxRotateSpeed() ) ) );
	}

	@Override
	public String getLabel() {
		return "10 Turns Fast";
	}

}
