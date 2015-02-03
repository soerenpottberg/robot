package parcours.task.labyrinth.config;

public class MainLabyrinthConfiguration extends LabyrinthConfiguration {

	@Override
	public int getMaximalDistance() {
		return 25;
	}
	
	@Override
	public int getMinimalDistance() {
		return 16;
	}

	@Override
	public int getNoWallDistance() {
		return 30;
	}
	
	@Override
	public int getFarDistance() {
		return 40;
	}
	
	@Override
	public int getBaseTravelSpeed() {
		return 25;
	}

	@Override
	public int getCuttingEdge() {
		return 17;
	}

	
}
