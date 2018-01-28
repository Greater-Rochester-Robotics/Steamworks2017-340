package org.usfirst.frc.team340.robot.commands.pathing;

public class Part5 extends Path {

	public Part5(double speed) {
		super(speed, 139.4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double dydx(double s) {
		// TODO Auto-generated method stub
		double t = s / 139.4;
		return (120 + -192 * t + 21 * Math.pow(t, 2))/ (24 + -318 * t + 66 * Math.pow(t, 2));
	}

}
