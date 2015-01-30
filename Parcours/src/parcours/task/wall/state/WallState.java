package parcours.task.wall.state;

public enum WallState {
	NORMAL(new NormalState()),
	START_90_DEGREE_LEFT_TURN(new Start90DegreeLeftTurn()),
	END_CHANGING_DISTANCE_LITTLE_LEFT(new EndChangingDistance(-10)), // -SMALL_TURN_LEFT_ANGLE
	END_CHANGING_DISTANCE_LEFT(new EndChangingDistance(-20)), // -Large_TURN_LEFT_ANGLE
	END_CHANGING_DISTANCE_RIGHT(new EndChangingDistance(10)),  // -turn_RIGHT_ANGLE
	START_CHANGING_DISTANCE_LITTLE_LEFT(new StartChangingDistance(END_CHANGING_DISTANCE_LITTLE_LEFT)),
	START_CHANGING_DISTANCE_LEFT(new StartChangingDistance(END_CHANGING_DISTANCE_LEFT)),
	START_CHANGING_DISTANCE_RIGHT(new StartChangingDistance(END_CHANGING_DISTANCE_RIGHT)),
	FINISH_MOVEMENT(new FinishMovementState()),
	START_90_DEGREE_RIGHT_TURN(new Start90DegreeRightTurn()),
	END_90_DEGREE_RIGHT_TURN(new End90DegreeRightTurn()),
	START_GOING_LEFT(new StartGoingLeft()), 
	START_GOING_RIGHT(new StartGoingRight());
	
	private WallStateBase state;

	private WallState(WallStateBase state) {
		this.state = state;
	}

	public WallStateBase getState() {
		return state;
	}

}
