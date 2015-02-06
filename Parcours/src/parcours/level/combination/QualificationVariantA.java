package parcours.level.combination;

import java.util.ArrayList;
import java.util.List;

import parcours.level.FollowLineVariantA;
import parcours.level.QualificationLabyrinth;
import parcours.level.base.Level;
import parcours.level.base.LevelCombination;

public class QualificationVariantA extends LevelCombination {

	@Override
	public String getLabel() {
		return "Qualification A";
	}

	@Override
	public List<Level> createLevelList() {
		final ArrayList<Level> levelList = new ArrayList<Level>();
		levelList.add(new FollowLineVariantA());
		levelList.add(new QualificationLabyrinth());
		return levelList;
	}

}
