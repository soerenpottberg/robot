package parcours.menu;

import lejos.nxt.LCD;
import lejos.util.TextMenu;
import parcours.level.base.LevelCombination;

public class MainMenu extends TextMenu {

	private int selectedLevelCombinationIndex = -1;
	private LevelCombination[] levelCombinations;

	public MainMenu(LevelCombination[] levelCombinations) {
		super(getItems(levelCombinations));
		this.levelCombinations = levelCombinations;
	}

	private static String[] getItems(LevelCombination[] levelCombinations) {
		String[] items = new String[levelCombinations.length];
		for (int i = 0; i < levelCombinations.length; i++) {
			LevelCombination levelCombination = levelCombinations[i];
			items[i] = levelCombination.getLabel();
		}
		return items;
	}

	public LevelCombination selectLevelCombination() {
		LCD.clear();
		selectedLevelCombinationIndex = select();
		LCD.clear();
		return getLevelCombination();
	}

	private LevelCombination getLevelCombination() {
		if(selectedLevelCombinationIndex < 0) {
			return null;
		}
		return levelCombinations[selectedLevelCombinationIndex];
	}

}

