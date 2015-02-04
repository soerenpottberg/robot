package parcours.task.elevator.state;



public enum ElevatorState {
	NORMAL(new NormalState()),
	//START_90_DEGREE_LEFT_TURN(new Start90DegreeLeftTurn()),
	//START_90_DEGREE_RIGHT_TURN(new Start90DegreeRightTurn()),
	FINISH_MOVEMENT(new FinishMovementState());
	
	private ElevatorStateBase state;

	private ElevatorState(ElevatorStateBase state) {
		this.state = state;
	}

	public ElevatorStateBase getState() {
		return state;
	}

}
