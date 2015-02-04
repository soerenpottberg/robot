package parcours.level.combination;

import java.util.ArrayList;
import java.util.List;

import parcours.level.BridgePower;
import parcours.level.BridgeSpeed;
import parcours.level.Elevator;
import parcours.level.FollowLineVariantB;
import parcours.level.StartLevel;
import parcours.level.base.Level;
import parcours.level.base.LevelCombination;

public class QualificationVariantB extends LevelCombination {

	@Override
	public String getLabel() {
		return "Qualification B";
	}

	@Override
	public List<Level> createLevelList() {
		final ArrayList<Level> levelList = new ArrayList<Level>();
		levelList.add(new StartLevel());
		levelList.add(new FollowLineVariantB());
		levelList.add(new BridgeSpeed());
		levelList.add(new Elevator());
		return levelList;
	}

}
