package parcours.task.labyrinth.state;

import parcours.task.labyrinth.state.EndChangingDistance.DistanceChange;

public enum LabyrinthState {
	NORMAL(new NormalState()),
	START_90_DEGREE_LEFT_TURN(new Start90DegreeLeftTurn()),
	END_CHANGING_DISTANCE_LITTLE_LEFT(new EndChangingDistance(DistanceChange.SMALL_INCREASE_OF_DISTANCE)),
	END_CHANGING_DISTANCE_LEFT(new EndChangingDistance(DistanceChange.LARGE_INCREASE_OF_DISTANCE)),
	END_CHANGING_DISTANCE_RIGHT(new EndChangingDistance(DistanceChange.DECREASE_OF_DISTANCE)),
	START_CHANGING_DISTANCE_LITTLE_LEFT(new StartChangingDistance(END_CHANGING_DISTANCE_LITTLE_LEFT)),
	START_CHANGING_DISTANCE_LEFT(new StartChangingDistance(END_CHANGING_DISTANCE_LEFT)),
	START_CHANGING_DISTANCE_RIGHT(new StartChangingDistance(END_CHANGING_DISTANCE_RIGHT)),
	FINISH_MOVEMENT(new FinishMovementState()),
	START_90_DEGREE_RIGHT_TURN(new Start90DegreeRightTurn()),
	END_90_DEGREE_RIGHT_TURN(new End90DegreeRightTurn()),
	START_GOING_LEFT(new StartGoingLeft()), 
	START_GOING_RIGHT(new StartGoingRight());
	
	private LabyrinthStateBase state;

	private LabyrinthState(LabyrinthStateBase state) {
		this.state = state;
	}

	public LabyrinthStateBase getState() {
		return state;
	}

}
