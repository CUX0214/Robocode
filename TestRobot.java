package i13028;

import java.awt.Color;
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


	/**
	 * run: Crazy's main run function
	 */
	public void run() {
		// Set colors
		setBodyColor(new Color(0, 200, 0));
		setGunColor(new Color(0, 150, 50));
		setRadarColor(new Color(0, 100, 100));
		setBulletColor(new Color(255, 255, 100));
		setScanColor(new Color(255, 200, 200));

		while (true) {
			// Tell the game we will want to move ahead 40000 -- some large number
			Ahead = random.nextInt(40000);
			setAhead(Ahead);
			movingForward = true;
			// Tell the game we will want to turn right 90
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

	/**
	 * onHitWall:  Handle collision with wall.
	 */
	public void onHitWall(HitWallEvent e) {
		if (movingForward) {
			setBack(40000);
			movingForward = false;
		} else {
			//setAhead(40000);
			movingForward = true;
		}
	}


	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(1);
	}

	/**
	 * onHitRobot:  Back up!
	 */
	public void onHitRobot(HitRobotEvent e) {
		// If we're moving the other robot, reverse!
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
