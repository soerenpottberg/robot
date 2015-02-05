package parcours.menu;

import parcours.level.base.Level;
import parcours.level.base.LevelCombination;
import lejos.nxt.LCD;
import lejos.util.TextMenu;

public class LevelMenu extends TextMenu {

	private Level[] levels;
	private int selectedLevelIndex = -1;

	public LevelMenu(LevelCombination levelCombination) {
		super(getItems(levelCombination));
		setTitle(levelCombination.getLabel());
		this.levels = levelCombination.getLevels();
	}

	private static String[] getItems(LevelCombination levelCombination) {
		Level[] levels = levelCombination.getLevels();
		String[] items = new String[levels.length];
		for (int i = 0; i < levels.length; i++) {
			Level level = levels[i];
			items[i] = level.getLabel();
		}
		return items;
	}

	public Level selectLevel() {
		LCD.clear();
		selectedLevelIndex = select();
		LCD.clear();
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
