package i13003;
import java.awt.Color;
import java.util.Random;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.TurnCompleteCondition;
//import java.awt.Color;

/**
 * Nagao - a robot by (your name here)
 */
public class Wham extends AdvancedRobot{
	/**
	 * run: Nagao's default behavior
	 */
	boolean movingForward;
	Random random = new Random();
	int Ahead = 0;
	int Back = 0;
	int TurnRight = 0;
	int TurnLeft = 0;
	public void run() {
		setAdjustGunForRobotTurn(true);
		//setAdjustRadarForGunTurn(true);
		//setAdjustRadarForRobotTurn(true);
		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:
		setColors(Color.black,Color.black,Color.BLACK);
		while(true) {
			//crazy();
			//setAhead(1000);
			turnGunRight(360);
				barsark();

	}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {

		//setTurnRadarRight(e.getBearing()-getHeading()-getRadarHeading());
		//fireBullet(0.1);



		double velocity = 0.0;
		velocity = e.getVelocity();

		double direction = 0.0;
		direction = e.getHeading();

		double distance = 0.0;
		distance = e.getDistance();

		//弾速 ＝ 20-3*power
		//(direction/11)*velocity

		turnRight(e.getBearing());
		setAhead(200);

		if(e.getBearing()-getGunHeading() <= 90){
			//turnRadarRight(e.getBearing()-(getRadarHeading()-getHeading()));
			turnGunRight(e.getBearing()-(getGunHeading()-getHeading()));
		}else{
			//turnRadarLeft(e.getBearing()-(getRadarHeading()-getHeading()));
			turnGunLeft(e.getBearing()-(getGunHeading()-getHeading()));
		}
		setFire(3);



	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		//turnGunRight(e.getBearing());
	}
	public void barsark(){
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
	public void onHitWall(HitWallEvent e) {
		if (movingForward) {
			setBack(400);
			movingForward = false;
		} else {
			setAhead(400);
			movingForward = true;
		}
	}
	public void onHitRobot(HitRobotEvent e) {
		// If we're moving the other robot, reverse!
		if (e.isMyFault()) {
		if (movingForward) {
		setBack(400);
		movingForward = false;
		} else {
		setAhead(400);
		movingForward = true;
		}
		}
		}

}

