package robot;

import parcours.level.Bridge;
import parcours.level.Elevator;
import parcours.level.FollowLine;
import parcours.level.Level;
import parcours.level.StartLevel;


public class Parcours {
	
	enum UsedLevels {
		START_LEVEL(new StartLevel()),
		FOLLOW_LINE(new FollowLine()),
		BRIDGE(new Bridge()),
		ELEVATOR(new Elevator());
		
		private final Level level;

		private UsedLevels(Level level) {
			this.level = level;
		}

		public static Level[] getLevels() {
			Level[] levels = new Level[values().length];
			for (int i = 0; i < values().length; i++) {
				levels[i] = values()[i].level;
			}
			return levels;
		}
	}
	
	public static void main(String[] args) {
		new Parcours();
	}
	
	public Parcours() {		
		Level[] levels = UsedLevels.getLevels();
		
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
