package parcours.level.combination;

import java.util.ArrayList;
import java.util.List;

import parcours.level.base.Level;
import parcours.level.base.LevelCombination;
import parcours.level.test.TestElevatorBluetooth;
import parcours.level.test.TestGateBluetooth;
import parcours.level.test.TestTurnTableBluetooth;

public class BluetoothLevelCombination extends LevelCombination {
	
	@Override
	public String getLabel() {
		return "Test Bluetooth";
	}

	@Override
	public List<Level> createLevelList() {
		final ArrayList<Level> levelList = new ArrayList<Level>();
		levelList.add(new TestTurnTableBluetooth());
		levelList.add(new TestGateBluetooth());
		levelList.add(new TestElevatorBluetooth());
		return levelList;
	}
	
	@Override
	public boolean isTestLevelCombination() {
		return true;
	}

}
