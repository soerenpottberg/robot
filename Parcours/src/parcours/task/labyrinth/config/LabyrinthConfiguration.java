package parcours.task.labyrinth.config;

public abstract class LabyrinthConfiguration {
	
	/*
	 * Distances
	 */

	public int getCloseDistance() {
		return 15;//15
	}

	public int getMinimalDistance() {
		return 20;
	}
	
	public int getMaximalDistance() {
		return 40; //25
	}
	
	/**
	 * Determines whether there is a wall or not
	 * @return
	 */
	public int getFarDistance() {
		return 60;
	}
	
	/*
	 * Angles
	 */

	
	
	
	/*
	 * 90 Degree Turns
	 */
	

	/**
	 * Distance to travel before making a 90 degree right turn
	 * @return
	 */
	public int getCuttingEdge() {
		return 17;//20
	}

	/**
	 * Distance to travel after making a 90 degree right turn
	 * @return
	 */
	public int getAfterCuttingEdge() {
		return 30;
	}
	
	/*
	 * Slight Turns (go back -> slight turn)
	 */

	public int getNoWallDistance() {
		return 40;//25
	}

	public int getBackwardLeftButtonWall() {
		return -15;//-20
	}

	public int getBackwardLeftButton() {
		return -10;
	}

	public int getBackwardRightButton() {
		return -10;
	}

	public int getBackwardBothButton() {
		return -15;//-25
	}
	
	public int getGoingLeftAngle() {
		return -15;
	}

	public int getGoingRightAngle() {
		return 30;//20
	}
	
	/*
	 * Adjusting Distances (left turn -> straight -> right turn)
	 */

	public int getAdjustmentDistance() {
		return 20;
	}
	
	public int getSmallInreaseDistanceAngle() {
		return 10;
	}
	
	public int getLargeInreaseDistanceAngle() {
		return 20;
	}

	public int getDecreaseDistanceAngle() {
		return -10;
	}

	
}
