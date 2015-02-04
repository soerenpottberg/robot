package parcours.level.combination;

import java.util.ArrayList;
import java.util.List;

import parcours.level.base.Level;
import parcours.level.base.LevelCombination;
import parcours.level.test.DebugMeasureLevel;
import parcours.level.test.TestElevatorBluetooth;
import parcours.level.test.TestGateBluetooth;
import parcours.level.test.TestRightTurnLevel;
import parcours.level.test.TestTravelLevel;
import parcours.level.test.TestTurnTableBluetooth;

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
		levelList.add(new TestTurnTableBluetooth());
		levelList.add(new TestGateBluetooth());
		levelList.add(new TestElevatorBluetooth());
		return levelList;
	}
	
	@Override
	public boolean isTestLevelCombination() {
		return true;
	}

}
