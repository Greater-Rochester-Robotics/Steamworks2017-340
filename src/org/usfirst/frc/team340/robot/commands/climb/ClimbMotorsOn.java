package org.usfirst.frc.team340.robot.commands.climb;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

 /**
 * The robot is driving under human control.
 * The climb motors will turn on and begin to go at engagement speed.
 * 
 * The command ends after a time delay.
 */
public class ClimbMotorsOn extends TimedCommand {

    public ClimbMotorsOn(double timeout) {
		super(timeout);
		requires(Robot.climber);	
    }

	// Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("initialize ClimbMotorsOn");
    	Robot.climber.goAtEngagementSpeed();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("end ClimbMotorsOn");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
