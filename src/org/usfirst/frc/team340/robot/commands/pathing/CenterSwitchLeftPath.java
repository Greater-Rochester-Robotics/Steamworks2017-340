package org.usfirst.frc.team340.robot.commands.pathing;

import org.usfirst.frc.team340.robot.Robot;

/**
 *
 */
public class CenterSwitchLeftPath extends PathOld {

	private double speed = 0;
    public CenterSwitchLeftPath(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super(speed, 141);
    	this.speed = speed;
    }
    
    @Override
    public double dydx(double t) {
    	// TODO Auto-generated method stub
    	t = t / 141;
    	return (12 + 324 * t + -360 * Math.pow(t, 2))/ (294 + -930 * t + 873 * Math.pow(t, 2));
    }
    
    @Override
    public double speed() {
    	// TODO Auto-generated method stub
    	if(Robot.drive.getDistance() > 120) {
    		return 0.2;
    	}
    	return speed;
    }
}
