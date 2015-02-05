package parcours.level.combination;

import java.util.ArrayList;
import java.util.List;

import parcours.level.BridgeSpeed;
import parcours.level.Elevator;
import parcours.level.FinalLevel;
import parcours.level.FollowLineRaceVariant;
import parcours.level.Labyrinth;
import parcours.level.SeeSaw;
import parcours.level.SuspensionBridge;
import parcours.level.Swamp;
import parcours.level.StartLevel;
import parcours.level.TurnTable;
import parcours.level.base.Level;
import parcours.level.base.LevelCombination;

public class FullParcours extends LevelCombination {

	@Override
	public String getLabel() {
		return "Parcours / Race";
	}

	@Override
	public List<Level> createLevelList() {
		final List<Level> levelList = new ArrayList<Level>();
		levelList.add(new StartLevel());
		levelList.add(new FollowLineRaceVariant());
		levelList.add(new BridgeSpeed());
		levelList.add(new Elevator());
		levelList.add(new Labyrinth());
		levelList.add(new Swamp());
		levelList.add(new SuspensionBridge());
		levelList.add(new SeeSaw());
		levelList.add(new TurnTable());
		levelList.add(new FinalLevel());
		return levelList;
	}

}
