package parcours.task.labyrinth;

import parcours.task.labyrinth.config.LabyrinthConfiguration;
import parcours.task.labyrinth.state.LabyrinthState;
import parcours.task.labyrinth.state.LabyrinthStateBase;
import lejos.nxt.LCD;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.RotateMoveController;

public class LabyrinthContext implements RotateMoveController {

	private LabyrinthStateBase state;
	private DifferentialPilot pilot;
	private LabyrinthConfiguration config;

	public LabyrinthContext(LabyrinthConfiguration config, DifferentialPilot pilot) {
		this.config = config;
		state = LabyrinthState.NORMAL.getState();
		this.pilot = pilot;
		
		tStart = System.currentTimeMillis();
		stateName = state.name();
	}
	
	private long tStart;
	private String stateName;

	public void handleBothButtonsPressed(int distance) {
		debugOut();
		state.handleBothButtonsPressed(this, distance);
	}

	public void handleLeftButtonPressed(int distance) {
		debugOut();
		state.handleLeftButtonPressed(this, distance);
	}

	public void handleRightButtonPressed(int distance) {
		debugOut();
		state.handleRightButtonPressed(this, distance);
	}

	public void handleNoButtonIsPressed(int distance) {
		debugOut();
		state.handleNoButtonIsPressed(this, distance);
	}
	
	final static boolean SHOW_ONLY_MAX = false;
	
	static int lineCount = 0;
	static int maxTime = 0;
	private int distance;
	private boolean direction ;
	@SuppressWarnings("unused")
	public void debugOut() {
		
		if (true) {
			return;
		}
		
		final long tNow = System.currentTimeMillis();
		
		final int tDuration = (int)(tNow - tStart);
		
		if ( !SHOW_ONLY_MAX || tDuration > maxTime ) {
			maxTime = tDuration;

			LCD.drawString( stateName, 0, lineCount);
			LCD.drawChar( ':', 8, lineCount);
			LCD.drawInt( tDuration, LCD.DISPLAY_CHAR_WIDTH - 9, 9, lineCount);

			++lineCount;
			lineCount %= 8;
			
			LCD.clear(lineCount);
		}
		
		tStart = tNow;
		stateName = state.name();
	}
	
	public void setState(LabyrinthState stateEnumEntry) {
		this.state = stateEnumEntry.getState();
	}

	public DifferentialPilot getPilot() {
		return pilot;
	}

	@Override
	public void setTravelSpeed(double travelSpeed) {
		pilot.setTravelSpeed(travelSpeed);
	}

	@Override
	public double getTravelSpeed() {
		return pilot.getTravelSpeed();
	}

	@Override
	public double getMaxTravelSpeed() {
		return pilot.getMaxTravelSpeed();
	}

	@Override
	public void setRotateSpeed(double rotateSpeed) {
		pilot.setRotateSpeed(rotateSpeed);
	}

	@Override
	public double getRotateSpeed() {
		return pilot.getRotateSpeed();
	}

	@Override
	public double getRotateMaxSpeed() {
		return pilot.getRotateMaxSpeed();
	}

	@Override
	public void forward() {
		pilot.forward();
	}

	@Override
	public void backward() {
		pilot.backward();
	}

	/**
	 * Note: This method will immediateReturn 
	 */
	@Override
	public void rotate(double angle) {
		pilot.rotate(angle, true);
	}

	@Override
	public void rotate(double angle, boolean immediateReturn) {
		pilot.rotate(angle, immediateReturn);
	}

	@Override
	public void stop() {
		pilot.stop();
	}

	/**
	 * Note: This method will immediateReturn 
	 */
	@Override
	public void travel(double distance) {
		pilot.travel(distance, true);
	}

	@Override
	public void travel(double distance, boolean immediateReturn) {
		pilot.travel(distance, immediateReturn);
	}

	@Override
	public boolean isMoving() {
		return pilot.isMoving();
	}

	@Override
	public Move getMovement() {
		return pilot.getMovement();
	}

	@Override
	public void addMoveListener(MoveListener listener) {
		pilot.addMoveListener(listener);
	}

	public int getCloseDistance() {
		return config.getCloseDistance();
	}

	public int getLargeIncreaseDistanceAngle() {
		return config.getLargeIncreaseDistanceAngle();
	}

	public int getMinimalDistance() {
		return config.getMinimalDistance();
	}

	public int getSmallIncreaseDistanceAngle() {
		return config.getSmallIncreaseDistanceAngle();
	}

	public int getMaximalDistance() {
		return config.getMaximalDistance();
	}

	public int getDecreaseDistanceAngle() {
		return config.getDecreaseDistanceAngle();
	}

	public int getFarDistance() {
		return config.getFarDistance();
	}

	public int getCuttingEdge() {
		return config.getCuttingEdge();
	}

	public int getAfterCuttingEdge() {
		return config.getAfterCuttingEdge();
	}

	public int getNoWallDistance() {
		return config.getNoWallDistance();
	}

	public int getBackwardLeftButtonWall() {
		return config.getBackwardLeftButtonWall();
	}

	public int getBackwardLeftButton() {
		return config.getBackwardLeftButton();
	}

	public int getBackwardRightButton() {
		return config.getBackwardRightButton();
	}

	public int getBackwardBothButton() {
		return config.getBackwardBothButton();
	}

	public int getAdjustmentDistance() {
		return config.getAdjustmentDistance();
	}

	public int getGoingLeftAngle() {
		return config.getGoingLeftAngle();
	}
	
	public int getGoingRightAngle() {
		return config.getGoingRightAngle();
	}

	public void setWallDistance(int distance) {
		this.distance = distance;
	}

	public int getWallDistance() {
		return distance;
	}

	public void setDirectionLeft(boolean direction) {
		this.direction = direction;
	}

	public boolean getDirectionLeft() {
		return direction;
	}

	

}
