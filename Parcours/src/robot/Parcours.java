package robot;

import parcours.level.base.Level;
import parcours.level.base.LevelCombination;
import parcours.level.combination.FullParcoursVariantA;
import parcours.level.combination.FullParcoursVariantB;
import parcours.level.combination.QualificationVariantA;
import parcours.level.combination.QualificationVariantB;
import parcours.level.combination.TestLevelCombination;
import parcours.menu.LevelMenu;
import parcours.menu.MainMenu;

public class Parcours {

	private static final int COMBINATION_COUNT = 5;
	private static final LevelCombination[] ITEMS = new LevelCombination[COMBINATION_COUNT];
	private MainMenu mainMenu;

	static {
		int i = 0;
		ITEMS[i++] = new QualificationVariantA();
		ITEMS[i++] = new QualificationVariantB();
		ITEMS[i++] = new FullParcoursVariantA();
		ITEMS[i++] = new FullParcoursVariantB();
		ITEMS[i++] = new TestLevelCombination();
	}

	public static void main(String[] args) {
		new Parcours();
	}

	public Parcours() {
		mainMenu = new MainMenu(ITEMS);
		while (true) {
			LevelCombination selectedLevelCombination = mainMenu
					.selectLevelCombination();
			if (selectedLevelCombination == null) {
				break;
			}
			Level[] levels = selectedLevelCombination.getLevels();
			LevelMenu menu = new LevelMenu(levels);
			while (true) {
				Level selectedLevel = menu.selectLevel();
				if (selectedLevel == null) {
					break;
				}
				selectedLevel.run();
				if(selectedLevelCombination.isTestLevelCombination()) {
					continue; // Return to menu instead of running next level
				}
				while (true) {
					Level nextLevel = menu.getNextLevel();
					if (nextLevel == null) {
						break;
					}
					nextLevel.run();
				}
			}
		}
	}

}
