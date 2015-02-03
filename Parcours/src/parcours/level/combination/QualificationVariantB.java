package parcours.level.combination;

import parcours.level.Bridge;
import parcours.level.Elevator;
import parcours.level.FollowLineVariantB;
import parcours.level.StartLevel;
import parcours.level.base.Level;
import parcours.level.base.LevelCombination;

public class QualificationVariantB extends LevelCombination {
	
	private static final int LEVEL_COUNT = 4;
	protected static final Level[] LEVELS = new Level[LEVEL_COUNT];
    
	static {
		int i = 0;
		LEVELS[i++] = new StartLevel();
		LEVELS[i++] = new FollowLineVariantB();
		LEVELS[i++] = new Bridge();
		LEVELS[i++] = new Elevator();
	}

	@Override
	public String getLabel() {
		return "Qualification B";
	}

	@Override
	public Level[] getLevels() {
		return LEVELS;
	}

}
