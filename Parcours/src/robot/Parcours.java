package robot;

import parcours.level.*;

public class Parcours {

	private static int LEVEL_COUNT = 5;
	private static Level[] levels = new Level[LEVEL_COUNT];

	static {
		int i = 0;
		levels[i++] = new StartLevel();
		levels[i++] = new FollowLine();
		levels[i++] = new Bridge();
		levels[i++] = new Elevator();
		levels[i++] = new DebugMeasure();
	}

	public static void main(String[] args) {
		new Parcours();
	}

	public Parcours() {
		LevelMenu menu = new LevelMenu(levels);
		while (true) {
			Level selectedLevel = menu.selectLevel();
			if (selectedLevel == null) {
				break;
			}
			selectedLevel.run();
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
