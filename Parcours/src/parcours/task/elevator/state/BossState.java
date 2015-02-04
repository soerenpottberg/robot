package parcours.task.elevator.state;



public enum BossState {
	RIGHT(new RightState()), 
	LEFT(new LeftState()) ,
	START_AVOID_lEFT(new StartAvoidLeft()),
	START_AVOID_RIGHT(new StartAvoidRight()),
	END_AVOID_LEFT(new EndAvoidLeft()),
	END_AVOID_RIGHT(new EndAvoidRight()),
	FINISH_MOVEMENT(new FinishMovementState());
	
	private BossStateBase state;

	private BossState(BossStateBase state) {
		this.state = state;
	}

	public BossStateBase getState() {
		return state;
	}

}
