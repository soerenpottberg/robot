package parcours.task.labyrinth.config;

public class StartLabyrinthConfiguration extends LabyrinthConfiguration {
	
	@Override
	public int getBaseTravelSpeed() {
		return 40;
	}
	
	// Wall distances
	@Override
	public int getMaximalDistance() {
		return 40;
	}

	@Override
	public int getFarDistance() {	
		return 80;  // 60
	}

	@Override
	public int getMinimalDistance() {
		return 20;
	}

	@Override
	public int getNoWallDistance() {
		return 50;
	}
	
	@Override
	public int getCloseDistance() {
		// TODO Auto-generated method stub
		return 15;
	}
	
	// travelling Distances
	@Override
	public int getCuttingEdge() {
		return 20;
	}

	@Override
	public int getAdjustmentDistance() {
		return 30;
	}

	@Override
	public int getBackwardRightButton() {
		// TODO Auto-generated method stub
		return -10;
	}

	@Override
	public int getBackwardBothButton() {
		// TODO Auto-generated method stub
		return -15;
	}
	
	@Override
	public int getGoingRightAngle() {
		return 50; // Just changed for race
	}
	
	
}
