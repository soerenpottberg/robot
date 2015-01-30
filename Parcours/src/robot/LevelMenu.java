package robot;

import parcours.level.Level;
import lejos.util.TextMenu;

public class LevelMenu extends TextMenu {

	private Level[] levels;
	private int selectedLevelIndex = -1;

	public LevelMenu(Level[] levels) {
		super(getItems(levels));
		this.levels = levels;
	}

	private static String[] getItems(Level[] levels) {
		String[] items = new String[levels.length];
		for (int i = 0; i < levels.length; i++) {
			Level level = levels[i];
			items[i] = level.getLabel();
		}
		return items;
	}

	public Level selectLevel() {
		selectedLevelIndex = select();
		return getLevel();
	}
	
	public Level getNextLevel() {
		if(selectedLevelIndex + 1 >= levels.length) {
			selectedLevelIndex = -1;
			return null;
		}
		selectedLevelIndex++;
		return getLevel();
	}

	private Level getLevel() {
		if(selectedLevelIndex < 0) {
			return null;
		}
		return levels[selectedLevelIndex];
	}

}
