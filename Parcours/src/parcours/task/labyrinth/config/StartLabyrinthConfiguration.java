package parcours.task.labyrinth.config;

public class StartLabyrinthConfiguration extends LabyrinthConfiguration {

	@Override
	public int getMaximalDistance() {
		return 40;
	}

	@Override
	public int getBaseTravelSpeed() {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	public int getFarDistance() {
		
		return 60;
	}

	@Override
	public int getMinimalDistance() {
		return 16;
	}

	@Override
	public int getCuttingEdge() {
		return 17;
	}

	@Override
	public int getNoWallDistance() {
		return 50;
	}

	
	
}
