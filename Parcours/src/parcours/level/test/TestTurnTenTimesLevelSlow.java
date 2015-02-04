package parcours.level.test;

import parcours.level.base.SingleTaskLevel;
import parcours.task.TurnTenTimesTask;
import parcours.utils.RobotDesign;

public class TestTurnTenTimesLevelSlow extends SingleTaskLevel {
	
	public TestTurnTenTimesLevelSlow() {
		super(new TurnTenTimesTask( (int)( 0.75f * RobotDesign.differentialPilot.getMaxRotateSpeed() ),
				                    (int)( 0.25f * RobotDesign.differentialPilot.getMaxRotateSpeed() ) ) );
	}

	@Override
	public String getLabel() {
		return "Travel";
	}

}
