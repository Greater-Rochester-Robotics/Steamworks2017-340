package org.usfirst.frc.team340.robot.commands.pathing;

/**
 *
 */
public class CenterSwitchLeftPath extends Path {

    public CenterSwitchLeftPath(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super(speed, 104);
    }
    
    @Override
    public double dydx(double t) {
    	// TODO Auto-generated method stub
    	return (-0.230851 * (Math.pow(t, 2) - 79.5487 * t - 282.685))/(t - 286.027);
    }
}
