package parcours.task.labyrinth.config;

public class StartLabyrinthConfiguration extends LabyrinthConfiguration {

	@Override
	public int getMaximalDistance() {
		return 40;
	}

	@Override
	public int getBaseTravelSpeed() {
		return 40;
	}

	@Override
	public int getFarDistance() {
		
		return 60;
	}

	@Override
	public int getMinimalDistance() {
		return 20;
	}

	@Override
	public int getCuttingEdge() {
		return 20;
	}

	@Override
	public int getNoWallDistance() {
		return 50;
	}

	@Override
	public int getAdjustmentDistance() {
		return 30;
	}

	@Override
	public int getCloseDistance() {
		// TODO Auto-generated method stub
		return 15;
	}

	
	
}
