package parcours.level.combination;

import parcours.level.base.Level;
import parcours.level.base.LevelCombination;
import parcours.level.test.DebugMeasureLevel;
import parcours.level.test.TestRightTurnLevel;
import parcours.level.test.TestTravelLevel;

public class Testing extends LevelCombination {
	
	private static final int LEVEL_COUNT = 3;
	private static final Level[] LEVELS = new Level[LEVEL_COUNT];
    
	static {
		int i = 0;
		LEVELS[i++] = new DebugMeasureLevel();
		LEVELS[i++] = new TestTravelLevel();
		LEVELS[i++] = new TestRightTurnLevel();
	}

	@Override
	public String getLabel() {
		return "Testing";
	}

	@Override
	public Level[] getLevels() {
		return LEVELS;
	}

}
