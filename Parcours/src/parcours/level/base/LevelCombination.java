package parcours.level.base;

import java.util.List;

public abstract class LevelCombination {

	public abstract String getLabel();

	public final Level[] getLevels() {
		final List<Level> levelList = createLevelList();
		if(levelList == null) {
			return null;
		}
		Level[] levels = new Level[levelList.size()];
		return levelList.toArray(levels);
	}

	public abstract List<Level> createLevelList();
	
	public boolean isTestLevelCombination() {
		return false;
	}

}
