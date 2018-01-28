package org.usfirst.frc.team340.robot.commands.pathing;

public class Part2 extends Path {

	public Part2(double speed) {
		super(speed, 125);
	}
	@Override
	public double dydx(double t) {
		t = t / 125;
		return (30 + 78 * t + -81 * Math.pow(t, 2))/ (156 + -540 * t + 147 * Math.pow(t, 2));
	}

}
