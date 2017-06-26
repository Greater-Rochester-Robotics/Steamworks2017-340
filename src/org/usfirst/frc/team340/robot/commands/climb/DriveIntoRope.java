package org.usfirst.frc.team340.robot.commands.climb;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The robot is being driven backward into the rope while under human control.
 * The climb motors at this point have reached the engagement speed and 
 * is ready to climb when the rope is contacted.
 * 
 * The command ends when it is in contact and engaged with the rope, which
 * is marked by a current spike.
 */
public class DriveIntoRope extends Command {

    public DriveIntoRope() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("initialize DriveIntoRope");
    	Robot.climber.goAtEngagementSpeed();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.climber.isEngagedWithRope();
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.println("end DriveIntoRope");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
