package i13028;

import java.util.Random;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.TurnCompleteCondition;

public class TestRobot extends AdvancedRobot
{
	boolean movingForward;
	Random random 		= new Random();
	int Ahead 		= 0;
	int Back 		= 0;
	int TurnRight 	= 0;
	int TurnLeft 	= 0;

	public void run() {


		while (true) {

			Ahead = random.nextInt(40000);
			setAhead(Ahead);
			movingForward = true;

			TurnRight = random.nextInt(360);
			setTurnRight(TurnRight);

			waitFor(new TurnCompleteCondition(this));

			TurnLeft = random.nextInt(360);
			setTurnLeft(TurnLeft);

			waitFor(new TurnCompleteCondition(this));

			TurnRight = random.nextInt(360);
			setTurnRight(TurnRight);

			waitFor(new TurnCompleteCondition(this));

		}
	}


	public void onHitWall(HitWallEvent e) {
		if (movingForward) {
			setBack(40000);
			movingForward = false;
		} else {
			setAhead(40000);
			movingForward = true;
		}
	}



	public void onScannedRobot(ScannedRobotEvent e) {
		fire(1);
	}


	public void onHitRobot(HitRobotEvent e) {

		if (e.isMyFault()) {
			if (movingForward) {
				setBack(40000);
				movingForward = false;
			} else {
				setAhead(40000);
				movingForward = true;
			}
		}
	}

}
