package parcours.task.wall.state;

import lejos.robotics.navigation.DifferentialPilot;

public class WallStateContext {
	
	private WallStateBase state;
	private DifferentialPilot pilot;
	
	public WallStateContext(DifferentialPilot pilot) {
		state = WallState.NORMAL.getState();
		this.pilot = pilot;
	}

	public void handleBothButtonsPressed(int distance) {
		state.handleBothButtonsPressed(this, distance);
	}

	public void handleLeftButtonPressed(int distance) {
		state.handleLeftButtonPressed(this, distance);
	}

	public void handleRightButtonPressed(int distance) {
		state.handleRightButtonPressed(this, distance);
	}

	public void handleNoButtonIsPressed(int distance) {
		state.handleNoButtonIsPressed(this, distance);
	}

	public void setState(WallState stateEnumEntry) {
		this.state = stateEnumEntry.getState();		
	}

	public DifferentialPilot getPilot() {
		return pilot;
	}

}
