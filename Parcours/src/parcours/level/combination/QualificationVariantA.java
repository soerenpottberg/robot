package parcours.level.combination;

import parcours.level.Bridge;
import parcours.level.Elevator;
import parcours.level.FollowLineVariantA;
import parcours.level.StartLevel;
import parcours.level.base.Level;
import parcours.level.base.LevelCombination;

public class QualificationVariantA extends LevelCombination {
	
	private static final int LEVEL_COUNT = 4;
	protected static final Level[] LEVELS = new Level[LEVEL_COUNT];
    
	static {
		int i = 0;
		LEVELS[i++] = new StartLevel();
		LEVELS[i++] = new FollowLineVariantA();
		LEVELS[i++] = new Bridge();
		LEVELS[i++] = new Elevator();
	}

	@Override
	public String getLabel() {
		return "Qualification A";
	}

	@Override
	public Level[] getLevels() {
		return LEVELS;
	}

}
