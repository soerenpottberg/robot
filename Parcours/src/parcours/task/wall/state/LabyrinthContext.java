package parcours.task.wall.state;

import lejos.robotics.navigation.DifferentialPilot;

public class LabyrinthContext {
	
	private LabyrinthStateBase state;
	private DifferentialPilot pilot;
	
	public LabyrinthContext(DifferentialPilot pilot) {
		state = LabyrinthState.NORMAL.getState();
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

	public void setState(LabyrinthState stateEnumEntry) {
		this.state = stateEnumEntry.getState();		
	}

	public DifferentialPilot getPilot() {
		return pilot;
	}

}
