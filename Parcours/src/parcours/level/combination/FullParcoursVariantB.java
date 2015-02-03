package parcours.level.combination;

import parcours.level.FinalLevel;
import parcours.level.Labyrinth;
import parcours.level.Obstacles;
import parcours.level.base.Level;
import parcours.level.base.LevelCombination;

public class FullParcoursVariantB extends LevelCombination {
	
	private static final int QUALIFICATION_LEVEL_COUNT = QualificationVariantB.LEVELS.length;
	private static final int LEVEL_COUNT = QUALIFICATION_LEVEL_COUNT + 3;
	private static final Level[] LEVELS = new Level[LEVEL_COUNT];
    
	static {
		int i = 0;
		for (; i < QUALIFICATION_LEVEL_COUNT; i++) {
			LEVELS[i] = QualificationVariantB.LEVELS[i];
		}
		LEVELS[i++] = new Labyrinth();
		LEVELS[i++] = new Obstacles();
		LEVELS[i++] = new FinalLevel();
	}

	@Override
	public String getLabel() {
		return "Full Parcours B";
	}

	@Override
	public Level[] getLevels() {
		return LEVELS;
	}

}
