package org.usfirst.frc.team340.robot.commands.pathing;

public class Part3 extends Path {

	public Part3(double speed) {
		super(speed, 161.8);
	}
	@Override
	public double dydx(double s) {
		// TODO Auto-generated method stub
		double t = s / 161.8;
		return (-129 + 372 * t + -396 * Math.pow(t, 2))/ (-192 + 1062 * t + -720 * Math.pow(t, 2));
	}

}
