package parcours.task.labyrinth.config;

public class MainLabyrinthConfiguration extends LabyrinthConfiguration {

	@Override
	public int getMaximalDistance() {
		return 30;
	}

	@Override
	public int getBaseTravelSpeed() {
		return 25;
	}

	@Override
	public int getFarDistance() {
		return 60;
	}
	
}
