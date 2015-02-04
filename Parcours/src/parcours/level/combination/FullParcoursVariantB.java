package parcours.level.combination;

import java.util.List;

import parcours.level.FinalLevel;
import parcours.level.Labyrinth;
import parcours.level.Obstacles;
import parcours.level.base.Level;
import parcours.level.base.LevelCombination;

public class FullParcoursVariantB extends LevelCombination {

	@Override
	public String getLabel() {
		return "Full Parcours B";
	}

	@Override
	public List<Level> createLevelList() {
		final LevelCombination qualification = new QualificationVariantB();
		final List<Level> levelList = qualification.createLevelList();
		levelList.add(new Labyrinth());
		levelList.add(new Obstacles());
		levelList.add(new FinalLevel());
		return levelList;
	}

}
