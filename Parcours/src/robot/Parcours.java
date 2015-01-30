package robot;

import parcours.level.Bridge;
import parcours.level.Elevator;
import parcours.level.FollowLine;
import parcours.level.Level;
import parcours.level.StartLevel;


public class Parcours {
	
	private static int LEVEL_COUNT = 4;
	private static Level[] levels = new Level[LEVEL_COUNT];

	static {
		int i = 0;
		levels[i++] = new StartLevel();
		levels[i++] = new FollowLine();
		levels[i++] = new Bridge();
		levels[i++] = new Elevator();
	}
	
	public static void main(String[] args) {
		new Parcours();
	}
	
	public Parcours() {		
		LevelMenu menu = new LevelMenu(levels);
		while(true) {
			Level selectedLevel = menu.selectLevel();
			if(selectedLevel == null) {
				break;
			}
			selectedLevel.run();
		}
	}

}
