package parcours.task.boss.state;



public enum BossState {
	RIGHT(new RightState()), 
	LEFT(new LeftState()) ,
	FORWARD_RIGHT(new ForwardRight()),
	FORWARD_LEFT(new ForwardLeft()),
	BACK_RIGHT(new BackRight()),
	BACK_LEFT(new BackLeft());
	
	private BossStateBase state;

	private BossState(BossStateBase state) {
		this.state = state;
	}

	public BossStateBase getState() {
		return state;
	}

}
