package org.usfirst.frc.team340.robot.commands.pathing;

/**
 *
 */
public class CenterSwitchLeftPath extends Path {

    public CenterSwitchLeftPath(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super(speed, 141);
    }
    
    @Override
    public double dydx(double t) {
    	// TODO Auto-generated method stub
    	t = t / 141;
    	return (12 + 324 * t + -360 * Math.pow(t, 2))/ (294 + -930 * t + 873 * Math.pow(t, 2));
    }
}
