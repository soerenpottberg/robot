package parcours.task.labyrinth.state;

import parcours.task.labyrinth.LabyrinthContext;

public class NormalState extends LabyrinthStateBase {
	
	public String name() {
		return "Normal";
	}

	@Override
	public void handleNoButtonIsPressed(LabyrinthContext context, int distance) {
		if ((distance < context.getCloseDistance())) {
			context.rotate(context.getLargeInreaseDistanceAngle());
			context.setState(LabyrinthState.START_CHANGING_DISTANCE_LEFT);
		} else if (distance > context.getCloseDistance() && distance < context.getMinimalDistance()) {
			context.rotate(context.getSmallInreaseDistanceAngle());
			context.setState(LabyrinthState.START_CHANGING_DISTANCE_LITTLE_LEFT);
		} else if ((distance > context.getMaximalDistance() && distance < context.getFarDistance())) {
			context.rotate(context.getDecreaseDistanceAngle());
			context.setState(LabyrinthState.START_CHANGING_DISTANCE_RIGHT);
		} else if (distance > context.getFarDistance()) {
			context.travel(context.getCuttingEdge());
			context.setState(LabyrinthState.START_90_DEGREE_RIGHT_TURN);
		}
	}

}
