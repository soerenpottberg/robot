package parcours.level;

import java.util.ArrayList;
import java.util.List;

import parcours.bluetooth.TurnTableConnection;
import parcours.level.base.Level;
import parcours.task.FindLineTask;
import parcours.task.FollowLineSpeedTask;
import parcours.task.FollowLineStraightAbortLostLineTask;
import parcours.task.TravelWithSpeedTask;
import parcours.task.TurnTask;
import parcours.task.base.Task;
import parcours.task.bluetooth.BluetoothCloseConnectionTask;
import parcours.task.bluetooth.BluetoothConnectTask;
import parcours.task.bluetooth.BluetoothFinishTurnTableTask;
import parcours.task.bluetooth.BluetoothTurnTurnTableTask;
import parcours.task.bluetooth.BluetoothWaitForTurnTableTask;


public class TurnTable extends Level {
	
	@Override
	public String getLabel() {
		return "Turn Table";
	}

	@Override
	public List<Task> createTaskList() {
		final TurnTableConnection connection = new TurnTableConnection();
		final List<Task> taskList = new ArrayList<Task>();
		taskList.add(new BluetoothConnectTask(connection));
		taskList.add(new BluetoothWaitForTurnTableTask(connection));
		taskList.add(new TravelWithSpeedTask(9, 50, 60));
		taskList.add(new TurnTask(50, 30));
		taskList.add(new FindLineTask(true));
		taskList.add(new FollowLineStraightAbortLostLineTask());
		taskList.add(new TravelWithSpeedTask(-15, 20, 60));
		taskList.add(new TurnTask(50, -20));
		taskList.add(new TravelWithSpeedTask(30, 20, 60));
		taskList.add(new BluetoothTurnTurnTableTask(connection));
		taskList.add(new TravelWithSpeedTask(-50, 30, 60));
		taskList.add(new BluetoothFinishTurnTableTask(connection));
		taskList.add(new BluetoothCloseConnectionTask(connection));
		taskList.add(new TurnTask(90, 180));
		taskList.add(new TravelWithSpeedTask(80, 150, 150));
		taskList.add(new TurnTask(50, 45));
		taskList.add(new FindLineTask());
		taskList.add(new FollowLineSpeedTask());
		return taskList;
	}

}
