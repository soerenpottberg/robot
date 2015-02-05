package parcours.level.combination;

import java.util.ArrayList;
import java.util.List;

import parcours.level.base.Level;
import parcours.level.base.LevelCombination;
import parcours.level.test.DebugMeasureLevel;
import parcours.level.test.TestGoIntoElevator;
import parcours.level.test.TestRightTurnLevel;
import parcours.level.test.TestTravelLevel;
import parcours.level.test.TestTurnTenTimesLevelFast;
import parcours.level.test.TestTurnTenTimesLevelSlow;

public class TestLevelCombination extends LevelCombination {
	
	@Override
	public String getLabel() {
		return "Testing";
	}

	@Override
	public List<Level> createLevelList() {
		final ArrayList<Level> levelList = new ArrayList<Level>();
		levelList.add(new DebugMeasureLevel());
		levelList.add(new TestTravelLevel());
		levelList.add(new TestRightTurnLevel());
		levelList.add(new TestGoIntoElevator());
		levelList.add(new TestTurnTenTimesLevelSlow());
		levelList.add(new TestTurnTenTimesLevelFast());
		return levelList;
	}
	
	@Override
	public boolean isTestLevelCombination() {
		return true;
	}

}
