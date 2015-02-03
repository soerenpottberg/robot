package parcours.level.base;

public abstract class LevelCombination {

	public abstract String getLabel();

	public abstract Level[] getLevels();
	
	public boolean isTestLevelCombination() {
		return false;
	}

}
