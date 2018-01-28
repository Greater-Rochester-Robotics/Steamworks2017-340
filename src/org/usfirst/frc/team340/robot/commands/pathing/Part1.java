package org.usfirst.frc.team340.robot.commands.pathing;

public class Part1 extends Path {

	public Part1(double speed) {
		super(speed, 76.5);
	}
	@Override
	public double dydx(double t) {
		// TODO Auto-generated method stub
		t = t /76.5;
		return (24 + 30 * t + -72 * Math.pow(t, 2))/ (81 + -300 * t + 423 * Math.pow(t, 2));
	}

}
