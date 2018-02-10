package org.usfirst.frc.team340.robot.commands.pathing;

public class Part4 extends PathOld {

	public Part4(double speed) {
		super(speed, 119.1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double dydx(double s) {
		// TODO Auto-generated method stub
		double t = s / 119.1;
		return (-69 + 522 * t + -336 * Math.pow(t, 2))/ (45 + 216 * t + -459 * Math.pow(t, 2));
	}

}
