package org.usfirst.frc.team340.robot.commands.pathing;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwitchLeftReturn extends PathOld {

    public SwitchLeftReturn(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super(speed, 135.5);
    }
    @Override
    public double dydx(double t) {
    	// TODO Auto-generated method stub
    	t = t / -135.5;
//    	System.out.println(t);
    	return (15 + -342 * t + 348 * Math.pow(t, 2))/ (-240 + 822 * t + -876 * Math.pow(t, 2));
    }
}
