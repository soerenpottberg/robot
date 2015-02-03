package parcours.task.labyrinth.config;

public class MainLabyrinthConfiguration extends LabyrinthConfiguration {


	@Override
	public int getBaseTravelSpeed() {
		return 25;
	}
	
	// Wall Distances
	@Override
	public int getCloseDistance() {
		// TODO Auto-generated method stub
		return 10;
	}
	
	@Override
	public int getMaximalDistance() {
		return 20;
	}
	
	@Override
	public int getMinimalDistance() {
		return 15;
	}

	@Override
	public int getNoWallDistance() {
		return 30;
	}
	
	@Override
	public int getFarDistance() {
		return 40;
	}
	

		//Travelling distances
	@Override
	public int getCuttingEdge() {
		return 17;
	}

	@Override
	public int getAdjustmentDistance() {
		return 20;
	}

	@Override
	public int getBackwardRightButton() {
		// TODO Auto-generated method stub
		return -7;
	}

	@Override
	public int getBackwardBothButton() {
		// TODO Auto-generated method stub
		return -10;
	}


	
}
