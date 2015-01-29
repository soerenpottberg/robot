package robot;

import parcours.level.Level;
import lejos.util.TextMenu;

public class LevelMenu extends TextMenu {

	private Level[] levels;

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
		int selectedLevelIndex = select();
		if(selectedLevelIndex < 0) {
			return null;
		}
		return levels[selectedLevelIndex];
	}

}
