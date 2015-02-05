package parcours.task.boss;

import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.RotateMoveController;
import parcours.task.boss.state.BossState;
import parcours.task.boss.state.BossStateBase;

public class BossContext implements RotateMoveController {

	private BossStateBase state;
	private DifferentialPilot pilot;

	public BossContext(DifferentialPilot pilot) {
		state = BossState.RIGHT.getState();
		this.pilot = pilot;
	}

	public void handleButtonPressed() {
		state.handleButtonPressed(this);
	}

	public void handleNoButtonIsPressed() {
		state.handleNoButtonIsPressed(this);
	}
	
	public void setState(BossState stateEnumEntry) {
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

}
